/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.javens.netty.protocol;

/**
 *
 * @author liujing01
 * @version NettyMessage.java, v 0.1 2018-09-24 21:03 
 */
public final class NettyMessage {
    /**
     * 消息头
     */
    private Header header;

    /**
     * 消息体
     */
    private Object body;

    /**
     * Getter method for property <tt>header</tt>.
     *
     * @return property value of header
     */
    public final Header getHeader() {
        return header;
    }

    /**
     * Setter method for property <tt>header</tt>.
     *
     * @param header value to be assigned to property header
     */
    public final  void setHeader(Header header) {
        this.header = header;
    }

    /**
     * Getter method for property <tt>body</tt>.
     *
     * @return property value of body
     */
    public final Object getBody() {
        return body;
    }

    /**
     * Setter method for property <tt>body</tt>.
     *
     * @param body value to be assigned to property body
     */
    public final void setBody(Object body) {
        this.body = body;
    }
}
