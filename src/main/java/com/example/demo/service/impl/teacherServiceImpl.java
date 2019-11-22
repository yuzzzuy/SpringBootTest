package com.example.demo.service.impl;

import com.example.demo.dao.TeacherDao;
import com.example.demo.model.Teacher;
import com.example.demo.service.teacherService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: teacherServiceImpl
 * Author:   10347
 * Date:     2019/11/8 10:25
 * Description:
 */
@Service
public class teacherServiceImpl implements teacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher findByid(long id) {
        return teacherDao.getOne(id);
    }

    @Override
    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    @Override
    public boolean deleteTeacherById(long id) {
        boolean b = false;
        try {
            JdbcTemplate j  = new JdbcTemplate();
            String sql = "SELECT * FROM TEACHER WHERE ";
            System.out.println("开始删除！");
            teacherDao.deleteTeacherById(id);
            b = true;
            System.out.println("删除成功！");
        } catch (Exception e) {
            System.out.println("删除失败！");
            throw e;
        }

        return b;
    }

    @Override
    public boolean insertTeacher(Teacher teacher) {
        boolean b = false;
        try {
            System.out.println("开始新增！");
            teacher = teacherDao.save(teacher);
            b = teacher != null;
            System.out.println("新增成功！");
        } catch (Exception e) {
            System.out.println("新增失败！");
            throw e;
        }

        return b;
    }

    @Override
    public boolean updateTeacher(Teacher teacher) {
        boolean b = false;
        try {
            System.out.println("开始修改！");
            teacherDao.updateTeacherById(teacher.getName(), teacher.getId());
            b = true;
            System.out.println("修改成功！");
        } catch (Exception e) {
            System.out.println("修改失败！");
            throw e;
        }
        return b;
    }

    @Override
    public Page<Teacher> findTeacher(Teacher teacher, Integer pageNumber, Integer pageSize) {
        Page<Teacher> reasultList = null;
        Specification querySpeci = new Specification<Teacher>() {
            @Override
            public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (StringUtils.isNotEmpty(String.valueOf(teacher.getId()))&& teacher.getId() != 0){
                    predicates.add(criteriaBuilder.equal(root.get("id"),teacher.getId()));
                }
                if(StringUtils.isNotEmpty(teacher.getName())) {
                    predicates.add(criteriaBuilder
                        .like(root.get("name"), "%" + teacher.getName() + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
        Pageable pageable = PageRequest.of(pageNumber-1,pageSize);
        reasultList = teacherDao.findAll(querySpeci,pageable);
        return reasultList;

    }
}
