package foogether.admin.service;

import foogether.admin.client.UserClient;
import foogether.admin.domain.Entity.Question;
import foogether.admin.domain.QuestionStatus;
import foogether.admin.domain.UserStatus;
import foogether.admin.repository.QuestionRepository;
import foogether.admin.utils.ResponseMessage;
import foogether.admin.web.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserClient userClient;

    /* 문의사항 작성 */
    @Transactional
    @Override
    public DefaultResponse postQuestion(QuestionRequestDto questionRequestDto, String header) {
        if(jwtService.decode(header).getUserIdx() != -1){
            questionRepository.save(questionRequestDto.toEntity());
            return DefaultResponse.res("success", ResponseMessage.SAVE_QUESTIONS);
        }
        else {
            return DefaultResponse.res("fail", ResponseMessage.UNAUTHORIZED);
        }
    }


    /* 문의사항 답변완료 */
    @Transactional
    @Override
    public DefaultResponse completeQuestion(int questionIdx, String header) {
        Question question = questionRepository.findByIdx(questionIdx);
        if(question == null || question.getQuestionStatus().equals(QuestionStatus.COMPLETE)){
            return DefaultResponse.res("fail", ResponseMessage.INTERNAL_SERVER_ERROR);
        }
        int isAdmin = jwtService.decode(header).getUserIdx();
        DefaultResponse<UserResponseDto>
                user = userClient.getUserInfo(isAdmin).getBody();

        UserResponseDto myDto = new UserResponseDto(user.getData());

        if(myDto.getUserStatus().equals(UserStatus.ADMIN)){
            QuestionRequestDto questionRequestDto = new QuestionRequestDto(question);
            questionRequestDto.setQuestionStatus(QuestionStatus.COMPLETE);

            questionRepository.saveAndFlush(questionRequestDto.toEntity());
            return DefaultResponse.res("success", ResponseMessage.QUESTION_COMPLETE);
        } else {
            return DefaultResponse.res("fail", ResponseMessage.UNAUTHORIZED);
        }
    }

    /* 문의사항 조회 */
    @Override
    public DefaultResponse<List<QuestionResponseDto>> findAll() {
        List<Question> questions = questionRepository.findAll();
        if(questions.size() == 0){
            return DefaultResponse.res("success", ResponseMessage.NOT_FOUND_QUESTIONS);
        }

        List<QuestionResponseDto> questionResponseDtos = new ArrayList<>();

        for(Question question : questions){
            questionResponseDtos.add(new QuestionResponseDto(question));
        }

        return DefaultResponse.res("success", ResponseMessage.FIND_ALL_QUESTIONS, questionResponseDtos);
    }
}
