package group1.habitAnalysis.controller;

import group1.habitAnalysis.entity.RatingEntity;
import group1.habitAnalysis.model.RatingModel;
import group1.habitAnalysis.repository.RatingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RatingController {
    private final RatingRepository ratingRepository;

    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/api/rating")
    public ResponseEntity<RatingModel> saveRating(@RequestBody RatingEntity rating){
        RatingModel model = new RatingModel();
        this.ratingRepository.save(rating);
        model.setRating(rating.getRating());
        return ResponseEntity.status(HttpStatus.OK).body(model);
    }
}
