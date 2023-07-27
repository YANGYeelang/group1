package group1.habitAlnalysis.repository;

import group1.habitAlnalysis.entity.GmailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GmailRepository extends JpaRepository<GmailEntity, Integer> {
    GmailEntity findByEmail(String email);
}
