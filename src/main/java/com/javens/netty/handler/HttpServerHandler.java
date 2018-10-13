/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.javens.netty.handler;

import com.alibaba.fastjson.JSON;
import com.javens.netty.entity.Order;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.util.CharsetUtil;

import java.net.URI;

import static com.sun.deploy.net.HttpRequest.CONTENT_LENGTH;
import static com.sun.deploy.net.HttpRequest.CONTENT_TYPE;
import static io.netty.buffer.Unpooled.copiedBuffer;

/**
 *
 * @author liujing01
 * @version HttpServerHandler.java, v 0.1 2018-10-05 21:49 
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    private HttpRequest request;
    private HttpPostRequestDecoder decoder;
    private final StringBuilder responseContent = new StringBuilder();
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        System.err.println(msg.getClass().getName());
        if (msg instanceof HttpRequest) {
            this.request = (HttpRequest) msg;
            URI uri = new URI(request.getUri());
            System.err.println("request uri==" + uri.getPath());

            if(uri.getPath().equals("/favicon.ico")){
                return;
            }
            
            if(uri.getPath().equals("/")){
                writeMenu(ctx);
                return;
            }

            if(uri.getPath().equals("/get")){
                writeMenu(ctx);
                return;
            }

            if(uri.getPath().equals("/post")){
                writePostMenu(ctx,0);
                return;
            }
            if(uri.getPath().equals("/post300")){
                writePostMenu(ctx,300);
                return;
            }
        }
    }


    private void writePostMenu(ChannelHandlerContext ctx,int time) {
        if(time>0){
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        responseContent.setLength(0);
        Order order = new Order(1,"Liujing","Netty Book","151****4085","中国杭州");
        ByteBuf buf = copiedBuffer(JSON.toJSONString(order), CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
        response.headers().set(CONTENT_TYPE, "application/json; charset=UTF-8");
        response.headers().set(CONTENT_LENGTH, buf.readableBytes());
        ctx.channel().writeAndFlush(response);
    }


    private void writeMenu(ChannelHandlerContext ctx) {
        responseContent.setLength(0);

        responseContent.append("<html>")
                       .append("<head>")
                       .append("<title>Netty Test Form</title>\r\n")
                       .append("</head>\r\n")
                       .append("<body bgcolor=white><style>td{font-size: 12pt;}</style>")

                       .append("<table border=\"0\">")
                       .append("<tr>")
                       .append("<td>")
                       .append("<h1>Netty Test Form</h1>")
                       .append("Choose one FORM")
                       .append("</td>")
                       .append("</tr>")
                       .append("</table>\r\n");

        responseContent.append("</body>")
                       .append("</html>");

        ByteBuf buf = copiedBuffer(responseContent.toString(), CharsetUtil.UTF_8);
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buf);
        response.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
        response.headers().set(CONTENT_LENGTH, buf.readableBytes());
        ctx.channel().writeAndFlush(response);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        if(decoder!=null){
            decoder.cleanFiles();
        }
    }


}
