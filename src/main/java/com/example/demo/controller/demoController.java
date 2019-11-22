package com.example.demo.controller;

import com.example.demo.model.Teacher;
import com.example.demo.service.teacherService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * FileName: demoController
 * Author:   10347
 * Date:     2019/11/8 10:27
 * Description:
 */
@RestController
@EnableSwagger2
public class demoController {

    @Autowired
    private teacherService teacherService;

    @ApiOperation(value = "查找单条记录", notes = "传递id", produces = "application/json")
    @GetMapping("/find")
    public Teacher findByid(@RequestParam("id") long id) {

        return teacherService.findByid(id);
    }

    @ApiOperation(value = "查找所有记录", notes = "无参数", produces = "application/json")
    @GetMapping("/findAll")
    public List<Teacher> findByAll() {
        return teacherService.findAll();
    }

    @ApiOperation(value = "删除单条记录", notes = "参数：id", produces = "application/json")
    @GetMapping("/deleteTeacher")
    public boolean deleteTeacher(@RequestParam("id") long id) {
        return teacherService.deleteTeacherById(id);
    }

    @ApiOperation(value = "更新单条记录", notes = "", produces = "application/json")
    @GetMapping("/updateTeacher")
    public boolean updateTeacher(String name, @RequestParam("id") long id) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        return teacherService.updateTeacher(teacher);
    }

    @ApiOperation(value = "添加单条记录", notes = "传递复杂对象DTO", produces = "application/json")
    @GetMapping("/addTeacher")
    public boolean addTeacher(String name, @RequestParam("id") long id) {
        Teacher teacher = new Teacher();
        teacher.setId(id);
        teacher.setName(name);
        return teacherService.insertTeacher(teacher);
    }

    @ApiOperation(value = "无主键添加单条记录", notes = "传递复杂对象DTO", produces = "application/json")
    @GetMapping("/addTeacher1")
    public boolean addTeacher1(String name) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        return teacherService.insertTeacher(teacher);
    }

    @ApiOperation(value = "多条件查询单表", produces = "application/json")
    @GetMapping("/getPage")
    public Page<Teacher> getpage(
        Teacher teacher,
        @RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
        @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        Page<Teacher> page = teacherService.findTeacher(teacher, pageNumber, pageSize);
        return page;
    }
}
