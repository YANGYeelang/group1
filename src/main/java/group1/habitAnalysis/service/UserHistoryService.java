package group1.habitAnalysis.service;

import group1.habitAnalysis.entity.*;
import group1.habitAnalysis.model.UserHistoryModel;
import group1.habitAnalysis.repository.UserHistoryRepository;
import group1.habitAnalysis.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class UserHistoryService {
    private final UserRepository userRepository;
    private final UserHistoryRepository userHistoryRepository;

    public UserHistoryService(UserRepository userRepository, UserHistoryRepository userHistoryRepository) {
        this.userRepository = userRepository;
        this.userHistoryRepository = userHistoryRepository;
    }


//______________________________________________Post History_______________________________________
    public ResponseEntity<?> saveHistory(UserHistoryModel user) {
        UserEntity entity = this.userRepository.findByEmail(user.getUserEmail());

        if(entity != null){
            UserHistoryEntity userHistory = new UserHistoryEntity();
            userHistory.setDescription_th(user.getDescriptionTh());
            userHistory.setDescription_en(user.getDescriptionEn());
            userHistory.setCreateDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))));

            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(user.getUserEmail());
            userHistory.setUser(userEntity);

            CategoryEntity category = new CategoryEntity();
            category.setId(user.getCategoryId());
            userHistory.setCategory(category);

            this.userHistoryRepository.save(userHistory);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You need to login");
    }


    //__________________________________________Get History_______________________________________________

    public ResponseEntity<List<UserHistoryEntity>> getUserHistory(String email) {

        List<UserHistoryEntity> history = this.userHistoryRepository.findAllByUserEmail(email);
//        List<UserHistoryEntity> result = new ArrayList<>();

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    //________________________________________Delete History_______________________________________________

    public ResponseEntity<?> deleteUserHistory(UserHistoryModel user) {
        UserEntity entity = this.userRepository.findByEmail(user.getUserEmail());
        if (entity != null) {
            userHistoryRepository.deleteById(user.getId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }
}
