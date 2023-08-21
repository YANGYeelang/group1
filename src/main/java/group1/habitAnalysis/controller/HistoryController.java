package group1.habitAnalysis.controller;

import group1.habitAnalysis.entity.CategoryEntity;
import group1.habitAnalysis.model.HistoryDetailModel;
import group1.habitAnalysis.model.HistoryModel;
import group1.habitAnalysis.repository.CategoryRepository;
import group1.habitAnalysis.repository.ChoiceRepository;
import group1.habitAnalysis.service.HistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class HistoryController {
    private final HistoryService historyService;
    private ChoiceRepository choiceRepository;
    private final CategoryRepository categoryRepository;

    public HistoryController(HistoryService historyService, CategoryRepository categoryRepository) {
        this.historyService = historyService;
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/api/post/history")
    public ResponseEntity<?> setHistory(@RequestBody HistoryModel historyModel) {
        return this.historyService.saveHistory(historyModel);
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
    @GetMapping("/api/get/description")
    public ResponseEntity<?> getDescription(Integer categoryId){
        Optional<CategoryEntity> category = this.categoryRepository.findById(categoryId);
        if (category.isPresent()){
            CategoryEntity entity = category.get();
            CategoryEntity categoryEntity = new CategoryEntity();
            categoryEntity.setDescription_th(entity.getDescription_th());
            categoryEntity.setDescription_en(entity.getDescription_en());
        return ResponseEntity.status(HttpStatus.OK).body(categoryEntity);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No description match categoryId");
    }



}
