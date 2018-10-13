package com.javens.netty.handler;


import com.javens.netty.protocol.Header;
import com.javens.netty.protocol.MessageType;
import com.javens.netty.protocol.NettyMessage;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class LoginAuthReqHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(buildLoginReq());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        if(message.getHeader()!=null && message.getHeader().getType()== MessageType.LOGIN_RESP.value()){
            byte loginResult = (byte) message.getBody();
            if(loginResult !=(byte)0){
                ctx.close();
            }else{
                System.out.println("Login is ok:"+ message);
                ctx.fireChannelRead(msg);
            }
        }else{
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildLoginReq() {
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.LOGIN_REQ.value());
        message.setHeader(header);
        return message;
    }
}
