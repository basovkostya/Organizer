package ru.organizer.orgdata.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.organizer.orgdata.enums.ApplicationStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_add_application")
public class EmployeeAddApplication implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @Column(name = "status")
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_date")
    private Date createdDate;

    @OneToMany(mappedBy = "employeeAddApplication", fetch = FetchType.EAGER)
    private List<Employee> employee;

    @ManyToOne
    @JoinColumn(name = "corporation_id", referencedColumnName = "id")
    private Corporation corporation;

    public void addEmployeeToEmployeeAddApplication(Employee employee){
        employee.setEmployeeAddApplication(this);
    }
}
