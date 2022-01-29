package com.tw.app.services;

import com.tw.app.entities.Answer;
import com.tw.app.entities.Question;
import com.tw.app.repositoties.AnswerRepository;
import com.tw.app.repositoties.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QuizService
{
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public QuizService(AnswerRepository answerRepository, QuestionRepository questionRepository)
    {
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    public Set<Question> getAllQuestion()
    {
        return new HashSet<>(getAllQuestionsList());
    }

    public List<Question> getAllQuestionsList()
    {
        return questionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
    }

    public Question getQuestion(Long id)
    {
        Optional<Question> conditional;

        conditional = questionRepository.findById(Objects.requireNonNullElse(id, 1L));

        return conditional.orElse(null);
    }

    public Answer getAnswer(Long id)
    {
        Optional<Answer> conditional = answerRepository.findById(id);
        return conditional.orElse(null);
    }

    public float getTotal(Map<Question, List<Long>> answers)
    {
        float procent = 10.0f / answers.size();
        float total = 0.0f;

        for (var pair : answers.entrySet())
        {
            boolean isCorrect = true;
            List<Long> submittedAnswers = pair.getValue();

            if (pair.getKey().correctAnswerCount() != submittedAnswers.size())
            {
                continue;
            }

            for (Long index : submittedAnswers)
            {
                Answer answer = getAnswer(index);

                if (!answer.isCorrect())
                {
                    isCorrect = false;
                    break;
                }
            }

            if (isCorrect)
            {
                total += procent;
            }
        }

        return total;
    }
}
