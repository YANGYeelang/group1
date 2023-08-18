package group1.habitAnalysis.service;

import group1.habitAnalysis.entity.*;
import group1.habitAnalysis.model.DetailModel;
import group1.habitAnalysis.model.HistoryDetailModel;
import group1.habitAnalysis.model.HistoryModel;
import group1.habitAnalysis.repository.ChoiceRepository;
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
    private final ChoiceRepository choiceRepository;

    public HistoryService(UserRepository userRepository, HistoryRepository historyRepository, HistoryDetailRepository historyDetailRepository, ChoiceRepository choiceRepository) {
        this.userRepository = userRepository;
        this.historyRepository = historyRepository;
        this.historyDetailRepository = historyDetailRepository;
        this.choiceRepository = choiceRepository;
    }
    private  String GHistoryId;

//______________________________________________Post History_______________________________________
    public ResponseEntity<?> saveHistory(HistoryModel user)  {
        UserEntity entity = this.userRepository.findByEmail(user.getUserEmail());

        if(entity != null){
            HistoryEntity history = new HistoryEntity();

            history.setHistoryId(UUID.randomUUID().toString());
            history.setCreateDate(LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))));

            UserEntity userEntity = new UserEntity();
            userEntity.setEmail(user.getUserEmail());
            history.setUser(userEntity);

            history.setDescriptionTh(user.getDescriptionTh());
            history.setDescriptionEn(user.getDescriptionEn());
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setId(user.getCategoryId());
            history.setCategory(categoryEntity);

            GHistoryId = history.getHistoryId();
            this.historyRepository.save(history);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("You need to login");
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
                historyModel.setDescriptionTh(historyEntity.getDescriptionTh());
                historyModel.setDescriptionEn(historyEntity.getDescriptionEn());
                historyModel.setCategoryId(historyEntity.getCategory().getId());

                List<HistoryDetailEntity> Models = historyEntity.getHistoryDetail();
                List<DetailModel> DetailModels = new ArrayList<>();
                for (HistoryDetailEntity model : Models) {
                    DetailModel MD = new DetailModel();
                    MD.setHistoryDetailId(model.getHistoryDetailId());
                    MD.setType(model.isType());
                    MD.setChoiceId(model.getChoice().getId());
                    DetailModels.add(MD);
                }

                historyModel.setHistoryDetail(DetailModels);
                historyModels.add(historyModel);

            }

            return ResponseEntity.status(HttpStatus.OK).body(historyModels);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Email");
    }


//*************************************Get History by HistoryId **********************************
    public ResponseEntity<?> getHistoryById(String historyId) {
        Optional<HistoryEntity> historyEntity = this.historyRepository.findById(historyId);
        if(historyEntity.isPresent()){
            List<HistoryModel> historyModels = new ArrayList<>();
            HistoryEntity history = historyEntity.get();
            HistoryModel historyModel = new HistoryModel();
            historyModel.setHistoryId(history.getHistoryId());
            historyModel.setCreateDate(history.getCreateDate());
            historyModel.setDescriptionTh(history.getDescriptionTh());
            historyModel.setDescriptionEn(history.getDescriptionEn());
            historyModel.setCategoryId(history.getCategory().getId());

            List<HistoryDetailEntity> Models = history.getHistoryDetail();
            List<DetailModel> DetailModels = new ArrayList<>();
            for (HistoryDetailEntity model : Models){
                DetailModel MD = new DetailModel();
                MD.setHistoryDetailId(model.getHistoryDetailId());
                MD.setType(model.isType());
                MD.setChoiceId(model.getChoice().getId());
                DetailModels.add(MD);
            }

            historyModel.setHistoryDetail(DetailModels);
            historyModels.add(historyModel);

            return ResponseEntity.status(HttpStatus.OK).body(historyModels);
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
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't not find history match: "+ historyId);

    }

    //________________________________________Post History Detail_______________________________________________

    public ResponseEntity<?> postHistoryDetail(List<HistoryDetailModel> historyDetail) {
        try {
            List<HistoryDetailEntity> result = new ArrayList<>();
            int i = 0;
            for (HistoryDetailModel historyDetailModel : historyDetail){
                i += 1;
                boolean type = i <= 10;
                HistoryDetailEntity hd = new HistoryDetailEntity();

                hd.setHistoryDetailId(UUID.randomUUID().toString());
                HistoryEntity historyEntity = new HistoryEntity();
                historyEntity.setHistoryId(GHistoryId);
                hd.setHistory(historyEntity);

                ChoiceEntity entity = new ChoiceEntity();
                entity.setId(historyDetailModel.getChoiceId());
                hd.setChoice(entity);
                hd.setType(type);

                result.add(hd);
            }
            historyDetailRepository.saveAll(result);
            return ResponseEntity.status(HttpStatus.OK).body("success");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Couldn't find History");
        }

    }

    //***********************************Get History Details by historyId*********************************

    public ResponseEntity<?> getHistoryDetail(String historyId) {
    try{
        HistoryEntity historyEntity = new HistoryEntity();
        historyEntity.setHistoryId(historyId);
        List<HistoryDetailEntity> historyDetail = this.historyDetailRepository.findAllByHistory(historyEntity);
            if (historyDetail.size() > 0) {

                List<HistoryDetailModel> choices = new ArrayList<>();
                for (HistoryDetailEntity detail : historyDetail) {

                    Optional<ChoiceEntity> choice = this.choiceRepository.findById(detail.getChoice().getId());
                    if (choice.isPresent()) {
                        HistoryDetailModel choiceModel = new HistoryDetailModel();
                        ChoiceEntity choiceEntity = choice.get();
                        choiceModel.setChoiceId(choiceEntity.getId());
                        choiceModel.setChoiceEn(choiceEntity.getChoiceEn());
                        choiceModel.setChoiceTh(choiceEntity.getChoiceTh());

                        CategoryEntity entity = choiceEntity.getCategory();
                        choiceModel.setCategoryId(entity.getId());
                        choiceModel.setType(detail.isType());

                        choices.add(choiceModel);
                    }
                }
                return ResponseEntity.status(HttpStatus.OK).body(choices);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid HistoryDetail");
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error");
    }
    }

}