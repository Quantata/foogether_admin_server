package foogether.admin.service;

import foogether.admin.client.UserClient;
import foogether.admin.domain.Entity.Notice;
import foogether.admin.domain.UserStatus;
import foogether.admin.repository.NoticeRepository;
import foogether.admin.utils.ResponseMessage;
import foogether.admin.web.dto.DefaultResponse;
import foogether.admin.web.dto.NoticeRequestDto;
import foogether.admin.web.dto.NoticeResponseDto;
import foogether.admin.web.dto.UserResponseDto;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    NoticeRepository noticeRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserClient userClient;

    /* 공지사항 삭제 */
    @Transactional
    @Override
    public DefaultResponse deleteNotice(NoticeRequestDto noticeRequestDto, String header) {
        int isAdmin = jwtService.decode(header).getUserIdx();
        DefaultResponse<UserResponseDto>
                user = userClient.getUserInfo(isAdmin).getBody();

        UserResponseDto myDto = new UserResponseDto(user.getData());

        if(myDto.getUserStatus().equals(UserStatus.ADMIN)) {
            if(noticeRequestDto.getIdx() != 0 ) {  // 삭제하기 위해선 idx 있어야 함.
//                noticeRepository.delete(noticeRequestDto.toEntity());
                noticeRepository.deleteByIdx(noticeRequestDto.getIdx());
                return DefaultResponse.res("success", ResponseMessage.DELETE_NOTICES);
            }
            else {
                return DefaultResponse.res("fail", ResponseMessage.BAD_PARAMETER);
            }
        }

        return  DefaultResponse.res("fail", ResponseMessage.UNAUTHORIZED);
    }

    /* 공지사항 수정 */
    @Transactional
    @Override
    public DefaultResponse updateNotice(NoticeRequestDto noticeRequestDto, String header) {
        int isAdmin = jwtService.decode(header).getUserIdx();
        DefaultResponse<UserResponseDto>
                user = userClient.getUserInfo(isAdmin).getBody();

        UserResponseDto myDto = new UserResponseDto(user.getData());

        if(myDto.getUserStatus().equals(UserStatus.ADMIN)) {
            if(noticeRequestDto.getIdx() != 0 ) {  // 수정하기 위해선 idx 있어야 함.
                noticeRepository.save(noticeRequestDto.toEntity());
                return DefaultResponse.res("success", ResponseMessage.UPDATE_NOTICES);
            } else {    // 잘못된 파라미터
                return DefaultResponse.res("fail", ResponseMessage.BAD_PARAMETER);
            }
        }

        return  DefaultResponse.res("fail", ResponseMessage.UNAUTHORIZED);
    }

    /* 공지사항 작성 */
    @Transactional
    @Override
    public DefaultResponse postNotice(NoticeRequestDto noticeRequestDto, String header) {
        int isAdmin = jwtService.decode(header).getUserIdx();
        DefaultResponse<UserResponseDto>
                user = userClient.getUserInfo(isAdmin).getBody();

        UserResponseDto myDto = new UserResponseDto(user.getData());

        if(myDto.getUserStatus().equals(UserStatus.ADMIN)) {
            if(noticeRequestDto.getIdx() != 0 ) {  // 잘못된 파라미너
                return DefaultResponse.res("fail", ResponseMessage.BAD_PARAMETER);
            } else {    // 저장
                noticeRepository.save(noticeRequestDto.toEntity());
                return DefaultResponse.res("success", ResponseMessage.SAVE_NOTICES);
            }
        }

        return  DefaultResponse.res("fail", ResponseMessage.UNAUTHORIZED);
    }

    /* 공지사항 조회 */
    @Override
    public DefaultResponse<List<NoticeResponseDto>> findAll() {
        List<Notice> notices = noticeRepository.findAll();

        if(notices.size() == 0) {
            return DefaultResponse.res("success", ResponseMessage.NOT_FOUND_NOTICES);
        }

        List<NoticeResponseDto> noticeResponseDtos = new ArrayList<>();
        for(Notice notice : notices){
            noticeResponseDtos.add(new NoticeResponseDto(notice));
        }

        return DefaultResponse.res("success", ResponseMessage.FIND_ALL_NOTICES,
                noticeResponseDtos);
    }
}
