package ru.myPackage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myPackage.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
