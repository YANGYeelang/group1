package group1.habitAnalysis.repository;

import group1.habitAnalysis.entity.ResultEntity;
import group1.habitAnalysis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
    List<ResultEntity> findAllByEmail(String email);

}
