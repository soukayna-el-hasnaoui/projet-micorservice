package soukayna.rubrique.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import soukayna.rubrique.model.Rubrique;

// toutes les operations cruds sont implementé par defaut
public interface RubriqueService extends JpaRepository<Rubrique, Long> {
}
