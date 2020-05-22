package com.nekochips.netty.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * @author NekoChips
 * @description 客户端时间处理器
 * @date 2020/5/21
 */
@Slf4j
public class TimeClientHandler extends ChannelDuplexHandler {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String message = "QUERY THE CURRENT TIME";
        ByteBuf byteBuf = Unpooled.buffer(message.getBytes().length);
        byteBuf.writeBytes(message.getBytes(StandardCharsets.UTF_8));
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] response = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(response);
        String currentTime = new String(response, StandardCharsets.UTF_8);
        log.info("Now is : {}", currentTime);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.getMessage(), cause);
        ctx.close();
    }
}
