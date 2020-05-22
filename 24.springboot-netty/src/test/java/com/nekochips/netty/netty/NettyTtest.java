package com.nekochips.netty.netty;

import com.nekochips.netty.NettyDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author NekoChips
 * @description Netty 测试类
 * @date 2020/5/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NettyDemoApplication.class)
public class NettyTtest {

    @Autowired
    private TimeServer timeServer;

    @Autowired
    private TimeClient timeClient;
    
    @Test
    public void testServer() {
        timeServer.start(1080);
    }
    
    @Test
    public void closeServer() {
        timeServer.stop();
    }
    
    @Test
    public void testClient() {
        timeClient.connect("localhost", 1080);
    }
}
