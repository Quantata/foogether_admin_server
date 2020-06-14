package foogether.admin.web.controller;

import foogether.admin.domain.Entity.Question;
import foogether.admin.service.QuestionService;
import foogether.admin.web.dto.DefaultResponse;
import foogether.admin.web.dto.NoticeResponseDto;
import foogether.admin.web.dto.QuestionRequestDto;
import foogether.admin.web.dto.QuestionResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static foogether.admin.web.dto.DefaultResponse.FAIL_DEFAULT_RES;

@Slf4j
@RestController
@RequestMapping("/questions")
// 문의사항
public class QuestionController {

    @Autowired
    QuestionService questionService;


    /* 문의사항 작성 */
    @PostMapping("")
    public ResponseEntity postQuestions(
            @RequestHeader("Authorization") String header,
            @RequestBody QuestionRequestDto questionRequestDto
            ){

        DefaultResponse defaultResponse;
        try{
            defaultResponse = questionService.postQuestion(questionRequestDto, header);
            return new ResponseEntity<>(defaultResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* 문의사항 답변 작성 - 상태도 변경 해주기 */
    @PostMapping("/{questionIdx}/complete")
    public ResponseEntity completeQuestions(
            @RequestHeader("Authorization") String header,
            @PathVariable("questionIdx") int questionIdx
    ){
        DefaultResponse<List<QuestionResponseDto>> defaultResponse;
        try{
            defaultResponse = questionService.completeQuestion(questionIdx, header);
            return new ResponseEntity<>(defaultResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* 문의사항 조회 */
    /* TODO: Front test 끝나면 Auth 추가 */
    @GetMapping("")
    public ResponseEntity findAll(){
        DefaultResponse<List<QuestionResponseDto>> defaultResponse;
        try{
            defaultResponse = questionService.findAll();
            return new ResponseEntity<>(defaultResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
