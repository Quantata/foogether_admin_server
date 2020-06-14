package foogether.admin.web.dto;

import foogether.admin.domain.Entity.Notice;
import foogether.admin.domain.Entity.Question;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class QuestionRequestDto {
//    int idx;
    String emailAddr;
    String category;
    String content;
//    LocalDateTime createdTime;


    public Question toEntity() {
        return Question.builder()
                .category(this.category)
                .content(this.content)
                .emailAddr(this.emailAddr)
                .build();
    }
}
