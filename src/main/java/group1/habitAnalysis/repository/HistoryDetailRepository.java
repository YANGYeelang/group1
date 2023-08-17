package group1.habitAnalysis.repository;

import group1.habitAnalysis.entity.HistoryDetailEntity;
import group1.habitAnalysis.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HistoryDetailRepository extends JpaRepository<HistoryDetailEntity, String> {
    List<HistoryDetailEntity> findAllByHistory(HistoryEntity historyEntity);
}
