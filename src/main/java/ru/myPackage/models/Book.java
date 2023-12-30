package ru.myPackage.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @NotEmpty(message = "Title should not be empty")
    String name;

    @NotEmpty(message = "Author name should not be empty")
    String author;

    @NotEmpty(message = "Year should not be empty")
    String dob;

    @ManyToOne
    @JoinColumn(name = "people_id", referencedColumnName = "id")
    People owner;

}
