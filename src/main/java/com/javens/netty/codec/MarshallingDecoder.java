/**
 * fshows.com
 * Copyright (C) 2013-2018 All Rights Reserved.
 */
package com.javens.netty.codec;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

/**
 *
 * @author liujing01
 * @version MarshallingDecoder.java, v 0.1 2018-09-24 22:01 
 */
public class MarshallingDecoder {
    private final Unmarshaller unmarshaller;

    public MarshallingDecoder() throws IOException{
        unmarshaller = MarshallingCodecFactory.buildUnMarshalling();
    }

    protected Object decode(ByteBuf in) throws Exception {
        try {
            //1 首先读取4个长度(实际body内容长度)
            int bodySize = in.readInt();
            //2 获取实际body的缓冲内容
            int readIndex = in.readerIndex();
            ByteBuf buf = in.slice(readIndex, bodySize);
            //3 转换
            ChannelBufferByteInput input = new ChannelBufferByteInput(buf);
            //4 读取操作:
            this.unmarshaller.start(input);
            Object ret = this.unmarshaller.readObject();
            this.unmarshaller.finish();
            //5 读取完毕以后, 更新当前读取起始位置:
            //因为使用slice方法，原buf的位置还在readIndex上，故需要将位置重新设置一下
            in.readerIndex(in.readerIndex() + bodySize);

            return ret;

        } finally {
            this.unmarshaller.close();
        }
    }
}
