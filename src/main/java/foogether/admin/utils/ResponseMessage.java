package foogether.admin.utils;

public class ResponseMessage {
    /**
     * default
     */
    public static final String AUTHORIZED = "인증 성공";
    public static final String UNAUTHORIZED = "인증된 사용자가 아닙니다.";
    public static final String FORBIDDEN = "인가 실패";

    public static final String INTERNAL_SERVER_ERROR = "서버 내부 에러";
    public static final String SERVICE_UNAVAILABLE = "현재 서비스를 사용하실 수 없습니다. 잠시후 다시 시도해 주세요.";

    public static final String DB_ERROR = "데이터베이스 에러";


    public static final String BAD_PARAMETER = "파라미터 값이 잘못되었습니다.";
    public static final String NOT_FOUND_QUESTIONS = "작성된 문의사항이 없습니다.";
    public static final String FIND_ALL_QUESTIONS = "문의사항 조회 완료";


    public static final String NOT_FOUND_NOTICES = "작성된 공지사항이 없습니다.";
    public static final String FIND_ALL_NOTICES = "공지사항 조회 완료";
    public static final String SAVE_NOTICES = "공지사항 작성 완료";
    public static final String UPDATE_NOTICES = "공지사항 수정 완료";
    public static final String DELETE_NOTICES = "공지사항 삭제 완료";


}