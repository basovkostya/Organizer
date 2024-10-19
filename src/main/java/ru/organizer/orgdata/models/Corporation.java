package ru.organizer.orgdata.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name = "corporation")
public class Corporation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "inn")
    private long inn;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+", message = "введите корректный почтовый адрес")
    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "corporation")
    private List<ClubAddApplication> clubAddApplicationList;

    @OneToMany(mappedBy = "corporation")
    private List<EmployeeAddApplication> employeeAddApplicationList;

    @OneToMany(mappedBy = "corporation")
    private List<EventAddApplication> eventAddApplications;

    @OneToMany(mappedBy = "corporation")
    private List<EmployeeDeleteApplication> employeeDeleteApplicationList;
}
