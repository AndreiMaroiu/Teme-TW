package com.achi.tw.app.services;

import com.achi.tw.app.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class SocketsService
{
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public SocketsService(SimpMessagingTemplate messagingTemplate)
    {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyFrontend(final String message)
    {
        Response response = new Response(message);
        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(final String id, final String message)
    {
        Response response = new Response(message);

        messagingTemplate.convertAndSendToUser(id, "/topic/private-messages", response);
    }

    public void notifyUser(final Integer id, final String message)
    {
        notifyUser(id.toString(), message);
    }
}
