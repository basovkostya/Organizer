package ru.organizer.orgdata.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.organizer.orgdata.enums.Sex;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotBlank
    @Column(name = "sur_name")
    private String surName;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+", message = "введите корректный почтовый адрес")
    @Column(name = "email")
    private String email;

    @NotBlank
    @Pattern(regexp = "[0-9a-zA-Z\\W]{6,}", message = "введите пароль латиницей длиной не менее 6 символов")
    @Column(name = "password")
    private String password;

    @Column(name = "snils")
    private long snils;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private Image logo;

    @Column(name = "passport_number")
    private long passportNumber;

    @NotNull
    @Column(name = "sex")
    private Sex sex;

    @NotBlank
    @Pattern(regexp = "(8|\\+)+\\d+", message = "введите номер начиная с 8 или +7")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull(message = "выберите дату рождения")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "age")
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "employee_add_application_id", referencedColumnName = "id")
    private EmployeeAddApplication employeeAddApplication;

    @ManyToOne
    @JoinColumn(name = "employee_delete_application_id", referencedColumnName = "id")
    private EmployeeDeleteApplication employeeDeleteApplication;

    @ManyToOne
    @JoinColumn(name="organization_id", referencedColumnName = "id")
    private Organization organization;

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", surName='" + surName + '\'' +
                '}';
    }
}
