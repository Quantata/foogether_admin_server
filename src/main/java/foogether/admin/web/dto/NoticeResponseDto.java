package foogether.admin.web.dto;

import foogether.admin.domain.Entity.Notice;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoticeResponseDto {
    int idx;
//    String nickname;
    String title;
    String content;
    LocalDateTime createdTime;

    public NoticeResponseDto(Notice entity){
        this.idx = entity.getIdx();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdTime = entity.getCreatedDate();
    }

}
