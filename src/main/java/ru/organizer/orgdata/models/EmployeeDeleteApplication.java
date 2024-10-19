package ru.organizer.orgdata.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.organizer.orgdata.enums.ApplicationStatus;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_delete_application")
public class EmployeeDeleteApplication {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private ApplicationStatus status;

    @OneToMany(mappedBy = "employeeDeleteApplication")
    private List<Employee> employee;

    @ManyToOne
    @JoinColumn(name = "corporation_id", referencedColumnName = "id")
    private Corporation corporation;
}
