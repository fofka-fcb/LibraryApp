package ru.myPackage.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    @Column(name = "name")
    String name;

    @NotEmpty(message = "Author name should not be empty")
    @Column(name = "author")
    String author;

    @NotEmpty(message = "Year should not be empty")
    @Column(name = "dob")
    String dob;

    @ManyToOne
    @JoinColumn(name = "people_id", referencedColumnName = "id")
    People owner;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;

    @Transient
    private boolean expired;

}
