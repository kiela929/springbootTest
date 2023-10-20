package com.example.springbootvuetest.service.user;

import com.example.springbootvuetest.model.response.user.SiteMemberResponse;
import com.example.springbootvuetest.repository.mssql.erp.SitememberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SitememberService {

    private final SitememberRepository sitememberRepository;

    public SiteMemberResponse selectSiteMember(int seq) {

        var siteMember = sitememberRepository.findById(seq);

        if (siteMember.isPresent()) {
            var user = siteMember.stream().findFirst().get();
            var result = new SiteMemberResponse();
            var modelMapper = new ModelMapper();
            result = modelMapper.map(user, SiteMemberResponse.class);
            return result;
        }
        return new SiteMemberResponse();
    }
}
