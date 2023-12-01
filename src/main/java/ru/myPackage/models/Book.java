package ru.myPackage.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    int id;

    int peopleID;

    String name;

    String author;

    String dob;
}
