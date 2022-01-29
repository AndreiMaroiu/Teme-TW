package com.tw.app.repositoties;

import com.tw.app.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long>
{
}
