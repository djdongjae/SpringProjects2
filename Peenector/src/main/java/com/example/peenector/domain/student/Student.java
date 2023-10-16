package com.example.peenector.domain.student;

import com.example.peenector.domain.team.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String schoolNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @Builder
    public Student(Long id, String schoolNumber, String name, String password, String contact, String email) {
        this.id = id;
        this.schoolNumber = schoolNumber;
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.email = email;
    }
}
