package foogether.admin.domain.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="notice")
@NoArgsConstructor
@Getter
@DynamicInsert // (insert 시 null 인필드 제외)
@DynamicUpdate // (update 시 null 인필드 제외)
public class Notice {
    // notice 고유번호
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "notice_idx")
    int idx;

    // 생성 시간
    @Column(name = "notice_createdDate")
    LocalDateTime createdDate = LocalDateTime.now();

    // 제목
    @Column(name = "notice_title")
    String title;

    // 내용
    @Column(name = "notice_content")
    String content;

    @Builder
    public Notice(int idx, String title, String content) {
        this.idx = idx;
        this.title = title;
        this.content = content;
    }
}
