package foogether.admin.service;

import foogether.admin.domain.Entity.Question;
import foogether.admin.repository.QuestionRepository;
import foogether.admin.utils.ResponseMessage;
import foogether.admin.web.dto.DefaultResponse;
import foogether.admin.web.dto.NoticeResponseDto;
import foogether.admin.web.dto.QuestionResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    /* 문의사항 수정 */

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
