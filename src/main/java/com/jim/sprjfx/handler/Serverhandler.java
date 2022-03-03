package com.jim.sprjfx.handler;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;


@Slf4j
public class Serverhandler {

    public static void server() throws IOException{
        ServerSocket server = new ServerSocket(2000);
        log.info("Socket 服务器准备就绪");
        log.info("服务端信息："+server.getInetAddress()+"\tP:"+server.getLocalPort());

        for(;;){
            // 得到客户端
         Socket client = server.accept();
         // 客户端异步线程
         ClientHandler clientHandler = new ClientHandler(client);
         // 线程启动
         clientHandler.start();
        }


    }

    public static class ClientHandler extends Thread{
        private Socket socket;
        private boolean flag = true;
        public ClientHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run(){
            super.run();
            log.info("新的客户端连接"+socket.getInetAddress()+"P:"+socket.getPort());

            try{
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                do{
                    String str = socketInput.readLine();
                    if("bye".equalsIgnoreCase(str)){
                        flag = false;
                        socketOutput.println("bye");
                    }else{
                        System.out.println(str);
                        System.out.println("回送"+str.length());
                    }
                }while(flag);

                socketInput.close();
                socketOutput.close();
            }catch (Exception e){
                log.error(String.valueOf(e));
            }finally {
                // 连接关闭
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                log.info("客户端已关闭");
            }
        }

    }


}
