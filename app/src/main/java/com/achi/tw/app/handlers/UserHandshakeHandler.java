package com.achi.tw.app.handlers;

import com.achi.tw.app.utils.SecurityUtils;
import com.sun.security.auth.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.security.Principal;
import java.util.Map;

public class UserHandshakeHandler extends DefaultHandshakeHandler
{
    private final Logger LOG = LoggerFactory.getLogger(UserHandshakeHandler.class);

    @Override
    protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes)
    {
        String username = SecurityUtils.getUser().getId().toString();

        LOG.info("User with ID '{}' opened the page", username);

        return new UserPrincipal(username);
    }
}
