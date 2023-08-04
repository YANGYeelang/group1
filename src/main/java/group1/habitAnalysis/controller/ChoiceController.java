package group1.habitAnalysis.controller;

import group1.habitAnalysis.entity.ChoiceEntity;
import group1.habitAnalysis.repository.ChoiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChoiceController {
    private final ChoiceRepository choiceRepository;

    public ChoiceController(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    @GetMapping("/api/choice")
    public ResponseEntity<List<ChoiceEntity>> getChoice(){
        return ResponseEntity.status(HttpStatus.OK).body(choiceRepository.findAll());
    }
}
