package com.tw.app.services;

import com.tw.app.messages.Message;
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
        Message response = new Message(message);
        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(final String id, final Message message)
    {
        messagingTemplate.convertAndSendToUser(id, "/topic/private-messages", message);
    }

    public void notifyUser(final Integer id, final Message message)
    {
        notifyUser(id.toString(), message);
    }
}
