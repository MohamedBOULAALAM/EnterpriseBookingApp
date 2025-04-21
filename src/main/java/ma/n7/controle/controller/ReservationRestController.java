package ma.n7.controle.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import ma.n7.controle.dao.Reservation;
import ma.n7.controle.repository.EntrepriseRepository;
import ma.n7.controle.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Tag(name = "Réservations", description = "API pour gérer les réservations")
public class ReservationRestController {

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private EntrepriseRepository entrepriseRepo;

    @Operation(summary = "Lister toutes les réservations")
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    @Operation(summary = "Obtenir une réservation par ID")
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservation(@PathVariable Long id) {
        Reservation res = reservationRepo.findById(id).orElse(null);
        return res != null ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Créer une nouvelle réservation")
    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    @Operation(summary = "Mettre à jour une réservation")
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation updated) {
        return reservationRepo.findById(id)
                .map(existing -> {
                    updated.setId(id);
                    return ResponseEntity.ok(reservationRepo.save(updated));
                }).orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Supprimer une réservation")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        if (!reservationRepo.existsById(id)) return ResponseEntity.notFound().build();
        reservationRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
