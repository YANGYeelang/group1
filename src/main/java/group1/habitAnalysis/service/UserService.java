package group1.habitAnalysis.service;

import group1.habitAnalysis.entity.ResultEntity;
import group1.habitAnalysis.entity.UserEntity;
import group1.habitAnalysis.repository.ResultRepository;
import group1.habitAnalysis.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private ResultRepository resultRepository;

    public UserService(UserRepository userRepository, ResultRepository resultRepository) {
        this.userRepository = userRepository;
        this.resultRepository = resultRepository;
    }

    public ResponseEntity<?> getGmailEntity(UserEntity userEntity) {


        UserEntity gmail = this.userRepository.findByEmail(userEntity.getEmail());
        System.out.println(gmail);
        if(gmail == null){
            UserEntity gm = new UserEntity();
            gm.setUserName(userEntity.getUserName());
            gm.setEmail(userEntity.getEmail());
            this.userRepository.save(gm);
            return  ResponseEntity.status(HttpStatus.OK).body("Login success, gmail saved");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body("Login successful");
        }
    }
}
