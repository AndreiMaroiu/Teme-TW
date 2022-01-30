package com.tw.app.controllers;

import com.tw.app.entities.Question;
import com.tw.app.services.QuizService;
import com.tw.app.services.SessionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@RestController
public class MainController
{
    private final QuizService quizService;
    private final SessionModel sessionModel;

    @Autowired
    public MainController(QuizService quizService, SessionModel sessionModel)
    {
        this.quizService = quizService;
        this.sessionModel = sessionModel;
    }

    @GetMapping("/")
    public ModelAndView main()
    {
        sessionModel.clear();
        return new ModelAndView("index");
    }

    @GetMapping("/final")
    public ModelAndView result(Model model)
    {
        model.addAttribute("total", quizService.getTotal(sessionModel.getAnswers()));
        sessionModel.clear();
        return new ModelAndView("final");
    }

    @GetMapping("/quiz")
    public ModelAndView quiz(Model model, HttpSession session, @RequestParam(required = false) Long questionId)
    {
        model.addAttribute("questions", quizService.getAllQuestion());

        // if the service cannot find question that means the user finished the quiz
        if (questionId != null && quizService.getQuestion(questionId) == null)
        {
            return new ModelAndView("redirect:/final");
        }

        Question question = quizService.getQuestion(questionId);
        model.addAttribute("question", question);

        // check if question is already answered
        if (sessionModel.getAnswered().contains(question.getId()))
        {
            model.addAttribute("answered", true);
        }

        return new ModelAndView("quiz");
    }

    /**
     *
     * @param allParams contains a key that contains the name of an answer and the value contains the id of the answer
     *                  comes from the html form
     * @return redirects to get request /quiz with param questionId
     */
    @PostMapping("/submit")
    public ModelAndView submit(@RequestParam Map<String, String> allParams)
    {
        Long questionId = Long.parseLong(allParams.get("questionId"));
        List<Long> answers = new ArrayList<>();

        for (var pair : allParams.entrySet())
        {
            // ignore question id because is already used
            if (pair.getKey().equals("questionId"))
            {
                continue;
            }

            answers.add(Long.parseLong(pair.getValue()));
        }

        Question question = quizService.getQuestion(questionId);
        // add question to session model, if not already in
        if (!sessionModel.getAnswers().containsKey(question))
        {
            sessionModel.getAnswered().add(questionId);
            sessionModel.getAnswers().put(question, answers);
        }

        // modify parameter to: ("redirect:/quiz?questionId=" + (questionId + 1)) to navigate to next question
        return new ModelAndView("redirect:/quiz?questionId=" + questionId);
    }
}
