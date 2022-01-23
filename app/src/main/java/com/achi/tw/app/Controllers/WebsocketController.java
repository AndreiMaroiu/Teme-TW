package com.achi.tw.app.Controllers;

import com.achi.tw.app.Dto.Message;
import com.achi.tw.app.Services.WsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebsocketController
{
    private final WsService service;

    @Autowired
    public WebsocketController(WsService service)
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
        service.notifyUser(id ,message.getContent());
        return "success!";
    }
}
