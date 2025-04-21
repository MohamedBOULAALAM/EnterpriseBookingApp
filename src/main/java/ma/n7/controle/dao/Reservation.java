package ma.n7.controle.dao;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id @GeneratedValue
    private Long id;
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private TypeReservation type;

    private int duree;
    private double cout;

    @Enumerated(EnumType.STRING)
    private EtatReservation etat;

    @ManyToOne
    private Entreprise entreprise;
}

