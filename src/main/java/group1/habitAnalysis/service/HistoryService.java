package group1.habitAnalysis.service;

import group1.habitAnalysis.entity.*;
import group1.habitAnalysis.model.HistoryDetailModel;
import group1.habitAnalysis.model.HistoryModel;
import group1.habitAnalysis.repository.HistoryDetailRepository;
import group1.habitAnalysis.repository.HistoryRepository;
import group1.habitAnalysis.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class HistoryService {
    private final UserRepository userRepository;
    private final HistoryRepository historyRepository;
    private final HistoryDetailRepository historyDetailRepository;

    public HistoryService(UserRepository userRepository, HistoryRepository historyRepository, HistoryDetailRepository historyDetailRepository) {
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
        this.historyDetailRepository = historyDetailRepository;
    }
    private  String GHistoryId;

//______________________________________________Post History_______________________________________
    public ResponseEntity<?> saveHistory(HistoryModel user) {
        UserEntity entity = this.userRepository.findByEmail(user.getUserEmail());

        if(entity != null){
            HistoryEntity history = new HistoryEntity();

            history.setHistoryId(UUID.randomUUID().toString());
            history.setCreateDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))));

            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(user.getUserEmail());
            history.setUser(userEntity);

            CategoryEntity category = new CategoryEntity();
            category.setId(user.getCategoryId());
            history.setCategory(category);

            GHistoryId = history.getHistoryId();
            this.historyRepository.save(history);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You need to login");
    }


    //__________________________________________Get History_______________________________________________

    public ResponseEntity<List<HistoryEntity>> getUserHistory(String email) {

        List<HistoryEntity> history = this.historyRepository.findAllByUserEmail(email);
//        List<UserHistoryEntity> result = new ArrayList<>();

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    //________________________________________Delete History_______________________________________________

    public ResponseEntity<?> deleteUserHistory(HistoryModel user) {
        UserEntity entity = this.userRepository.findByEmail(user.getUserEmail());
        if (entity != null) {
            historyRepository.deleteById(user.getHistoryId());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    //________________________________________Post History Detail_______________________________________________

    public ResponseEntity<?> postHistoryDetail(List<HistoryDetailModel> historyDetail) {
            HistoryDetailEntity hd = new HistoryDetailEntity();
            int i = 0;
        for (HistoryDetailModel historyDetailModel : historyDetail){
                i += 1;
                boolean type = i <= 10;

            hd.setHistoryDetailId(UUID.randomUUID().toString());
                HistoryEntity historyEntity = new HistoryEntity();
                historyEntity.setHistoryId(GHistoryId);
                hd.setHistory(historyEntity);

                hd.setChoiceId(historyDetailModel.getChoiceId());
                hd.setType(type);
            historyDetailRepository.save(hd);
        }
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
}
