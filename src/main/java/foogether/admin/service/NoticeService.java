package foogether.admin.service;

import foogether.admin.domain.Entity.Notice;
import foogether.admin.web.dto.DefaultResponse;
import foogether.admin.web.dto.NoticeRequestDto;
import foogether.admin.web.dto.NoticeResponseDto;

import java.util.List;

public interface NoticeService {
    /* 공지사항 삭제 */
    DefaultResponse deleteNotice(NoticeRequestDto noticeRequestDto, String header);

    /* 공지사항 수정 */
    DefaultResponse updateNotice(NoticeRequestDto noticeRequestDto, String header);

    /* 공지사항 작성 */
    DefaultResponse postNotice(NoticeRequestDto noticeRequestDto, String header);
    /* 모든 공지사항 조회 */
    DefaultResponse<List<NoticeResponseDto>> findAll();

}
