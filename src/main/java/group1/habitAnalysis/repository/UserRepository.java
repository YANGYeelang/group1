package group1.habitAnalysis.repository;

import group1.habitAnalysis.entity.ResultEntity;
import group1.habitAnalysis.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);

}
