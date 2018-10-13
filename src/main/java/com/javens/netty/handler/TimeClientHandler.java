package com.javens.netty.handler;


import com.alibaba.fastjson.JSON;
import com.javens.netty.entity.Order;
import com.sun.org.apache.xpath.internal.operations.String;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {
    private int counter;

    private byte[] req;

    public TimeClientHandler(){
        List<Order> list = new ArrayList<>();
        for(int i=0;i<1;i++){
            Order order = new Order(i+1,"Liujing-"+i,"Netty Book","151****4085","中国杭州");
            list.add(order);
        }
        req = JSON.toJSONString(list).getBytes();
        System.out.println(new java.lang.String(req));
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for(int i=0;i<100;i++){
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
       /* ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");*/
       String body = (String) msg;

        System.out.println("Now is : " + body +";the counter is : " + ++counter);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       // super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}
