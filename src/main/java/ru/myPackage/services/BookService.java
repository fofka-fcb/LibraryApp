package ru.myPackage.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.myPackage.models.Book;
import ru.myPackage.models.People;
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

    public People getBookOwner(int id) {
        return bookRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        Book bookToBeUpdated = bookRepository.findById(id).get();

        updateBook.setId(id);
        updateBook.setOwner(bookToBeUpdated.getOwner());

        bookRepository.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public void assign(int id, People selectedPeople) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(selectedPeople);
                }
        );
    }

    @Transactional
    public void release(int id) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                });
    }

}
