package group1.habitAnalysis.service;

import group1.habitAnalysis.entity.ResultEntity;
import group1.habitAnalysis.entity.UserEntity;
import group1.habitAnalysis.model.SelectorModel;
import group1.habitAnalysis.repository.ResultRepository;
import group1.habitAnalysis.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResultService {
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;

    public ResultService(ResultRepository resultRepository, UserRepository userRepository) {
        this.resultRepository = resultRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<ResultEntity> insertResult(SelectorModel selector) {
        try{
        ResultEntity result = new ResultEntity();
        int cow = selector.getMostCow() - selector.getLeastCow();
        int rat = selector.getMostRat() - selector.getLeastRat();
        int bear = selector.getMostBear() - selector.getLeastBear();
        int eagle = selector.getMostEagle() - selector.getLeastEagle();

        int sum = cow+rat+bear+eagle;

        UserEntity user = this.userRepository.findByEmail(selector.getEmail());
        result.setUser(user);

        //set cow percentage
        if (cow > 0) {
            switch (cow) {
                case 10 -> result.setCow(10*100/sum);
                case 9 -> result.setCow(9*100/sum);
                case 8 -> result.setCow(8*100/sum);
                case 7 -> result.setCow(7*100/sum);
                case 6 -> result.setCow(6*100/sum);
                case 5 -> result.setCow(5*100/sum);
                case 4 -> result.setCow(4*100/sum);
                case 3 -> result.setCow(3*100/sum);
                case 2 -> result.setCow(2*100/sum);
                case 1 -> result.setCow(100/sum);
            }
        }else {
            result.setCow(0);
        }

        //set rat percentage
        if (rat > 0) {
            switch (rat) {
                case 10 -> result.setRat(10*100/sum);
                case 9 -> result.setRat(9*100/sum);
                case 8 -> result.setRat(8*100/sum);
                case 7 -> result.setRat(7*100/sum);
                case 6 -> result.setRat(6*100/sum);
                case 5 -> result.setRat(5*100/sum);
                case 4 -> result.setRat(4*100/sum);
                case 3 -> result.setRat(3*100/sum);
                case 2 -> result.setRat(2*100/sum);
                case 1 -> result.setRat(100/sum);
            }
        }else {
            result.setRat(0);
        }

        //set bear percentage
        if (bear > 0) {
            switch (bear) {
                case 10 -> result.setBear(10*100/sum);
                case 9 -> result.setBear(9*100/sum);
                case 8 -> result.setBear(8*100/sum);
                case 7 -> result.setBear(7*100/sum);
                case 6 -> result.setBear(6*100/sum);
                case 5 -> result.setBear(5*100/sum);
                case 4 -> result.setBear(4*100/sum);
                case 3 -> result.setBear(3*100/sum);
                case 2 -> result.setBear(2*100/sum);
                case 1 -> result.setBear(100/sum);
            }
        }else {
            result.setBear(0);
        }

        //set eagle percentage
        if (eagle > 0) {
            switch (eagle) {
                case 10 -> result.setEagle(10*100/sum);
                case 9 -> result.setEagle(9*100/sum);
                case 8 -> result.setEagle(8*100/sum);
                case 7 -> result.setEagle(7*100/sum);
                case 6 -> result.setEagle(6*100/sum);
                case 5 -> result.setEagle(5*100/sum);
                case 4 -> result.setEagle(4*100/sum);
                case 3 -> result.setEagle(3*100/sum);
                case 2 -> result.setEagle(2*100/sum);
                case 1 -> result.setEagle(100/sum);
            }
        }else {
            result.setEagle(0);
        }

        this.resultRepository.save(result);

    return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            log.info("Exception{}"+ e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    public ResponseEntity<List<ResultEntity>> getResult(UserEntity entity) {
        UserEntity user = new UserEntity();
        user.setEmail(entity.getEmail());
        List<ResultEntity> result = this.resultRepository.findAllByUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
