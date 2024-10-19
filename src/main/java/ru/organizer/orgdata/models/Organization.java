package ru.organizer.orgdata.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "organization")
public class Organization {

    @Id
    @Column(name = "id")
    private  Integer id;

    @NotBlank
    @Column(name = "String")
    private String name;

    @OneToMany(mappedBy = "organization", fetch = FetchType.EAGER)
    private List<Employee> employee;

}
