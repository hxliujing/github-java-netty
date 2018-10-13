package com.javens.netty.codec;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class JsonDecoderIn extends ByteToMessageDecoder{
    public JsonDecoderIn() {
        System.out.println("\n ## new JsonDecoderIn  ");
    }
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //开头的4个字节设为了长度字段。new LengthFieldBasedFrameDecoder(8192, 0, 4)
        if (in.readableBytes() < 4) {
            return;
        }

        //标识读的下标
        in.markReaderIndex();

        //读一个int,是4个字节,表示长度
        int dataLength = in.readInt();

        //如果数据长度不对,就重置读下标
        if (in.readableBytes() < dataLength) {
            in.resetReaderIndex();
            return;
        }

        //获得字节数组
        byte[] bytes2 = new byte[dataLength];
        in.readBytes(bytes2);

        String str = new String(bytes2);
        System.out.println("\n >>> decode  json=" + str);

        //转成 对象
      /*  ProtocolBean bean = JSON.parseObject(str, ProtocolBean.class);
        list.add(bean);*/
    }
}
