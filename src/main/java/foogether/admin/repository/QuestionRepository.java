package foogether.admin.repository;

import foogether.admin.domain.Entity.Notice;
import foogether.admin.domain.Entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    Question findByIdx(int questionIdx);
//    void deleteByIdx(int questionIdx);
}
