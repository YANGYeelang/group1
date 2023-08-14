package group1.habitAnalysis.repository;

import group1.habitAnalysis.entity.UserHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistoryEntity, Long> {
    List<UserHistoryEntity> findAllByUserEmail(String email);
}