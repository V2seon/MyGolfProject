package com.example.golf.repository;

import com.example.golf.entity.CountryAccountEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface CountryAccountRepository extends JpaRepository<CountryAccountEntity,Long>, QuerydslPredicateExecutor<CountryAccountEntity> {

    Optional<CountryAccountEntity>  findByCanoAndCauino(Long Cano, Long Cauino);

    @Query(value = "SELECT * FROM test_club_account where CA_UI_NO=:CA_UI_NO and CA_STATE=1" , nativeQuery = true)
    List<CountryAccountEntity> findByCauino(Long CA_UI_NO);

    @Query(value = "SELECT * FROM test_club_account" , nativeQuery = true)
    Page<CountryAccountEntity> findAll(Pageable pageable);

    @Query(value = "SELECT COUNT(CA_UI_NO) FROM test_club_account WHERE CA_CC_NO=:no" , nativeQuery = true)
    int findCnt(Long no);

    @Query(value = "SELECT COUNT(*) FROM test_club_account WHERE CA_NO=:no" , nativeQuery = true)
    int checkNo(Long no);

    // CA 데이터 삭제
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM test_club_account WHERE CA_NO =:no", nativeQuery = true)
    void deleteData(Long no);

    Optional<CountryAccountEntity>  findByCauinoAndCaccno(Long Cauino, Long Caccno);

    Optional<CountryAccountEntity>  findByCaccnoAndCaid(Long Caccno, String Caid);

}
