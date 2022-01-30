package com.tw.app.controllers;

import com.tw.app.messages.Message;
import com.tw.app.services.SocketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SocketsController
{
    private final SocketsService service;

    @Autowired
    public SocketsController(SocketsService service)
    {
        this.service = service;
    }

    @PostMapping("/send-message")
    public String sendMessage(@RequestBody Message message)
    {
        service.notifyFrontend(message.getContent());
        return "success!";
    }

    @PostMapping("/send-private-message/{id}")
    public String sendPrivateMessage(@PathVariable final String id, @RequestBody Message message)
    {
        service.notifyUser(id, message);
        return "success!";
    }
}
