package com.example.demo.demo.controller;


import com.example.demo.demo.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagerController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1L, "James"),
            new Student(2L, "Maria Jones"),
            new Student(3L, "Anna Smith")
    );

    @GetMapping()
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public void getAllStudents() {
        System.out.println("getAllStudents");
    }

    @PostMapping
    @PreAuthorize("hasAuthority('course:write')")
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('course:write')")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        System.out.println(id);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('course:write')")
    public void updateStudent(@PathVariable("studentId") Long id, Student student) {
        System.out.println(String.format("%s, %s", id, student));
    }
}
