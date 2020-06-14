package foogether.admin.service;

import foogether.admin.web.dto.DefaultResponse;
import foogether.admin.web.dto.NoticeResponseDto;
import foogether.admin.web.dto.QuestionResponseDto;

import java.util.List;

public interface QuestionService {
    DefaultResponse<List<QuestionResponseDto>> findAll();

}
