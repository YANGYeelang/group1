package group1.habitAnalysis.controller;

import group1.habitAnalysis.entity.ChoiceEntity;
import group1.habitAnalysis.entity.HistoryEntity;
import group1.habitAnalysis.model.HistoryDetailModel;
import group1.habitAnalysis.model.HistoryModel;
import group1.habitAnalysis.repository.ChoiceRepository;
import group1.habitAnalysis.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class HistoryController {
    private final HistoryService historyService;
    private ChoiceRepository choiceRepository;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/api/post/history")
    public ResponseEntity<?> setHistory(@RequestBody HistoryModel user){
        return this.historyService.saveHistory(user);
    }
    @GetMapping("/api/get/history")
    public ResponseEntity<List<HistoryEntity>> getHistory(@RequestParam String email){
        return this.historyService.getUserHistory(email);
    }

    @DeleteMapping("/api/delete/history")
    public ResponseEntity<?> deleteHistory(@RequestParam String historyId){
        return this.historyService.deleteUserHistory(historyId);
    }


    //______________________________________________History Detail ________________________________________
    @PostMapping("/api/post/history/detail")
    public ResponseEntity<?> postTrueDetail(@RequestBody List<HistoryDetailModel> historyDetail){
        return this.historyService.postHistoryDetail(historyDetail);
    }

    @PostMapping("/api/post/detail/choice")
    public  ResponseEntity<ChoiceEntity> getChoice(@RequestParam Integer choiceId){
        Optional<ChoiceEntity> choice = choiceRepository.findById(choiceId);
        ChoiceEntity choiceEntity = new ChoiceEntity();
        if (choice.isPresent()){
            choiceEntity = choice.get();
        }
        return ResponseEntity.status(HttpStatus.OK).body(choiceEntity);
    }

}
