package com.javens.netty.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class JsonEncoderOut extends MessageToByteEncoder<Object> {

    public JsonEncoderOut() {
        System.out.println("\n ## new JsonEncoderOut  ");
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object bean, ByteBuf out) throws Exception {
        //对象转json
        String json = JSON.toJSONString(bean);
        //json转字节数组
        byte[] bytes = json.getBytes();
        System.out.println("\n >>>  encode  json=" + json);
        //通常将 MessageToByteEncoder 和 LengthFieldBasedFrameDecoder 组合起来使用
        //写,lengthField,数据长度,字节数组长度
        out.writeInt(bytes.length);
        //写,数据,字节数组
        out.writeBytes(bytes);
    }
}
