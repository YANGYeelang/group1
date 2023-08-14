package group1.habitAnalysis.controller;

import group1.habitAnalysis.entity.UserHistoryEntity;
import group1.habitAnalysis.model.UserHistoryModel;
import group1.habitAnalysis.service.UserHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserHistoryController {
    private final UserHistoryService userHistoryService;

    public UserHistoryController(UserHistoryService userHistoryService) {
        this.userHistoryService = userHistoryService;
    }

    @PostMapping("/api/post/history")
    public ResponseEntity<?> setHistory(@RequestBody UserHistoryModel user){
        return this.userHistoryService.saveHistory(user);
    }
    @GetMapping("/api/get/history")
    public ResponseEntity<List<UserHistoryEntity>> getHistory(@RequestParam String email){
        return this.userHistoryService.getUserHistory(email);
    }

    @DeleteMapping("/api/delete/history")
    public ResponseEntity<?> deleteHistory(@RequestBody UserHistoryModel user){
        return this.userHistoryService.deleteUserHistory(user);
    }
}
