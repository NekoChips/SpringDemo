package com.nekochips.netty.nio;

import com.nekochips.netty.NettyDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author NekoChips
 * @description nio 测试类
 * @date 2020/5/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NettyDemoApplication.class)
public class NioTest {

    @Autowired
    private NioServer nioServer;

    @Autowired
    private NioClient nioClient;

    @Test
    public void testServer() {
        nioServer.init();
        nioServer.listen();
    }

    @Test
    public void testClient() {
        nioClient.start();
        nioClient.connect();
        nioClient.listen();
    }

}
