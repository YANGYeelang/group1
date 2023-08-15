package group1.habitAnalysis.controller;

import group1.habitAnalysis.entity.HistoryEntity;
import group1.habitAnalysis.model.HistoryDetailModel;
import group1.habitAnalysis.model.HistoryModel;
import group1.habitAnalysis.service.HistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class HistoryController {
    private final HistoryService historyService;

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
    public ResponseEntity<?> deleteHistory(@RequestBody HistoryModel user){
        return this.historyService.deleteUserHistory(user);
    }


    //______________________________________________History Detail ________________________________________
    @PostMapping("/api/post/true/detail")
    public ResponseEntity<?> postTrueDetail(@RequestBody List<HistoryDetailModel> historyDetail){
        return this.historyService.postHistoryDetail(historyDetail);
    }

//    @PostMapping("/api/post/false/detail")
//    public ResponseEntity<?> postFalseDetail(@RequestBody List<HistoryDetailModel> historyDetail){
//        return this.historyService.postHistoryDetail(historyDetail);
//    }
}
