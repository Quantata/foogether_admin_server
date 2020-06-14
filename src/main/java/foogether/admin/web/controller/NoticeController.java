package foogether.admin.web.controller;

import com.sun.net.httpserver.HttpsConfigurator;
import foogether.admin.repository.NoticeRepository;
import foogether.admin.service.JwtService;
import foogether.admin.service.NoticeService;
import foogether.admin.utils.ResponseMessage;
import foogether.admin.web.dto.DefaultResponse;
import foogether.admin.web.dto.NoticeRequestDto;
import foogether.admin.web.dto.NoticeResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static foogether.admin.web.dto.DefaultResponse.FAIL_AUTHORIZATION_RES;
import static foogether.admin.web.dto.DefaultResponse.FAIL_DEFAULT_RES;


@Slf4j
@RestController
// 공지사항
public class NoticeController {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private NoticeService noticeService;

    /* 공지사항 삭제 */
    @DeleteMapping("/notices")
    public ResponseEntity deleteNotice(
            @RequestHeader("Authorization") String header,
            @RequestBody NoticeRequestDto noticeRequestDto
    ) {
        DefaultResponse defaultResponse;
        if(jwtService.decode(header).getUserIdx() != -1 ) {
            try {
                defaultResponse = noticeService.deleteNotice(noticeRequestDto, header);
                return new ResponseEntity<>(defaultResponse, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.UNAUTHORIZED);
        }
    }

    /* 공지사항 수정 */
    @PutMapping("/notices")
    public ResponseEntity updateNotice(
            @RequestHeader("Authorization") String header,
            @RequestBody NoticeRequestDto noticeRequestDto
    ) {
        DefaultResponse defaultResponse;
        if(jwtService.decode(header).getUserIdx() != -1 ) {
            try {
                defaultResponse = noticeService.updateNotice(noticeRequestDto, header);
                return new ResponseEntity<>(defaultResponse, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.UNAUTHORIZED);
        }
    }

    /* 공지사항 작성 */
    @PostMapping("/notices")
    public ResponseEntity postNotice(
            @RequestHeader("Authorization") String header,
            @RequestBody NoticeRequestDto noticeRequestDto
            ) {
        DefaultResponse defaultResponse;
        if(jwtService.decode(header).getUserIdx() != -1 ) {
            try {
                defaultResponse = noticeService.postNotice(noticeRequestDto, header);
                return new ResponseEntity<>(defaultResponse, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.UNAUTHORIZED);
        }
    }

    /* 공지사항 조회 */
    @GetMapping("/notices")
    public ResponseEntity findAll(){
        DefaultResponse<List<NoticeResponseDto>> defaultResponse;
        try{
            defaultResponse = noticeService.findAll();
            return new ResponseEntity<>(defaultResponse, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL_DEFAULT_RES, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
