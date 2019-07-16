package com.itqf.one;

import com.itqf.utils.ConnUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class OneSend {
    public static void main(String[] args) throws IOException, TimeoutException {

        Connection conn = ConnUtil.getConn();
        System.out.println(conn);

        Channel channel = conn.createChannel();

        channel.queueDeclare("que01",false,false,false,null);

        channel.basicPublish("","que01",null,"hello2".getBytes());

        channel.close();

        conn.close();
    }
}
