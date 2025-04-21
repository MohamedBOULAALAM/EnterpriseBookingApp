package ma.n7.controle.repository;

import ma.n7.controle.dao.Entreprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    Page<Entreprise> findByNomContains(String keyword, Pageable pageable);
}
