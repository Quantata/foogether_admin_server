package foogether.admin.domain.Entity;

import foogether.admin.domain.QuestionStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="question")
@NoArgsConstructor
@Getter
@DynamicInsert // (insert 시 null 인필드 제외)
@DynamicUpdate // (update 시 null 인필드 제외)
public class Question {
    // question 고유번호
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_idx")
    int idx;

    // 생성 시간
    @Column(name = "question_createdDate")
    LocalDateTime createdDate = LocalDateTime.now();

    // 카테고리
    @Column(name = "question_catetory")
    String category;

    // 이메일
    @Column(name = "question_emailAddr")
    String emailAddr;

    // 상태
    @Enumerated(EnumType.STRING)
    @Column(name = "question_status")
    QuestionStatus questionStatus;

    // 내용
    @Column(name = "question_content")
    String content;


    @PrePersist
    public void prePersist() {
        this.questionStatus = this.questionStatus == null ? QuestionStatus.NONCOMPLETE : this.questionStatus;
    }

//    @PreUpdate
//    public void preUpdate() {
//        this.questionStatus = this.questionStatus == null ? QuestionStatus.NONCOMPLETE : this.questionStatus;
//    }

    @Builder
    public Question(int idx, String category, QuestionStatus questionStatus, String content
    , String emailAddr) {
        this.idx = idx;
        this.category = category;
        this.questionStatus= questionStatus;
        this.content = content;
        this.emailAddr = emailAddr;
    }
}
