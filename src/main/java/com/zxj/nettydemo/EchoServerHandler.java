package com.zxj.nettydemo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.io.UnsupportedEncodingException;

/**
 * @author: zxj
 * @date: 2019/5/27
 */
@Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    public EchoServerHandler() {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        ByteBuf in = (ByteBuf) msg;
        //写消息到控制
        System.out.println("Server received:"+in.toString(CharsetUtil.UTF_8));
        ctx.write(in);  //将消息回写给发送者，而不是冲刷出消息
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
