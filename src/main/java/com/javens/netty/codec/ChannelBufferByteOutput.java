/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.javens.netty.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteOutput;

import java.io.IOException;

/**
 *
 * @author liujing01
 * @version ChannelBufferByteOutput.java, v 0.1 2018-09-24 21:44 
 */
public class ChannelBufferByteOutput implements ByteOutput {
    private final ByteBuf buffer;

    public ChannelBufferByteOutput(ByteBuf buffer){
        this.buffer = buffer;
    }

    @Override
    public void write(int b) throws IOException {
        buffer.writeByte(b);
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        buffer.writeBytes(bytes);
    }

    @Override
    public void write(byte[] bytes, int srcIndex, int length) throws IOException {
        buffer.writeBytes(bytes, srcIndex, length);
    }

    @Override
    public void close() throws IOException {

    }

    @Override
    public void flush() throws IOException {

    }

    ByteBuf getBuffer() {
        return buffer;
    }


}
