package ma.n7.controle.controller;


import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import ma.n7.controle.dao.Entreprise;
import ma.n7.controle.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/entreprises")
public class EntrepriseController {

    @Autowired
    private EntrepriseRepository entrepriseRepo;

    // Affichage + Recherche + Pagination
    @GetMapping
    public String list(Model model,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page) {

        Page<Entreprise> pageEntreprises = entrepriseRepo
                .findByNomContains(keyword, PageRequest.of(page, 5));

        model.addAttribute("entreprises", pageEntreprises.getContent());
        model.addAttribute("pages", new int[pageEntreprises.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "entreprises";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("entreprise", new Entreprise());
        return "formEntreprise";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Entreprise e) {
        entrepriseRepo.save(e);
        return "redirect:/entreprises";
    }

    @GetMapping("/delete")
    public String delete(Long id) {
        entrepriseRepo.deleteById(id);
        return "redirect:/entreprises";
    }

    @GetMapping("/edit")
    public String edit(Model model, Long id) {
        Entreprise e = entrepriseRepo.findById(id).orElse(null);
        model.addAttribute("entreprise", e);
        return "formEntreprise";
    }
}
