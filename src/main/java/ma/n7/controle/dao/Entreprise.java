package ma.n7.controle.dao;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Entreprise {
    @Id @GeneratedValue
    private Long id;
    private String nom, email, domaine, username;
    @OneToMany(mappedBy = "entreprise")
    private List<Reservation> reservations;
}

