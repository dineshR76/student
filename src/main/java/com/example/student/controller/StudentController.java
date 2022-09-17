package com.example.student.controller;

import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RequestMapping("adminDept")
@AllArgsConstructor
@RestController
public class StudentController {


    @Autowired
    private StudentService StudentService;

    @GetMapping
    public Flux<Student> getAll() {
        System.out.println("::will returns ALL Students records::");
        return StudentService.getAll();
    }


    @GetMapping("{id}")
    public Mono<Student> getById(@PathVariable("id") final String id) {
        System.out.println("::will return a Student record::");
        return StudentService.getById(id);
    }

    @PutMapping("{id}")
    public Mono updateById(@PathVariable("id") final String id, @RequestBody final Student student) {
        System.out.println("::update the Student record::");
        return StudentService.update(id, student);
    }

    @PostMapping
    public Mono save(@RequestBody final Student student) {
        System.out.println("will insert the student's record :: "+ student.getId() + " :: " + student.getFirstName());
        return StudentService.save(student);
    }

    @DeleteMapping("{id}")
    public Mono delete(@PathVariable final String id) {
        System.out.println("::will delete a Student record::");
        return StudentService.delete(id);
    }
}
