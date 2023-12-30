package ru.myPackage.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.myPackage.models.Book;
import ru.myPackage.models.People;
import ru.myPackage.services.BookService;
import ru.myPackage.services.PeopleService;

@Controller
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }

    @GetMapping
    public String bookIndex(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model, @ModelAttribute("people") People people) {
        model.addAttribute("book", bookService.findOne(id));

        People bookOwner = bookService.getBookOwner(id);

        if (bookOwner != null) model.addAttribute("owner", bookOwner);
        else model.addAttribute("allPeople", peopleService.findAll());

        return "book/show";
    }

    @PatchMapping("/{id}/updateId")
    public String updateId(@PathVariable("id") int id, @ModelAttribute("people") People selectedPeople) {
        bookService.assign(id, selectedPeople);
        return "redirect:/book/{id}";
    }

    @PatchMapping("/{id}/freeBook")
    public String freeBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookService.release(id);
        return "redirect:/book/{id}";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) return "book/new";

        bookService.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookService.findOne(id));
        model.addAttribute("peoples", peopleService.findAll());
        return "book/edit";
    }

    @PatchMapping("/{id}/update")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) return "book/edit";

        bookService.update(id, book);
        return "redirect:/book/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }
}
