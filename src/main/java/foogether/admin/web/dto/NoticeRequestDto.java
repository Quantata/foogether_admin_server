package foogether.admin.web.dto;

import foogether.admin.domain.Entity.Notice;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class NoticeRequestDto {
    int idx;
    //    String nickname;
    String title;
    String content;
    LocalDateTime createdTime;


    public Notice toEntity() {
        return Notice.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }

}
