package com.example.demo.dao;

import com.example.demo.model.Teacher;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * FileName: teacherDao
 * Author:   10347
 * Date:     2019/11/8 10:16
 * Description:
 */
@Transactional
@Repository
public interface TeacherDao extends JpaRepository<Teacher, Long>, JpaSpecificationExecutor {

    @Transactional
    void deleteTeacherById(long id);

    @Modifying
    @Primary
    @Transactional
    @Query(value = "update Teacher set Teacher .name = :name where id = :id", nativeQuery = true)
    void updateTeacherById(@Param("name") String name, @Param("id") long id);


}
