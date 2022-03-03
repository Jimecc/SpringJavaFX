package com.jim.sprjfx.service;

import com.jim.sprjfx.handler.Serverhandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
@RestController
public class SocketThread {

    public boolean a = true;

    ServerSocket server = new ServerSocket(2222);

    public SocketThread() throws IOException {
    }

//    private boolean serverFlag = true;
    public void test() throws IOException {
        try {
            server.close();
        }catch (Exception e){
            System.out.println();
        }
    }

    public void NNNew() throws IOException {
        this.server = new ServerSocket(2222);
    }

    @Async("socketthreadPool")
    public void startSocket() throws IOException, InterruptedException {
        if(this.server.isClosed() || a){
            if(!a) {
                this.server = new ServerSocket(2222);
            }
            this.a = false;
            log.info("Socket 服务器准备就绪");
            log.info("服务端信息："+server.getInetAddress()+"\tP:"+server.getLocalPort());

            while(true){
                // 得到客户端
                Socket client = server.accept();
                // 客户端异步线程
                SocketThread.ClientHandler clientHandler = new SocketThread.ClientHandler(client);
                // 线程启动
                clientHandler.start();
            }
        }else{
            this.server.close();
            log.info("服务器成功关闭Success");
            return;
        }
    }


    public static class ClientHandler extends Thread{
        private Socket socket;
        private boolean flag = true;
        ClientHandler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run(){
            super.run();
            log.info("新的客户端连接：\t"+socket.getInetAddress()+"\tP:\t"+socket.getPort()+"\t本机IP：\t"+socket.getLocalAddress()+"\tP:\t"+socket.getLocalPort());

            try{
                PrintStream socketOutput = new PrintStream(socket.getOutputStream());
                BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                do{
                    String str = socketInput.readLine();
                    if("bye".equalsIgnoreCase(str)){
                        flag = false;
                        socketOutput.println("bye");
                    }else{
                        log.info("回送：\t"+str.length()+"\tMessage:\t"+str);
                    }
                }while(flag);

                socketInput.close();
                socketOutput.close();
            }catch (Exception e){
                log.error("Error link");
//                log.error(String.valueOf(e));
            }finally {
                // 连接关闭
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                log.info("客户端已关闭\t"+socket.getInetAddress());
            }
        }

    }


}
