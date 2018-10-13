/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.javens.netty.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.ByteOutput;

import java.io.IOException;

/**
 *
 * @author liujing01
 * @version ChannelBufferByteOutput.java, v 0.1 2018-09-24 21:44 
 */
public class ChannelBufferByteInput implements ByteInput {
    private final ByteBuf buffer;

    public ChannelBufferByteInput(ByteBuf buffer){
        this.buffer = buffer;
    }





    ByteBuf getBuffer() {
        return buffer;
    }


    @Override
    public int read() throws IOException {
        if (buffer.isReadable()) {
            return buffer.readByte() & 0xff;
        }
        return -1;
    }

    @Override
    public int read(byte[] bytes) throws IOException {
        return read(bytes, 0, bytes.length);
    }

    @Override
    public int read(byte[] dst, int dstIndex, int length) throws IOException {
        int available = available();
        if (available == 0) {
            return -1;
        }

        length = Math.min(available, length);
        buffer.readBytes(dst, dstIndex, length);
        return length;
    }

    @Override
    public int available() throws IOException {
        return buffer.readableBytes();
    }

    @Override
    public long skip(long bytes) throws IOException {
        int readable = buffer.readableBytes();
        if (readable < bytes) {
            bytes = readable;
        }
        buffer.readerIndex((int) (buffer.readerIndex() + bytes));
        return bytes;
    }

    @Override
    public void close() throws IOException {

    }
}
