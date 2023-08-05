package group1.habitAnalysis.service;

import group1.habitAnalysis.entity.ResultEntity;
import group1.habitAnalysis.model.SelectorModel;
import group1.habitAnalysis.repository.ResultRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {
    private final ResultRepository resultRepository;

    public ResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public ResponseEntity<ResultEntity> insertResult(SelectorModel selector) {

        ResultEntity result = new ResultEntity();
        int cow = selector.getMostCow() - selector.getLeastCow();
        int rat = selector.getMostRat() - selector.getLeastRat();
        int bear = selector.getMostBear() - selector.getLeastBear();
        int eagle = selector.getMostEagle() - selector.getLeastEagle();

        result.setEmail(selector.getEmail());

        //set cow percentage
        if (cow > 0) {
            switch (cow) {
                case 10 -> result.setCow(100);
                case 9 -> result.setCow(90);
                case 8 -> result.setCow(80);
                case 7 -> result.setCow(70);
                case 6 -> result.setCow(60);
                case 5 -> result.setCow(50);
                case 4 -> result.setCow(40);
                case 3 -> result.setCow(30);
                case 2 -> result.setCow(20);
                case 1 -> result.setCow(10);
            }
        }else {
            result.setCow(0);
        }

        //set rat percentage
        if (rat > 0) {
            switch (rat) {
                case 10 -> result.setRat(100);
                case 9 -> result.setRat(90);
                case 8 -> result.setRat(80);
                case 7 -> result.setRat(70);
                case 6 -> result.setRat(60);
                case 5 -> result.setRat(50);
                case 4 -> result.setRat(40);
                case 3 -> result.setRat(30);
                case 2 -> result.setRat(20);
                case 1 -> result.setRat(10);
            }
        }else {
            result.setRat(0);
        }

        //set bear percentage
        if (bear > 0) {
            switch (bear) {
                case 10 -> result.setBear(100);
                case 9 -> result.setBear(90);
                case 8 -> result.setBear(80);
                case 7 -> result.setBear(70);
                case 6 -> result.setBear(60);
                case 5 -> result.setBear(50);
                case 4 -> result.setBear(40);
                case 3 -> result.setBear(30);
                case 2 -> result.setBear(20);
                case 1 -> result.setBear(10);
            }
        }else {
            result.setBear(0);
        }

        //set eagle percentage
        if (eagle > 0) {
            switch (eagle) {
                case 10 -> result.setEagle(100);
                case 9 -> result.setEagle(90);
                case 8 -> result.setEagle(80);
                case 7 -> result.setEagle(70);
                case 6 -> result.setEagle(60);
                case 5 -> result.setEagle(50);
                case 4 -> result.setEagle(40);
                case 3 -> result.setEagle(30);
                case 2 -> result.setEagle(20);
                case 1 -> result.setEagle(10);
            }
        }else {
            result.setEagle(0);
        }

        this.resultRepository.save(result);

    return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public ResponseEntity<List<ResultEntity>> getResult(ResultEntity entity) {

        List<ResultEntity> result = this.resultRepository.findAllByEmail(entity.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
