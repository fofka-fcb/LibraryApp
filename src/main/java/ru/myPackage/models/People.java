package ru.myPackage.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class People {

    int id;

    @NotEmpty(message = "Name should not be empty")
    String fullName;

    @NotEmpty(message = "Date of birthday should not be empty")
    String dob;
}
