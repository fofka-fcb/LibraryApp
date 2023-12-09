package ru.myPackage.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    int id;

    int peopleID;

    @NotEmpty(message = "Title should not be empty")
    String name;

    @NotEmpty(message = "Author name should not be empty")
    String author;

    @NotEmpty(message = "Year should not be empty")
    String dob;
}
