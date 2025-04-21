package ma.n7.controle.controller;

import ma.n7.controle.dao.EtatReservation;
import ma.n7.controle.dao.Reservation;
import ma.n7.controle.dao.TypeReservation;
import ma.n7.controle.repository.EntrepriseRepository;
import ma.n7.controle.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepo;

    @Autowired
    private EntrepriseRepository entrepriseRepo;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("reservations", reservationRepo.findAll());
        return "reservations";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("reservation", new Reservation());
        model.addAttribute("entreprises", entrepriseRepo.findAll());
        model.addAttribute("types", TypeReservation.values());
        model.addAttribute("etats", EtatReservation.values());
        return "formReservation";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Reservation r) {
        reservationRepo.save(r);
        return "redirect:/reservations";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        reservationRepo.deleteById(id);
        return "redirect:/reservations";
    }

    @GetMapping("/edit")
    public String edit(Model model, Long id) {
        Reservation r = reservationRepo.findById(id).orElse(null);
        model.addAttribute("reservation", r);
        model.addAttribute("entreprises", entrepriseRepo.findAll());
        model.addAttribute("types", TypeReservation.values());
        model.addAttribute("etats", EtatReservation.values());
        return "formReservation";
    }
}
