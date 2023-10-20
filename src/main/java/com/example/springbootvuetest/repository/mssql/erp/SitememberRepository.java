package com.example.springbootvuetest.repository.mssql.erp;

import com.example.springbootvuetest.entity.erp.user.SiteMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SitememberRepository extends JpaRepository<SiteMemberEntity, Integer> {

    Optional<SiteMemberEntity> findById(Integer integer);
}
