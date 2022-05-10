package com.savvycom.springjwt.entity.service;

import com.savvycom.springjwt.data.LoverDTO;
import com.savvycom.springjwt.entity.Lover;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Project: springjwt
 * Author: Nhokxayda at 27/08/21
 */
public interface LoverRepository extends JpaRepository<Lover, Integer> {
    List<Lover> findLoversByLoverOrderByName(String lover);

    @Query("SELECT l from Lover l")
    List<Lover> getLovers(Pageable pageable);

    @Query("SELECT l FROM Lover l WHERE l.mark = :value ORDER BY :sort ASC")
    List<Lover> searchLoverByMark(@Param("value") Integer value, @Param("sort") String sort);

    @Query("SELECT l FROM Lover l WHERE l.lover LIKE ':value' ORDER BY :sort ASC")
    List<Lover> searchLoverByLover(@Param("value") String value, @Param("sort") String sort);

    @Query("SELECT l FROM Lover l WHERE l.name LIKE ':value' ORDER BY :sort ASC")
    List<Lover> searchLoverByName(@Param("value") String value, @Param("sort") String sort);
}
