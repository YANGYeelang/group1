package group1.habitAlnalysis.repository;

import group1.habitAlnalysis.entity.CateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CateRepository extends JpaRepository<CateEntity, Integer> {
}
