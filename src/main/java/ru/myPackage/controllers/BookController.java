package ru.myPackage.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.myPackage.models.Book;
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
    public String show(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("book", bookService.findOne(id));
//        modelMap.addAttribute("peopleWhoTakeBook", bookService.findPeopleWhoTakeBook(id));
        modelMap.addAttribute("peoples", peopleService.findAll());
        return "book/show";
    }

    @PatchMapping("/{id}/updateId")
    public String updateId(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
//        bookDAO.updateId(book, id);
        return "redirect:/book/{id}";
    }

    @PatchMapping("/{id}/freeBook")
    public String freeBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        ///
//        bookDAO.updateId(book, id);
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

//        bookDAO.save(book);

        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String editBook(Model modelMap, @PathVariable("id") int id) {

//        modelMap.addAttribute("book", bookDAO.show(id));
//        modelMap.addAttribute("peoples", peopleDAO.indexAllPeople());

        return "book/edit";
    }

    @PatchMapping("/{id}/update")
    public String update(@ModelAttribute("book") @Valid Book book,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (bindingResult.hasErrors()) return "book/edit";

//        bookDAO.update(book, id);

        return "redirect:/book/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.delete(id);
        return "redirect:/book";
    }
}
