package com.example.springbootvuetest.repository.mysql.www;

import com.example.springbootvuetest.entity.www.board.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>, PagingAndSortingRepository<BoardEntity, Integer> {

    @Override
    List<BoardEntity> findAll(Sort sort);

    @Override
    Page<BoardEntity> findAll(Pageable pageable);

    @Override
    Optional<BoardEntity> findById(Integer integer);

    @Override
    <S extends BoardEntity> S save(S entity);

    @Override
    void deleteById(Integer integer);

    Page<BoardEntity> findAllByOrderByIdDesc(Pageable pageable);

}
