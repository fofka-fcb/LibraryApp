package ru.myPackage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myPackage.models.Book;
import ru.myPackage.repositories.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findOne(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        return optionalBook.orElse(null);
    }

//    public People findPeopleWhoTakeBook(int id) {
//        Optional<Book> optionalBook = bookRepository.findById(id);
//
//        if (optionalBook.isPresent()) {
//            Hibernate.initialize(optionalBook.get().getOwner());
//            return optionalBook.get().getOwner();
//        } else {
//            return null;
//        }
//    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        updateBook.setId(id);
        bookRepository.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

}
