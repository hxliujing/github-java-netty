package com.javens.netty;

import com.javens.netty.entity.Order;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class ProtoBufTest {

    @Test
    public void test1() throws Exception{
        Order order = new Order(1,"Liujing","Netty Book","151****4085","中国杭州");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(order);
        os.flush();

        System.out.println("JDK序列化后的长度："+ out.toByteArray().length);
        os.close();
        out.close();


        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put(String.valueOf(order.getId()).getBytes());
        buffer.put(order.getAuthor().getBytes());
        buffer.put(order.getName().getBytes());
        buffer.put(order.getMobile().getBytes());
        buffer.put(order.getAddress().getBytes());

        buffer.flip();

        byte[] result = new byte[buffer.remaining()];

        buffer.get(result);
        System.out.println("使用二进制序列化的长度："+ result.length);

    }
}
