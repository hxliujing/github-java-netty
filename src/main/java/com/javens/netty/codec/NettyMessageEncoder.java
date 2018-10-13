/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.javens.netty.codec;

import com.javens.netty.protocol.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author liujing01
 * @version NettyMessageEncoder.java, v 0.1 2018-09-24 21:14 
 */
public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {
    MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException{
        this.marshallingEncoder = new MarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf sendBuf) throws Exception {
        if(msg==null || msg.getHeader()==null)
            throw new Exception("The encode message is null");

        sendBuf.writeInt(msg.getHeader().getCrcCode());
        sendBuf.writeInt(msg.getHeader().getLength());
        sendBuf.writeLong(msg.getHeader().getSessionID());
        sendBuf.writeByte(msg.getHeader().getType());
        sendBuf.writeByte(msg.getHeader().getPriority());
        sendBuf.writeInt(msg.getHeader().getAttachment().size());


        String key = null;
        byte[] keyArray =  null;
        Object value = null;
        for(Map.Entry<String,Object> param: msg.getHeader().getAttachment().entrySet()){
            key = param.getKey();
            keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            value = param.getValue();
            marshallingEncoder.encode(value,sendBuf);
        }

        key = null;
        keyArray = null;
        value = null;
        if(msg.getBody() != null){
            marshallingEncoder.encode(msg.getBody(),sendBuf);
        }else{
            //如果没有数据 则进行补位 为了方便后续的 decoder操作
            sendBuf.writeInt(0);
        }
        //最后我们要获取整个数据包的总长度 也就是 header +  body 进行对 header length的设置

        // TODO:  解释： 在这里必须要-8个字节 ，是因为要把CRC和长度本身占的减掉了
        //（官方中给出的是：LengthFieldBasedFrameDecoder中的lengthFieldOffset+lengthFieldLength）
        //总长度是在header协议的第二个标记字段中
        //第一个参数是长度属性的索引位置
        sendBuf.setInt(4, sendBuf.readableBytes() - 8);

    }
}
