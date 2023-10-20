package com.example.springbootvuetest.entity.erp.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="site_member")
public class SiteMemberEntity {

    @Id
    @GeneratedValue
    @Column(name = "mbrseq")
    private Long id;
    @Column(name="user_id")
    private String userId;
    @Column(name="user_name")
    private String userName;
    @Column(name="user_pwd")
    private String userPwd;
    @Column(name="user_sno")
    private String userSno;
    @Column(name="user_level")
    private int userLevel;
    @Column(name="user_ok")
    private String userOk;
    @Column(name="user_birth")
    private String userBirth;
    @Column(name="user_pare_name")
    private String userPareName;
    @Column(name="user_sosok_name")
    private String userSosokName;
    @Column(name="user_course")
    private String userCourse;
    @Column(name="user_grade")
    private String userGrade;
    @Column(name="user_zipcode")
    private String userZipcode;
    @Column(name="user_addr")
    private String userAddr;
    @Column(name="user_tel")
    private String userTel;
    @Column(name="user_hp")
    private String userHp;
    @Column(name="user_pare_hp")
    private String userPareHp;
    @Column(name="user_email")
    private String userEmail;
    @Column(name="user_memo")
    private String userMemo;
    @Column(name="user_eng_name")
    private String userEngName;
    @Column(name="sms_receive_yn")
    private String smsReceiveYn;
    @Column(name="mail_receive_yn")
    private String mailReceiveYn;
    @Column(name="reg_path")
    private String regPath;
    @Column(name="user_last_login")
    private String userLastLogin;
    @Column(name="user_login_count")
    private int userLoginCount;
    @Column(name="user_reg_site")
    private String userRegSite;
    @Column(name="usertype")
    private String userType;


    @Column(name="is_sleep")
    private String is_sleep;
    @Column(name="user_withdraw")
    private String userWithdraw;


}
