package com.itqf.one;

import com.itqf.utils.ConnUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class OneRecv {

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection conn = ConnUtil.getConn();
        final Channel channel = conn.createChannel();
        channel.queueDeclare("que01",false,false,false,null);
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("消费者消费的消息是"+msg);
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume("que01",false,consumer);

    }
}
