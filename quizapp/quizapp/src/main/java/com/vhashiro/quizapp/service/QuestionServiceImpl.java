package com.vhashiro.quizapp.service;

import com.sun.jdi.event.ExceptionEvent;
import com.vhashiro.quizapp.entity.Question;
import com.vhashiro.quizapp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    /** Uses questionRepository built-in method .findAll()
     *  to get all [Question objects] from Table in DB
     *
     *  returns
     *  ResponseEntity<>(Data, HttpStatus.OK)
     *  returns a ResponseEntity containing the <list<Questions>> + a HttpStatus code */
    @Override
    public ResponseEntity<List<Question>> getQuestionsList() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    /** Uses custom QuestionRepository method to get
     *  [Question objects] from Table in DB
     *  that match the categoryName  */
    @Override
    public ResponseEntity<List<Question>> getQuestionsListCategory(String categoryName) {
        try{
            return new ResponseEntity<>(questionRepository.findByQuestionCategory(categoryName), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    /** Uses questionRepository built-in method .save()
     *  to save a [Question object] to Table in DB  */
    @Override
    public ResponseEntity<String> addQuestion(Question question) {
        try{
            questionRepository.save(question);
            return new ResponseEntity<>("successful add", HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("failed add", HttpStatus.BAD_REQUEST);
    }

}
