package com.jim.sprjfx.handler;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Client {
    public void socketClient() throws IOException{
        Socket socket = new Socket();
        socket.setSoTimeout(3000);
        socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(),2000),3000);

        System.out.println("已经发起请求");
        System.out.println("客户端信息："+socket.getLocalAddress()+"P:"+socket.getLocalPort());
        System.out.println("服务端信息："+socket.getInetAddress()+"P:"+socket.getPort());
        try{
            todo(socket);
        }catch (Exception e){
            System.out.println("异常关闭");
        }

        socket.close();
        System.out.println("客户端退出");
    }

    private static void todo(Socket client) throws IOException{
        InputStream in = System.in;
        BufferedReader input = new BufferedReader(new InputStreamReader(in));

        // 得到 socket 输出流，并转化为打印流
        OutputStream out = client.getOutputStream();
        PrintStream socketPrintStream = new PrintStream(out);

        // 得到 socket 输入流，并转化为 BufferReader 流
        InputStream inputStream = client.getInputStream();
        BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));


        boolean flag = true;
        do{
            // 键盘读取一行
            String str = input.readLine();
            // 发送到服务器
            socketPrintStream.println(str);
            String echo = socketBufferedReader.readLine();
            if("bye".equalsIgnoreCase(echo)){
                flag = false;
            }else{
                System.out.println(echo);
            }
        }while(flag);

        socketPrintStream.close();
        socketBufferedReader.close();

    }
}
