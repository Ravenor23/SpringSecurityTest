package com.example.demo.demo.controller;

import com.example.demo.demo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1L, "James"),
            new Student(2L, "Maria Jones"),
            new Student(3L, "Anna Smith")
    );

    @GetMapping
    public List<Student> getAllStudents() {
        System.out.println(STUDENTS);
        return STUDENTS;
    }

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Long id) {
        return STUDENTS.stream().filter(student -> id == student.getId())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + id + " does not exist"));
    }
}
