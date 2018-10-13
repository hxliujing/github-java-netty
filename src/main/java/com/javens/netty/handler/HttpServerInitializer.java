/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.javens.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;


/**
 *
 * @author liujing01
 * @version HttpServerInitializer.java, v 0.1 2018-10-05 21:45 
 */
public class HttpServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline().addLast("decoder", new HttpRequestDecoder());
        ch.pipeline().addLast("encoder", new HttpResponseEncoder());
        ch.pipeline().addLast("deflater",new HttpContentCompressor());
        ch.pipeline().addLast("handler",new HttpServerHandler());

    }
}
