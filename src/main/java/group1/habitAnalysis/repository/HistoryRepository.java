package group1.habitAnalysis.repository;

import group1.habitAnalysis.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<HistoryEntity, String> {
    List<HistoryEntity> findAllByUserEmail(String email);
}
