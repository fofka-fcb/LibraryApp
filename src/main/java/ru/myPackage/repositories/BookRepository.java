package ru.myPackage.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.myPackage.models.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
//    List<Book> findByNameStartingWith(String name);

    List<Book> findBooksByNameStartingWith(String name);
}
