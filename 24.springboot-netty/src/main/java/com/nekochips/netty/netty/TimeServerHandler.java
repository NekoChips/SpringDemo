package com.nekochips.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author NekoChips
 * @description 服务端时间处理器
 * @date 2020/5/21
 */
@Slf4j
public class TimeServerHandler extends ChannelDuplexHandler {

    private static final String CORRECT_REQUEST = "QUERY THE CURRENT TIME";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] reqBytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(reqBytes);
        String request = new String(reqBytes, StandardCharsets.UTF_8);
        log.info("time server receive request: {}", request);

        String currentTime = CORRECT_REQUEST.equals(request) ? new Date().toString() : "WRONG REQUEST";
        ByteBuf respBuffer = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(respBuffer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
        ctx.close();
    }
}
