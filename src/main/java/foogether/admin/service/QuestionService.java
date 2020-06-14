package foogether.admin.service;

import foogether.admin.web.dto.DefaultResponse;
import foogether.admin.web.dto.NoticeResponseDto;
import foogether.admin.web.dto.QuestionRequestDto;
import foogether.admin.web.dto.QuestionResponseDto;

import java.util.List;

public interface QuestionService {
    /* 문의사항 작성 */
    DefaultResponse postQuestion(QuestionRequestDto questionRequestDto, String header);

    /* 문의사항 답변 */
    DefaultResponse completeQuestion(int questionIdx, String header);
    /* 문의사항 조회 */
    DefaultResponse<List<QuestionResponseDto>> findAll();

}
