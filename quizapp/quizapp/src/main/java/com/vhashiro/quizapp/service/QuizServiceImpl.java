package com.vhashiro.quizapp.service;

import com.vhashiro.quizapp.entity.Question;
import com.vhashiro.quizapp.entity.QuestionWrapper;
import com.vhashiro.quizapp.entity.Quiz;
import com.vhashiro.quizapp.entity.UserResponse;
import com.vhashiro.quizapp.repository.QuestionRepository;
import com.vhashiro.quizapp.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    /** Get [Questions List] from [DataBase]
     *  Store them in [Quiz object]
     *  Save new [Quiz object] to [Quiz DataBase] */
    @Override
    public ResponseEntity<String> createQuiz(String questionCategory, int numQuestions, String title) {

        List<Question> questionList = questionRepository.
                findRandomQuestionsByQuestionCategory(questionCategory, numQuestions);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionsList(questionList);

        quizRepository.save(quiz);

        return new ResponseEntity<>("Created new Quiz", HttpStatus.CREATED);
    }

    /** Find a [Quiz] via ID
     *  Get [Question objects list] from [Quiz object]
     *  Store necessary [Question values] in [QuestionWrapper variables]
     *  Return [QuestionWrapper list] */
    @Override
    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id) {

        //Optional in case no matching ID is found
        //Get Quiz Object from DB
        Optional<Quiz> quiz = quizRepository.findById(id);

        //Get List<Question> from Quiz Object
        List<Question> questionsFromDB = quiz.get().getQuestionsList();
        List<QuestionWrapper> questionForUser = new ArrayList<>();//List -extra info

        //Convert every Question to QuestionWrapper
        //Add wrapped Question to questionForUser List
        for (Question question : questionsFromDB) {
            QuestionWrapper questionWrapper = new QuestionWrapper(
                    question.getQuestionID(),
                    question.getQuestionName(),
                    question.getOption1(),
                    question.getOption2(),
                    question.getOption3(),
                    question.getOption4());

            questionForUser.add(questionWrapper);
        }

        return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }

    /**
     * Find a [Quiz] via ID
     * Get [Question objects list] from [Quiz object]
     * Compare [rightAnswer] of [Question objects] to [userResponse selectedOptions]
     */
    @Override
    public ResponseEntity<String> calcResult(Integer id, List<UserResponse> responseList) {
        Optional<Quiz> quizFromDB = quizRepository.findById(id);
        List<Question> questionList = quizFromDB.get().getQuestionsList();
        int score = 0;
        int currQuestion = 0;

        for (UserResponse response : responseList){
            String rightAnswer = questionList.get(currQuestion).getRightAnswer();
            String selectedOption = response.getSelectedOption();

            if (selectedOption.equals(rightAnswer)) score++;

            currQuestion++;
        }

        String result = "Score is: " + score + " out of " + questionList.size();

        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
