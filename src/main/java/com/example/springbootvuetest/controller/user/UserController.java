package com.example.springbootvuetest.controller.user;

import com.example.springbootvuetest.model.response.user.SiteMemberResponse;
import com.example.springbootvuetest.service.user.SitememberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final SitememberService sitememberService;

    @GetMapping("")
    public ResponseEntity<SiteMemberResponse> siteMember(@RequestParam int seq) {
        return ResponseEntity.ok(sitememberService.selectSiteMember(seq));
    }
}
