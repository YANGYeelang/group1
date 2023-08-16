package group1.habitAnalysis.service;

import group1.habitAnalysis.entity.*;
import group1.habitAnalysis.model.AllHistory;
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

    public ResponseEntity<?> saveAllHistory(List<AllHistory> user) {
        String historyId = null;

        AllHistory allHistory = new AllHistory();
        for (AllHistory history : user) {
            allHistory.setUserEmail(history.getUserEmail());
            allHistory.setChoiceId(history.getChoiceId());
            allHistory.setCategoryId(history.getCategoryId());
            allHistory.setDescriptionEn(history.getDescriptionEn());
            allHistory.setDescriptionTh(history.getDescriptionTh());

            break;
        }
        try {
            UserEntity entity = this.userRepository.findByEmail(allHistory.getUserEmail());

            if(entity != null){
                HistoryEntity history = new HistoryEntity();

                history.setHistoryId(UUID.randomUUID().toString());
                history.setCreateDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))));

                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(allHistory.getUserEmail());
                history.setUser(userEntity);

                history.setCategoryId(allHistory.getCategoryId());
                history.setDescriptionTh(allHistory.getDescriptionTh());
                history.setDescriptionEn(allHistory.getDescriptionEn());

                historyId = history.getHistoryId();
                this.historyRepository.save(history);
//                return ResponseEntity.status(HttpStatus.OK).body("success");
            }
//        }catch (Exception e){
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You need to login");
//        }
//
//
//        try {
            HistoryDetailEntity hd = new HistoryDetailEntity();
            int i = 0;
            for (AllHistory allhistory : user){
                i += 1;
                boolean type = i <= 10;

                hd.setHistoryDetailId(UUID.randomUUID().toString());
                HistoryEntity historyEntity = new HistoryEntity();
                historyEntity.setHistoryId(historyId);
                hd.setHistory(historyEntity);

                hd.setChoiceId(allhistory.getChoiceId());
                hd.setType(type);
                historyDetailRepository.save(hd);
            }
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Couldn't find History");
        }

    }
    //__________________________________________Get History_______________________________________________

    public ResponseEntity<List<HistoryEntity>> getHistory(String email) {

        List<HistoryEntity> history = this.historyRepository.findAllByUserEmail(email);
//        List<UserHistoryEntity> result = new ArrayList<>();

        return ResponseEntity.status(HttpStatus.OK).body(history);
    }

    //________________________________________Delete History_______________________________________________

    public ResponseEntity<?> deleteHistory(String historyId) {
           Optional<HistoryEntity> history = historyRepository.findById(historyId);
           if (history.isPresent()){
            historyRepository.deleteById(historyId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
           }
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't not find history match: "+ historyId);

    }

    //________________________________________Post History Detail_______________________________________________


}
