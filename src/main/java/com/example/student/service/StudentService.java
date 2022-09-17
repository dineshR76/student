package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Service
@Transactional
@AllArgsConstructor
public class StudentService {


    @Autowired
    private StudentRepository studentRepository;

    public Flux<Student> getAll() {
        return studentRepository.findAll().switchIfEmpty(Flux.empty());
    }

    public Mono<Student> getById(final String id) {
        return studentRepository.findById(id);
    }

    public Mono update(final String id, final Student student) {
        return studentRepository.save(student);
    }

    public Mono save(final Student student) {
        return studentRepository.save(student);
    }

    public Mono delete(final String id) {
        final Mono<Student> dbStudent = getById(id);
        if (Objects.isNull(dbStudent)) {
            return Mono.empty();
        }
        return getById(id).switchIfEmpty(Mono.empty()).filter(Objects::nonNull).flatMap(studentToBeDeleted -> studentRepository
                .delete(studentToBeDeleted).then(Mono.just(studentToBeDeleted)));
    }
}