package com.nekochips.netty.bio;

import com.nekochips.netty.NettyDemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author NekoChips
 * @description BioServer 测试类
 * @date 2020/5/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = NettyDemoApplication.class)
public class BioTest {

    @Autowired
    private BioServer bioServer;

    @Autowired
    private BioClient bioClient;
    
    @Test
    public void testServer() {
        bioServer.start();
    }
    
    @Test
    public void testClient() {
        bioClient.start();
        bioClient.send("hello i am a test message.");
    }
}
