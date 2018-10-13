package com.javens.netty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void testByte(){
        int b = 9999;
        String s = Integer.toBinaryString(b & 0xFF);
        System.out.println(s);
    }


    @Test
    public void testFramesDecoded(){
        ByteBuf buf = Unpooled.buffer();
        for(int i=0;i<9;i++){
            buf.writeByte(i);
        }

        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        assertTrue(channel.writeInbound(input.retain()));
        assertTrue(channel.finish());

        //read message
        ByteBuf read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3),read);
        read.release();


        read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3),read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3),read);
        read.release();

        assertNull(channel.readInbound());
        buf.release();



    }
}
