package com.tw.app.services;

import com.tw.app.entities.Answer;
import com.tw.app.entities.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDto
{
    private List<Answer> answers;
}
