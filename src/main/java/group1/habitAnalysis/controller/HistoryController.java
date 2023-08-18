package group1.habitAnalysis.controller;

import group1.habitAnalysis.entity.HistoryEntity;
import group1.habitAnalysis.model.HistoryDetailModel;
import group1.habitAnalysis.model.HistoryModel;
import group1.habitAnalysis.repository.ChoiceRepository;
import group1.habitAnalysis.service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class HistoryController {
    private final HistoryService historyService;
    private ChoiceRepository choiceRepository;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/api/post/history")
    public ResponseEntity<?> setHistory(@RequestBody HistoryModel user) {
        return this.historyService.saveHistory(user);
    }


    @GetMapping("/api/get/history")
    public ResponseEntity<?> getHistory(@RequestParam String email){
        return this.historyService.getHistory(email);
    }

    @GetMapping("/api/get/history/byId")
    public ResponseEntity<?> getHistoryById(@RequestParam String historyId){
        return this.historyService.getHistoryById(historyId);
    }

    @DeleteMapping("/api/delete/history")
    public ResponseEntity<?> deleteHistory(@RequestParam String historyId){
        return this.historyService.deleteHistory(historyId);
    }


    //______________________________________________History Detail ________________________________________
    @PostMapping("/api/post/history/detail")
    public ResponseEntity<?> postTrueDetail(@RequestBody List<HistoryDetailModel> historyDetail){
        return this.historyService.postHistoryDetail(historyDetail);
    }

    @GetMapping("/api/get/history/detail")
    public ResponseEntity<?> getHistoryDetail(@RequestParam String historyId){
        return this.historyService.getHistoryDetail(historyId);
    }



}
