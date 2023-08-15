package group1.habitAnalysis.repository;

import group1.habitAnalysis.entity.HistoryDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HistoryDetailRepository extends JpaRepository<HistoryDetailEntity, String> {

}
