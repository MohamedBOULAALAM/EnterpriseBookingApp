package ma.n7.controle.repository;

import ma.n7.controle.dao.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByEntrepriseUsername(String username);
}
