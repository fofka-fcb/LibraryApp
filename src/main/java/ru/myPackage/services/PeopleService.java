package ru.myPackage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myPackage.models.People;
import ru.myPackage.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<People> findAll() {
        return peopleRepository.findAll();
    }

    public People findOne(int id) {
        Optional<People> peopleOptional = peopleRepository.findById(id);

        return peopleOptional.orElse(null);
    }

    public People findByFullName(String fullName) {
        return peopleRepository.findByFullName(fullName);
    }

    @Transactional
    public void save(People people) {
        peopleRepository.save(people);
    }

    @Transactional
    public void update(int id, People updatedPeople) {
        updatedPeople.setId(id);
        peopleRepository.save(updatedPeople);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
}
