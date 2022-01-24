package com.achi.tw.app.controllers;

import com.achi.tw.app.dto.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageController
{
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Message getMessage(Message message)
    {
        return message;
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public Message getPrivateMessage(Message message, final Principal principal)
    {
        return message;
    }
}
