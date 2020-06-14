package foogether.admin.repository;

import foogether.admin.domain.Entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    void deleteByIdx(int noticeIdx);
}
