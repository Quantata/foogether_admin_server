package foogether.admin.web.dto;

import foogether.admin.domain.Gender;
import foogether.admin.domain.UserStatus;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UserResponseDto {
    // user 고유번호
    int idx;
    // 이메일
    String emailAddr;
    // 이름
    String name;
    // 전화 번호
    String phoneNum;
    // 닉네임
    String nickname;
    // 프로필 이미지
    String profileImg;
    // 성별
    Gender gender;
    // 상태
    UserStatus userStatus;


    public UserResponseDto(foogether.admin.web.dto.UserResponseDto data) {
        this.idx = data.getIdx();
//        this.emailAddr = data.getEmailAddr();
//        this.name = data.getName();
//        this.phoneNum = data.getPhoneNum();
        this.nickname = data.getNickname();
        this.profileImg = data.getProfileImg();
        this.gender = data.getGender();
        this.userStatus = data.getUserStatus();
    }
}
