package com.demo.crypt.core.handler;

import com.demo.crypt.core.alias.CryptType;
import com.demo.crypt.util.RsaUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author NekoChips
 * @description 自定义加解密类型处理器
 * @date 2020/4/26
 */
@Slf4j
@MappedTypes(CryptType.class)
public class CryptTypeHandler extends BaseTypeHandler<String> {

    static final int INPUT_MAXIMUM_LENGTH = 18;

    static RSAPublicKey publicKey;

    static RSAPrivateKey privateKey;
    
    static {
        try {
            KeyPair keyPair = RsaUtil.getKeyPair();
            publicKey = (RSAPublicKey) keyPair.getPublic();
            privateKey = (RSAPrivateKey) keyPair.getPrivate();
        } catch (NoSuchAlgorithmException e) {
            log.error("RsaUtil create keyPair fail", e);
        }
    }
    
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        try {
            String encrypt = RsaUtil.encrypt(parameter, publicKey);
            ps.setString(i, encrypt);
        } catch (Exception e) {
            log.error("encrypt message failed", e);
            // 加密失败，使用原始数据
            ps.setString(i, parameter);
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) {
        try {
            String parameter = rs.getString(columnName);
            if (isEncrypt(parameter)) {
                return RsaUtil.decrypt(parameter, privateKey);
            }
            // 不是密文直接返回
            return parameter;
        } catch (Exception e) {
            log.error("decrypt message failed", e);
        }
        return null;
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) {
        try {
            String parameter = rs.getString(columnIndex);
            if (isEncrypt(parameter)) {
                return RsaUtil.decrypt(parameter, privateKey);
            }
            return parameter;
        } catch (Exception e) {
            log.error("decrypt message failed", e);
        }
        return null;
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) {
        try {
            String parameter = cs.getString(columnIndex);
            if (isEncrypt(parameter)) {
                return RsaUtil.decrypt(parameter, privateKey);
            }
            return parameter;
        } catch (Exception e) {
            log.error("decrypt message failed", e);
        }
        return null;
    }
    
    /**
     * 判断是否为加密过的内容
     */
    private boolean isEncrypt(String parameter) {
        // 这里只是粗略地对密文长度进行判断，不一定准确，实际开发中可从但不限于以下维度进行判断：
        // 1. 密文长度
        // 2. 是否包含中文
        // 3. 特殊字符
        // 4. 特定格式（正则表达式）
        // 5. ....
        return parameter.length() > INPUT_MAXIMUM_LENGTH;
    }
}
