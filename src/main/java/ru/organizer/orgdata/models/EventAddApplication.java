package ru.organizer.orgdata.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "event_application")
public class EventAddApplication {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "description")
    private String description;

    @NotBlank
    @Column(name = "place")
    private String place;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "date_from")
    private Date dateFrom;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "date_to")
    private Date dateTo;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "date_event")
    private Date dateEvent;

    @Column(name = "division_into_teams")
    private boolean divisionIntoTeams;

    @Min(1)
    @Column(name = "participants_from")
    private int participantsFrom;

    @Min(1)
    @Column(name = "participants_to")
    private int participantsTo;

    @Column(name = "snils_organizer")
    private long snilsOrganizer;

    @NotBlank
    @Column(name = "full_name")
    private String fullName;

    @Min(1)
    @Column(name = "price")
    private int price;

    @Column(name = "currency")
    private String currency;

    @Column(name = "status")
    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "is_active")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "corporation_id", referencedColumnName = "id")
    private Corporation corporation;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "sport_type")
    private String sportType;

    @Column(name = "tournament_type")
    private String tournamentType;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "application_deadline")
    private Date applicationDeadline;
}
