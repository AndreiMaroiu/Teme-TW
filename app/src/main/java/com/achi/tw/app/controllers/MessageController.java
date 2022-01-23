package com.achi.tw.app.controllers;

import com.achi.tw.app.dto.Message;
import com.achi.tw.app.dto.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.security.Principal;

@Controller
public class MessageController
{
    @MessageMapping("/message")
    @SendTo("/topic/messages")
    public Response getMessage(Message message)
    {
        Response response = new Response(HtmlUtils.htmlEscape(message.getContent()));
        return response;
    }

    @MessageMapping("/private-message")
    @SendToUser("/topic/private-messages")
    public Response getPrivateMessage(Message message, final Principal principal)
    {
        Response response = new Response(HtmlUtils.htmlEscape(message.getContent()));
        return response;
    }
}
