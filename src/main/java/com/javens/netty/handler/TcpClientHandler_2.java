package com.javens.netty.handler;


import com.alibaba.fastjson.JSON;
import com.javens.netty.entity.Order;
import com.sun.org.apache.xpath.internal.operations.String;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TcpClientHandler_2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client接收到服务器返回的消息:" + msg);
    }
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for(int i=0;i<100;i++){
            byte[] req = new Order(i+1,"liujing","Netty NIO","151****4085","中国.杭州").toString().getBytes();
            if(message==null){
                message = Unpooled.copiedBuffer(req);
            }else{
                message = Unpooled.copiedBuffer(message.array(),req);
            }

        }
        System.out.println("可读字节" + message.readableBytes());
        ctx.writeAndFlush(message);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
       // super.exceptionCaught(ctx, cause);
        ctx.close();
    }


}
