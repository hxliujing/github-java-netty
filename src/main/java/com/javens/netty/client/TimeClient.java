package com.javens.netty.client;

import com.javens.netty.codec.JsonDecoderIn;
import com.javens.netty.codec.JsonEncoderOut;
import com.javens.netty.handler.TimeClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

public class TimeClient {

    public void connect(int port,String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("frameDecoderIn", new LengthFieldBasedFrameDecoder(1024 * 64, 0, 4));
                            ch.pipeline().addLast("encodeOut",new JsonEncoderOut());
                            ch.pipeline().addLast("decodeIn",new JsonDecoderIn());
                            ch.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            ChannelFuture f = b.connect(host,port).sync();

            f.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }


    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        new TimeClient().connect(port,"127.0.0.1");
       // Thread.sleep(1000 * 60);
    }
}
