package com.example.hospital.entities;

import jakarta.persistence.*;   // Pour JPA
import lombok.*;               // Pour @Getter, @Setter, etc.
import java.util.Date;

@Entity
@Table(name = "PATIENTS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @Temporal(TemporalType.DATE)
    private Date dateNaissance;

    private int score;
    private boolean malade;
}
