package group1.habitAnalysis.repository;

import group1.habitAnalysis.entity.CategoryEntity;
import group1.habitAnalysis.entity.ChoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<ChoiceEntity, Integer> {

}
