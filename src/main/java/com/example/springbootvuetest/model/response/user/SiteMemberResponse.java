package com.example.springbootvuetest.model.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SiteMemberResponse {

    private int mbrseq;
    private int id;
    private String userType;
    private int tryLoginCnt;
    private String userId;
    private String userName;
    private String userPwd;
    private String userHp;
    private String userTel;
    private String dutyName;
    private String userEmail;
    private String userWithdraw;
    private String loginDateTime;
    private String role;
    private String smsReceiveYn;
    private String mailReceiveYn;
    private String puseYn;
    private String userTest1;
    private String userPwdChg;
    private String userBirth;
    private String userZipcode;
    private String userAddr;
    private String userPareHp;
    private String userCourse;
    private String userGrade;
    private String receiptsNo;
    private String receiptsType;
    private String hashId;
    private String userGradeByBirth;
    private int gradeCorrectionValue;
    private String happyCallDate;
    private String userSosokName;
    private String userPareName;
    private String gender;
    private String regDate;
    private String regularMemberCheck;
    private String regularPayYN;
    private String classSubjects;
    private String aptCode;
    private String userAddrDoro;
    private String confirm_dt;
    private String confirm_yn;
    private String send_dt;
    private String rqcode;
    private String userPwdFind;
    private String is_sleep;
    private String user_withdraw;
    private String regPath;

}
