package com.achi.tw.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Message
{
    @Getter
    @Setter
    private String content;

    @Getter
    @Setter
    private Integer id;

    public Message(String message)
    {
        this.content = message;
    }
}
