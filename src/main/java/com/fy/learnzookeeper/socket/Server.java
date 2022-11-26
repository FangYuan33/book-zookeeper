package com.fy.learnzookeeper.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        final int DEFAULT_PORT = 8888;
        final String QUIT = "quit";
        ServerSocket serverSocket = null;

        try {
            // 绑定端口号
            serverSocket = new ServerSocket(DEFAULT_PORT);
            System.out.println("服务器已经启动，绑定端口号：" + DEFAULT_PORT);

            while (true) {
                // 等待客户端的连接
                Socket socket = serverSocket.accept();
                System.out.println("客户端" + socket.getPort() + ": " + "已经连接");

                // 获取io流
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                // 读取客户发送的信息
                String msg;
                while ((msg = reader.readLine()) != null) {
                    System.out.println("客户端" + socket.getPort() + ": " + msg);
                    // 回复消息
                    writer.write(msg + " ok" + "\n");
                    writer.flush();
                    System.out.println("服务器：" + msg + " ok");

                    if (msg.equals(QUIT)) {
                        System.out.println("客户端" + socket.getPort() + ": 断开连接");
                        break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                    System.out.println("服务器Socket关闭");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


