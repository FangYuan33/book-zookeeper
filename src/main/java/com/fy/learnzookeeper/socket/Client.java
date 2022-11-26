package com.fy.learnzookeeper.socket;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        final int DEFAULT_SERVER_PORT = 8888;
        final String DEFAULT_ADDRESS = "127.0.0.1";
        final String QUIT = "quit";

        Socket socket;
        BufferedWriter writer = null;

        try {
            // 创建Socket
            socket = new Socket(DEFAULT_ADDRESS, DEFAULT_SERVER_PORT);

            // 创建io流
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 等待用户输入信息
            while (true) {
                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
                String msg = consoleReader.readLine();
                // 向服务器发送消息
                writer.write(msg + "\n");
                writer.flush();
                System.out.println("客户端" + ": " + msg);
                String line = reader.readLine();
                System.out.println("服务器：" + line);
                // 退出判断
                if (msg.equals(QUIT)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}


