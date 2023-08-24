package group1.habitAnalysis.service;

import group1.habitAnalysis.entity.*;
import group1.habitAnalysis.model.HistoryByIdModel;
import group1.habitAnalysis.model.HistoryModel;
import group1.habitAnalysis.repository.*;
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
    private final ChoiceRepository choiceRepository;

    public HistoryService(UserRepository userRepository, HistoryRepository historyRepository, ChoiceRepository choiceRepository) {
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
        this.choiceRepository = choiceRepository;
    }


//______________________________________________Post History_______________________________________
    public ResponseEntity<?> saveHistory(HistoryModel historyModel) {
        if (historyModel.getCategoryId() <= 4) {
            UserEntity entity = this.userRepository.findByEmail(historyModel.getUserEmail());

            if (entity != null) {
                HistoryEntity history = new HistoryEntity();

                history.setHistoryId(UUID.randomUUID().toString());
                history.setCreateDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))));

                UserEntity userEntity = new UserEntity();
                userEntity.setEmail(historyModel.getUserEmail());
                history.setUser(userEntity);

                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.setId(historyModel.getCategoryId());
                history.setCategory(categoryEntity);

                ArrayList<Integer> choiceIdLike = new ArrayList<>(historyModel.getChoiceIdLike());
                history.setChoiceIdLike(choiceIdLike.toString());
                ArrayList<Integer> choiceIdDisLike = new ArrayList<>(historyModel.getChoiceIdDisLike());
                history.setChoiceIdDislike(choiceIdDisLike.toString());

                this.historyRepository.save(history);
                return ResponseEntity.status(HttpStatus.OK).body("success");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid Email");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category Id not available");
    }


    //__________________________________________Get History by email_______________________________________________

    public ResponseEntity<?> getHistory(String email) {
        List<HistoryEntity> history = this.historyRepository.findAllByUserEmail(email);
        if (history.size()>0) {
            List<HistoryModel> historyModels = new ArrayList<>();
            for (HistoryEntity historyEntity : history) {
                HistoryModel historyModel = new HistoryModel();
                historyModel.setHistoryId(historyEntity.getHistoryId());
                historyModel.setCreateDate(historyEntity.getCreateDate());
                historyModel.setCategoryId(historyEntity.getCategory().getId());

                String choiceIdLikeString = historyEntity.getChoiceIdLike();
                String[] choiceIdLikeArray = choiceIdLikeString.substring(1, choiceIdLikeString.length() - 1).split(", ");
                ArrayList<Integer> choiceIdLike = new ArrayList<>();
                for (String choiceId : choiceIdLikeArray) {
                    choiceIdLike.add(Integer.parseInt(choiceId));
                }
                historyModel.setChoiceIdLike(choiceIdLike);

                String choiceIdDislikeString = historyEntity.getChoiceIdDislike();
                String[] choiceIdDislikeArray = choiceIdDislikeString.substring(1, choiceIdDislikeString.length() - 1).split(", ");
                ArrayList<Integer> choiceIdDislike = new ArrayList<>();
                for (String choiceId : choiceIdDislikeArray) {
                    choiceIdDislike.add(Integer.parseInt(choiceId));
                }
                historyModel.setChoiceIdDisLike(choiceIdDislike);
                historyModels.add(historyModel);

            }

            return ResponseEntity.status(HttpStatus.OK).body(historyModels);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid history");
    }


//*************************************Get History by HistoryId **********************************
    public ResponseEntity<?> getHistoryById(String historyId) {
        Optional<HistoryEntity> historyEntity = this.historyRepository.findById(historyId);

        if(historyEntity.isPresent()){
            HistoryByIdModel historyByIdModel = new HistoryByIdModel();
            HistoryEntity history = historyEntity.get();
            HistoryModel historyModel = new HistoryModel();
            historyModel.setHistoryId(history.getHistoryId());
            historyModel.setCreateDate(history.getCreateDate());
            historyModel.setCategoryId(history.getCategory().getId());

            List<ChoiceEntity> choiceLike = new ArrayList<>();
            String choiceIdLikeString = history.getChoiceIdLike();
            String[] choiceIdLikeArray = choiceIdLikeString.substring(1, choiceIdLikeString.length() - 1).split(", ");
            for (String choiceId : choiceIdLikeArray) {
                Integer choiceIdLike = Integer.parseInt(choiceId);
                Optional<ChoiceEntity> choice = this.choiceRepository.findById(choiceIdLike);
                if (choice.isPresent()){
                    ChoiceEntity chLike = choice.get();
                    choiceLike.add(chLike);
                }
            }
            List<ChoiceEntity> choiceDislike = new ArrayList<>();
            String choiceIdDislikeString = history.getChoiceIdDislike();
            String[] choiceIdDislikeArray = choiceIdDislikeString.substring(1, choiceIdDislikeString.length() - 1).split(", ");
            for (String choiceId : choiceIdDislikeArray) {
                Integer choiceIdDislike = Integer.parseInt(choiceId);
                Optional<ChoiceEntity> choice = this.choiceRepository.findById(choiceIdDislike);
                if (choice.isPresent()){
                    ChoiceEntity chDislike = choice.get();
                 choiceDislike.add(chDislike);
                }
            }

            historyByIdModel.setHistory(historyModel);
            historyByIdModel.setChoiceLike(choiceLike);
            historyByIdModel.setChoiceDislike(choiceDislike);

            return ResponseEntity.status(HttpStatus.OK).body(historyByIdModel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid History");
    }

    //________________________________________Delete History_______________________________________________

    public ResponseEntity<?> deleteHistory(String historyId) {
           Optional<HistoryEntity> history = historyRepository.findById(historyId);
           if (history.isPresent()){
            historyRepository.deleteById(historyId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
           }
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid historyId");

    }
}