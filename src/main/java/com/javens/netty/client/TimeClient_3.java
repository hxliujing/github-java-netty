package com.javens.netty.client;

import com.javens.netty.codec.NettyMessageDecoder;
import com.javens.netty.codec.NettyMessageEncoder;
import com.javens.netty.handler.HeartBeatReqHandler;
import com.javens.netty.handler.LoginAuthReqHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class TimeClient_3 {

    public void connect(int port,String host) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyMessageDecoder(1024*1024,4,4));
                            ch.pipeline().addLast("Encoder",new NettyMessageEncoder());
                            ch.pipeline().addLast(new ReadTimeoutHandler(50));
                            ch.pipeline().addLast(new LoginAuthReqHandler());
                            ch.pipeline().addLast("HeartBeatHandler",new HeartBeatReqHandler());
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
        new TimeClient_3().connect(port,"127.0.0.1");
       // Thread.sleep(1000 * 60);
    }
}
