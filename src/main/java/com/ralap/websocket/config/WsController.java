package com.ralap.websocket.config;

import com.ralap.websocket.domain.WiselyMessage;
import com.ralap.websocket.domain.WiselyResponse;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by ralap on 2017/11/11.
 */
@Controller
public class WsController {

    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public WiselyResponse say(WiselyMessage message) throws InterruptedException {
        Thread.sleep(3000);
        return new WiselyResponse("Welcome, " + message.getName() + "!");
    }


    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        if (principal.getName().equals("hjx")) {
            messagingTemplate
                    .convertAndSendToUser("zyt", "/queue/notifications",
                            principal.getName() + "-send:" + msg);
        } else {
            messagingTemplate
                    .convertAndSendToUser("hjx", "/queue/notifications",
                            principal.getName() + "-send:" + msg);

        }
    }
}
