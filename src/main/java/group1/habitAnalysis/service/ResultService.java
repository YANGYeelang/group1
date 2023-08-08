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

import java.text.DecimalFormat;
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
        DecimalFormat decimal = new DecimalFormat();
        decimal.setMaximumFractionDigits(3);
        int cow = selector.getMostCow() - selector.getLeastCow();
        int rat = selector.getMostRat() - selector.getLeastRat();
        int bear = selector.getMostBear() - selector.getLeastBear();
        int eagle = selector.getMostEagle() - selector.getLeastEagle();

        // adding sum
        float sum = 0;
        if(cow >0){
            sum += cow;
        }
        if(rat >0){
            sum += rat;
        }
        if(bear >0){
            sum += bear;
        }
        if(eagle >0){
            sum += eagle;
        }

        UserEntity user = this.userRepository.findByEmail(selector.getEmail());
        if(user != null) {
            result.setUser(user);

            //set cow percentage
            if (cow > 0) {
                switch (cow) {
                    case 10 -> result.setCow(Float.parseFloat(decimal.format(10 * 100 / sum)));
                    case 9 -> result.setCow(Float.parseFloat(decimal.format(9 * 100 / sum)));
                    case 8 -> result.setCow(Float.parseFloat(decimal.format(8 * 100 / sum)));
                    case 7 -> result.setCow(Float.parseFloat(decimal.format(7 * 100 / sum)));
                    case 6 -> result.setCow(Float.parseFloat(decimal.format(6 * 100 / sum)));
                    case 5 -> result.setCow(Float.parseFloat(decimal.format(5 * 100 / sum)));
                    case 4 -> result.setCow(Float.parseFloat(decimal.format(4 * 100 / sum)));
                    case 3 -> result.setCow(Float.parseFloat(decimal.format(3 * 100 / sum)));
                    case 2 -> result.setCow(Float.parseFloat(decimal.format(2 * 100 / sum)));
                    case 1 -> result.setCow(Float.parseFloat(decimal.format(100 / sum)));
                }
            } else {
                result.setCow(0);
            }

            //set rat percentage
            if (rat > 0) {
                switch (rat) {
                    case 10 -> result.setRat(Float.parseFloat(decimal.format(10 * 100 / sum)));
                    case 9 -> result.setRat(Float.parseFloat(decimal.format(9 * 100 / sum)));
                    case 8 -> result.setRat(Float.parseFloat(decimal.format(8 * 100 / sum)));
                    case 7 -> result.setRat(Float.parseFloat(decimal.format(7 * 100 / sum)));
                    case 6 -> result.setRat(Float.parseFloat(decimal.format(6 * 100 / sum)));
                    case 5 -> result.setRat(Float.parseFloat(decimal.format(5 * 100 / sum)));
                    case 4 -> result.setRat(Float.parseFloat(decimal.format(4 * 100 / sum)));
                    case 3 -> result.setRat(Float.parseFloat(decimal.format(3 * 100 / sum)));
                    case 2 -> result.setRat(Float.parseFloat(decimal.format(2 * 100 / sum)));
                    case 1 -> result.setRat(Float.parseFloat(decimal.format(100 / sum)));
                }
            } else {
                result.setRat(0);
            }

            //set bear percentage
            if (bear > 0) {
                switch (bear) {
                    case 10 -> result.setBear(Float.parseFloat(decimal.format(10 * 100 / sum)));
                    case 9 -> result.setBear(Float.parseFloat(decimal.format(9 * 100 / sum)));
                    case 8 -> result.setBear(Float.parseFloat(decimal.format(8 * 100 / sum)));
                    case 7 -> result.setBear(Float.parseFloat(decimal.format(7 * 100 / sum)));
                    case 6 -> result.setBear(Float.parseFloat(decimal.format(6 * 100 / sum)));
                    case 5 -> result.setBear(Float.parseFloat(decimal.format(5 * 100 / sum)));
                    case 4 -> result.setBear(Float.parseFloat(decimal.format(4 * 100 / sum)));
                    case 3 -> result.setBear(Float.parseFloat(decimal.format(3 * 100 / sum)));
                    case 2 -> result.setBear(Float.parseFloat(decimal.format(2 * 100 / sum)));
                    case 1 -> result.setBear(Float.parseFloat(decimal.format(100 / sum)));
                }
            } else {
                result.setBear(0);
            }

            //set eagle percentage
            if (eagle > 0) {
                switch (eagle) {
                    case 10 -> result.setEagle(Float.parseFloat(decimal.format(10 * 100 / sum)));
                    case 9 -> result.setEagle(Float.parseFloat(decimal.format(9 * 100 / sum)));
                    case 8 -> result.setEagle(Float.parseFloat(decimal.format(8 * 100 / sum)));
                    case 7 -> result.setEagle(Float.parseFloat(decimal.format(7 * 100 / sum)));
                    case 6 -> result.setEagle(Float.parseFloat(decimal.format(6 * 100 / sum)));
                    case 5 -> result.setEagle(Float.parseFloat(decimal.format(5 * 100 / sum)));
                    case 4 -> result.setEagle(Float.parseFloat(decimal.format(4 * 100 / sum)));
                    case 3 -> result.setEagle(Float.parseFloat(decimal.format(3 * 100 / sum)));
                    case 2 -> result.setEagle(Float.parseFloat(decimal.format(2 * 100 / sum)));
                    case 1 -> result.setEagle(Float.parseFloat(decimal.format(100 / sum)));
                }
            } else {
                result.setEagle(0);
            }

            this.resultRepository.save(result);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
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
