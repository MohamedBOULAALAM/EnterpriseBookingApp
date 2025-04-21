package ma.n7.controle;

import ma.n7.controle.dao.Entreprise;
import ma.n7.controle.dao.EtatReservation;
import ma.n7.controle.dao.Reservation;
import ma.n7.controle.dao.TypeReservation;
import ma.n7.controle.repository.EntrepriseRepository;
import ma.n7.controle.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
 public class Test1 implements CommandLineRunner {

    @Autowired
    private EntrepriseRepository entrepriseRepo;

    @Autowired
    private ReservationRepository reservationRepo;

    @Override
    public void run(String... args) throws Exception {
        // 1. Création d'une entreprise
        Entreprise e1 = new Entreprise(null, "DevCorp", "contact@devcorp.com", "Informatique", "devuser", new ArrayList<>());
        entrepriseRepo.save(e1);

        // 2. Ajout de réservations
        Reservation r1 = new Reservation(null, LocalDate.now(), TypeReservation.SALLE_CONFERENCE, 3, 1200, EtatReservation.PENDING, e1);
        Reservation r2 = new Reservation(null, LocalDate.now().plusDays(1), TypeReservation.CENTRE_CALCUL, 2, 1800, EtatReservation.VALIDATED, e1);
        reservationRepo.saveAll(List.of(r1, r2));

        // 3. Lire les réservations liées à une entreprise
        List<Reservation> reservations = reservationRepo.findByEntrepriseUsername("devuser");
        reservations.forEach(r -> {
            System.out.println("👉 Réservation : " + r.getType() + " | Durée : " + r.getDuree() + "h | Entreprise : " + r.getEntreprise().getNom());
        });

        // 4. Afficher toutes les entreprises
        entrepriseRepo.findAll().forEach(ent -> {
            System.out.println("🏢 Entreprise : " + ent.getNom() + " – " + ent.getDomaine());
        });
    }

}
