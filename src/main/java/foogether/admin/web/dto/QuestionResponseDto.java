package foogether.admin.web.dto;

import foogether.admin.domain.Entity.Notice;
import foogether.admin.domain.Entity.Question;
import foogether.admin.domain.QuestionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class QuestionResponseDto {
    int idx;
    String category;
    QuestionStatus questionStatus;
    String content;
    LocalDateTime createdDate;


    public QuestionResponseDto(Question entity){
        this.idx = entity.getIdx();
        this.category = entity.getCategory();
        this.questionStatus = entity.getQuestionStatus();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
    }


    @Builder
    public QuestionResponseDto(int idx, String category, QuestionStatus questionStatus, String content, LocalDateTime createdDate) {
        this.idx = idx;
        this.category = category;
        this.questionStatus = questionStatus;
        this.content = content;
        this.createdDate = createdDate;
    }


}
