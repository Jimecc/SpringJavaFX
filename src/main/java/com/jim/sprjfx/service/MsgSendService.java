package com.jim.sprjfx.service;


import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import io.netty.handler.codec.http.HttpHeaders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class MsgSendService {

    public Map<UUID, SocketIOClient> CLIENT_MAP = new ConcurrentHashMap<>();
    private final SocketIOServer socketIOServer;
    public boolean switchWebsocket = true;

    // 清空已有连接
    public void cleanMap(){
        log.info("---------------------------------------------------------------------");
        log.info("Websocket连接池状态是否为空---"+CLIENT_MAP.isEmpty());
        CLIENT_MAP.clear();
        log.info("Websocket连接池状态是否为空---"+CLIENT_MAP.isEmpty());
    }


    // 关闭 WebSocket 服务
    public void switchWebSocket(){
        CLIENT_MAP.clear();
        this.switchWebsocket = !this.switchWebsocket;
        log.info("switchWebsocket：--------"+switchWebsocket);
    }


    /**
     * 启动的时候会被调用一次
     */
    public void autoStart() {
        log.info("start WebSocket");
                socketIOServer.addConnectListener(client -> {
                    String token = getClientToken(client, "token");
                    if (checkToken(token)) {
                        log.info("check success");
                        if(switchWebsocket) {
                            CLIENT_MAP.put(client.getSessionId(), client);
                        }
                    } else {
                        client.disconnect();
                    }
                });
                socketIOServer.addDisconnectListener(client -> {
                    if(switchWebsocket) {
                        CLIENT_MAP.remove(client.getSessionId());
                    }
                    client.disconnect();
                    log.info("移除client:{}", client.getSessionId());
                });
            socketIOServer.start();
        log.info("start finish");
    }

    @PostConstruct
    public void start(){
        autoStart();
    }

    private boolean checkToken(String token) {
        log.info("检查token是否有效:{}", token);
        return true;
    }

    private String getClientToken(SocketIOClient client, String key) {
        HttpHeaders httpHeaders = client.getHandshakeData().getHttpHeaders();
        return httpHeaders.get(key);
    }

    @PreDestroy
    private void onDestroy() {
        if (socketIOServer != null) {
            socketIOServer.stop();
        }
    }

    public int sendMsg(Object demo) {
        CLIENT_MAP.forEach((key, value) -> {
            value.sendEvent("server_event", demo);
            log.info("发送数据成功:{}", key);
        });
        return CLIENT_MAP.size();
    }


}
