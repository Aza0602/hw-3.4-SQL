package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student add(Student student) {
        student.setId(null);
        return studentRepository.save(student);
    }

    public Optional<Student> update(Long id, Student newStudent) {
        return studentRepository.findById(id)
                .map(oldStudent -> {
                    oldStudent.setName(newStudent.getName());
                    oldStudent.setAge(newStudent.getAge());
                    return studentRepository.save(oldStudent);
                });
    }

    public Optional<Student> getById(Long id) {
        return studentRepository.findById(id);
    }

    public Collection<Student> getAll() {
        return Collections.unmodifiableCollection(studentRepository.findAll());
    }

    public Optional<Student> deleteById(Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    studentRepository.deleteById(id);
                    return student;
                });
    }

    public Collection<Student> getAllByAge(int age) {
        return studentRepository.findAllByAge(age);
    }
}
