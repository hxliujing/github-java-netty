package com.javens.netty.handler;

import com.javens.netty.codec.JsonDecoderIn;
import com.javens.netty.codec.JsonEncoderOut;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;


public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel arg0) throws Exception {
        //基于长度字段的帧解码器。注意,这是解码器,不负责编码。经常和MessageToByteEncoder配合使用。
        arg0.pipeline().addLast("frameDecoderIn", new LengthFieldBasedFrameDecoder(1024 * 64, 0, 4));
        //json编码,输出
        arg0.pipeline().addLast("encodeOut",new JsonEncoderOut());
        //json解码,输入
        arg0.pipeline().addLast("decodeIn",new JsonDecoderIn());
       // arg0.pipeline().addLast(new StringDecoder());
        arg0.pipeline().addLast(new TimeServerHandler());
    }
}
