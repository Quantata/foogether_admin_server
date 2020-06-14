package foogether.admin.web.dto;

import foogether.admin.domain.Entity.Question;
import foogether.admin.domain.QuestionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class QuestionRequestDto {
    int idx;
    String emailAddr;
    String category;
    String content;
    QuestionStatus questionStatus;
    LocalDateTime createdDate;


    public QuestionRequestDto(Question entity){
        this.idx = entity.getIdx();
        this.emailAddr = entity.getEmailAddr();
        this.category = entity.getCategory();
        this.questionStatus = entity.getQuestionStatus();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedDate();
    }


    public Question toEntity() {
        return Question.builder()
                .idx(this.idx)
                .category(this.category)
                .content(this.content)
                .emailAddr(this.emailAddr)
                .questionStatus(this.questionStatus)
                .build();
    }

}
