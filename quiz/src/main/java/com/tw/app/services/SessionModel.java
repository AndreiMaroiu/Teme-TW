package com.tw.app.services;

import com.tw.app.entities.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Setter
@Getter
public class SessionModel
{
    private Map<Question, List<Long>> answers = new HashMap<>();

    private Set<Long> answered = new HashSet<>();

    public void clear()
    {
        answers.clear();
        answered.clear();
    }
}
