package group1.habitAnalysis.service;

import group1.habitAnalysis.entity.UserEntity;
import group1.habitAnalysis.model.UserModel;
import group1.habitAnalysis.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getGmailEntity(UserEntity userEntity) {
        UserEntity gmail = this.userRepository.findByEmail(userEntity.getEmail());
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
