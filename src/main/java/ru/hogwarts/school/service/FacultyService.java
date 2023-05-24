package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.entity.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        faculty.setId(null);
        return facultyRepository.save(faculty);
    }

    public Optional<Faculty> update(Long id, Faculty newfaculty) {
        return facultyRepository.findById(id)
                .map(oldFaculty -> {
                    oldFaculty.setName(newfaculty.getName());
                    oldFaculty.setColor(newfaculty.getColor());
                    return facultyRepository.save(oldFaculty);
                });
    }

    public Optional<Faculty> getById(Long id) {
        return facultyRepository.findById(id);
    }

    public Collection<Faculty> getAll() {
        return Collections.unmodifiableCollection(facultyRepository.findAll());
    }

    public Optional<Faculty> deleteById(Long id) {
        return facultyRepository.findById(id)
                .map(faculty -> {
                    facultyRepository.deleteById(id);
                    return faculty;
                });
    }

    public Collection<Faculty> getAllByColor(String color) {
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> getAllByNameOrColor(String nameOrColor) {
        return facultyRepository.findAllByNameContainsIgnoreCaseOrColorContainsIgnoreCase(nameOrColor, nameOrColor);
    }
}
