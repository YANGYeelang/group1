package group1.habitAnalysis.controller;

import group1.habitAnalysis.entity.ResultEntity;
import group1.habitAnalysis.entity.UserEntity;
import group1.habitAnalysis.model.SelectorModel;
import group1.habitAnalysis.service.ResultService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ResultController {
    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/api/post/result")
    public ResponseEntity<ResultEntity> setSelector(@RequestBody SelectorModel selector){
        return this.resultService.insertResult(selector);
    }
    @GetMapping("/api/get/result")
    public ResponseEntity<List<ResultEntity>> getSelector(@RequestBody UserEntity entity){
        return this.resultService.getResult(entity);
    }
}
