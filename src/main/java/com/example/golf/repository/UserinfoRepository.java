package com.example.golf.repository;

import com.example.golf.entity.UserinfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserinfoRepository extends JpaRepository<UserinfoEntity, Long>, QuerydslPredicateExecutor<UserinfoEntity> {

    Optional<UserinfoEntity> findByUiid (String Uiid);

    Optional<UserinfoEntity> findByUiidAndUipassword(String uiid, String uipassword);

    // 사용자 정보 페이지
    @Query(value = "SELECT * FROM test_user_info " , nativeQuery = true)
    Page<UserinfoEntity> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM test_user_info " , nativeQuery = true)
    List<UserinfoEntity> findAll();

    // 사용자 정보 데이터 수정
    @Modifying
    @Transactional
    @Query(value = "UPDATE test_user_info SET UI_NAME =:uiname, UI_PHONE =:uiphone, UI_STATE =:uistate, UI_BAN =:uiban, UI_UDATETIME =:sdf where UI_NO =:no" , nativeQuery = true)
    void updateUIData(Long no, String uiname, String uiphone, int uistate, String uiban, LocalDateTime sdf);

    // 사용자 정보 데이터 수 가져오기(uiid)
    @Query(value = "SELECT COUNT(UI_ID) FROM test_user_info WHERE UI_ID =:uiid", nativeQuery = true)
    int checkUiid(String uiid);

    // 사용자 정보 데이터 가져오기(uino)
    @Query(value = "SELECT * FROM test_user_info WHERE UI_NO =:no", nativeQuery = true)
    UserinfoEntity getUino(Long no);
}
