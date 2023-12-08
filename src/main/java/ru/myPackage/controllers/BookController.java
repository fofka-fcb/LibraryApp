package ru.myPackage.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.myPackage.dao.BookDAO;
import ru.myPackage.dao.PeopleDAO;
import ru.myPackage.models.Book;

@Controller
@RequestMapping("/book")
public class BookController {

    private final PeopleDAO peopleDAO;

    private final BookDAO bookDAO;

    @Autowired
    public BookController(PeopleDAO peopleDAO, BookDAO bookDAO) {
        this.peopleDAO = peopleDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String bookIndex(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "book/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, ModelMap modelMap) {
        modelMap.addAttribute("book", bookDAO.show(id));
        modelMap.addAttribute("peoples", peopleDAO.index());
        return "book/show";
    }

    @PatchMapping("/{id}/updateId")
    public String updateId(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        bookDAO.updateId(book, id);
        return "redirect:/book/{id}";
    }

    @PatchMapping("/{id}/freeBook")
    public String freeBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        book.setPeopleID(1);
        bookDAO.updateId(book, id);
        return "redirect:/book/{id}";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "book/new";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") Book book) {
        bookDAO.save(book);
        return "redirect:/book";
    }

    @GetMapping("/{id}/edit")
    public String editBook(ModelMap modelMap, @PathVariable("id") int id) {
        modelMap.addAttribute("book", bookDAO.show(id));
        modelMap.addAttribute("peoples", peopleDAO.indexAllPeople());
        return "book/edit";
    }

    @PatchMapping("/{id}/update")
    public String update(@ModelAttribute("book") Book book, @PathVariable("id") int id) {
        bookDAO.update(book, id);
        return "redirect:/book/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/book";
    }
}
