package com.javens.netty.handler;


import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Date;

public class TimeServerHandler extends SimpleChannelInboundHandler<Object> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        //tcp channel
        Channel channel = ctx.channel();
        //tcp address
        InetSocketAddress tcpAddr = (InetSocketAddress) channel.remoteAddress();
        InetAddress addr = tcpAddr.getAddress();//addr ip
        String hostname = tcpAddr.getHostName();//name
        int port = tcpAddr.getPort();//port

        StringBuilder buf = new StringBuilder(500);
        buf.append("\n >>> channelRead0 ");
        buf.append("\n addr = ").append(JSON.toJSONString(addr));
        buf.append("\n hostname = ").append(hostname);
        buf.append("\n port = ").append(port);
        buf.append("\n bird = ").append(JSON.toJSONString(msg));
        //System.out.println(buf.toString());

//        msg.setAge(msg.getAge() + 1);
//        ctx.writeAndFlush(msg);
    }


    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        InetSocketAddress tcpAddr = (InetSocketAddress) channel.remoteAddress();

        StringBuilder buf = new StringBuilder(500);
        buf.append("\n >>> channelActive ");
       // buf.append("  now=").append(Utils.nowDatetime());
        buf.append("  addr=").append(JSON.toJSONString(tcpAddr));
        //System.out.println(buf.toString());

        ctx.fireChannelActive();
    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        InetSocketAddress tcpAddr = (InetSocketAddress) channel.remoteAddress();

        StringBuilder buf = new StringBuilder(500);
        buf.append("\n >>> channelInactive ");
       // buf.append("  now=").append(Utils.nowDatetime());
        buf.append("  addr=").append(JSON.toJSONString(tcpAddr));
        //System.out.println(buf.toString());

        ctx.fireChannelInactive();
    }
}
