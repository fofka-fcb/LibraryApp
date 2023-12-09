package ru.myPackage.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.myPackage.dao.BookDAO;
import ru.myPackage.dao.PeopleDAO;
import ru.myPackage.models.People;
import ru.myPackage.utils.PeopleValidator;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PeopleDAO peopleDAO;

    private final BookDAO bookDAO;

    private final PeopleValidator peopleValidator;

    @Autowired
    public PeopleController(PeopleDAO peopleDAO, BookDAO bookDAO, PeopleValidator peopleValidator) {
        this.peopleDAO = peopleDAO;
        this.bookDAO = bookDAO;
        this.peopleValidator = peopleValidator;
    }

    @GetMapping
    public String peopleIndex(Model model) {

        model.addAttribute("peoples", peopleDAO.index());

        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id,
                       ModelMap modelMap) {

        modelMap.addAttribute("people", peopleDAO.show(id));
        modelMap.addAttribute("books", bookDAO.showAllPeopleBook(id));

        return "people/show";
    }

    @GetMapping("/new")
    public String newPeople(@ModelAttribute("people") People people) {
        return "people/new";
    }

    @PostMapping
    public String createPeople(@ModelAttribute("people") @Valid People people,
                               BindingResult bindingResult) {

        peopleValidator.validate(people, bindingResult);

        if (bindingResult.hasErrors()) return "people/new";

        peopleDAO.save(people);

        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPeople(Model model, @PathVariable("id") int id) {

        model.addAttribute("people", peopleDAO.show(id));

        return "people/edit";
    }

    @PatchMapping("/{id}/update")
    public String update(@ModelAttribute("people") @Valid People people,
                         BindingResult bindingResult,
                         @PathVariable("id") int id) {

        if (!people.getFullName().equals(peopleDAO.show(id).getFullName()))
            peopleValidator.validate(people, bindingResult);

        if (bindingResult.hasErrors()) return "people/edit";

        peopleDAO.update(people, id);

        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {

        peopleDAO.delete(id);

        return "redirect:/people";
    }
}
