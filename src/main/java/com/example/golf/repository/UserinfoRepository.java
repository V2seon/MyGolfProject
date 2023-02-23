package com.example.golf.repository;

import com.example.golf.entity.UserinfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface UserinfoRepository extends JpaRepository<UserinfoEntity, Long>, QuerydslPredicateExecutor<UserinfoEntity> {

    Optional<UserinfoEntity> findByUiid (String Uiid);

    Optional<UserinfoEntity> findByUiidAndUipassword(String uiid, String uipassword);

    // 사용자 정보 페이지
    @Query(value = "SELECT * FROM test_user_info " , nativeQuery = true)
    Page<UserinfoEntity> findAll(Pageable pageable);

}
