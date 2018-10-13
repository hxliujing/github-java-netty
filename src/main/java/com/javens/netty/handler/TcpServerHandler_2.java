package com.javens.netty.handler;


import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;
import java.net.InetSocketAddress;

public class TcpServerHandler_2 extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server receive message :"+ msg);
        ctx.channel().writeAndFlush("yes server already accept your message" + msg);
        ctx.close();
    }


    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive>>>>>>>>");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("exception is general");
        super.exceptionCaught(ctx, cause);
    }
}
