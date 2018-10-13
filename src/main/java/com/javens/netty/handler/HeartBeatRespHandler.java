/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.javens.netty.handler;

import com.javens.netty.protocol.Header;
import com.javens.netty.protocol.MessageType;
import com.javens.netty.protocol.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelOutboundHandlerAdapter;

/**
 *
 * @author liujing01
 * @version HeartBeatRespHandler.java, v 0.1 2018-09-26 23:18 
 */
public class HeartBeatRespHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if(message.getHeader()!=null && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()){
            System.out.println("Receive client heart beat message: --->"+ message);
            NettyMessage heartBeat = buildHeartBeat();
            System.out.println("Send heart beat response message to client: --->"+ heartBeat);
            ctx.writeAndFlush(heartBeat);
        }else{
            ctx.fireChannelRead(msg);
        }



    }

    private NettyMessage buildHeartBeat() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        message.setHeader(header);
        return message;
    }
}
