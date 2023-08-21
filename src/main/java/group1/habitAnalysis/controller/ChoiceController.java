package group1.habitAnalysis.controller;

import group1.habitAnalysis.entity.CategoryEntity;
import group1.habitAnalysis.entity.ChoiceEntity;
import group1.habitAnalysis.model.ChoiceModel;
import group1.habitAnalysis.repository.ChoiceRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class ChoiceController {
    private final ChoiceRepository choiceRepository;

    public ChoiceController(ChoiceRepository choiceRepository) {
        this.choiceRepository = choiceRepository;
    }

    @GetMapping("/api/choice")
    public ResponseEntity<List<ChoiceModel>> getChoice(){
        List<ChoiceModel> choices = new ArrayList<>();
        List<ChoiceEntity> entities = choiceRepository.findAll();
        for(ChoiceEntity entity: entities){
        ChoiceModel model = new ChoiceModel();
        model.setId(entity.getId());
        model.setChoiceTh(entity.getChoiceTh());
        model.setChoiceEn(entity.getChoiceEn());

        CategoryEntity category = entity.getCategory();
        model.setCategoryId(category.getId());
            choices.add(model);
        }

        return ResponseEntity.status(HttpStatus.OK).body(choices);
    }

}
