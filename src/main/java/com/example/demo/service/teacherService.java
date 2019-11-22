package com.example.demo.service;

import com.example.demo.model.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * FileName: teacherService
 * Author:   10347
 * Date:     2019/11/8 10:23
 * Description:
 */

public interface teacherService {

    Teacher findByid(long id);

    List<Teacher> findAll();

    boolean deleteTeacherById(long id);

    boolean insertTeacher(Teacher teacher);

    boolean updateTeacher(Teacher teacher);

    Page<Teacher> findTeacher(Teacher teacher, Integer pageNumber, Integer pageSize);
}
