package ru.myPackage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.myPackage.models.People;

@Repository
public interface PeopleRepository extends JpaRepository<People, Integer> {

    People findByFullName(String fullName);
}
