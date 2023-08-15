package group1.habitAnalysis.service;

import group1.habitAnalysis.entity.UserEntity;
import group1.habitAnalysis.model.UserModel;
import group1.habitAnalysis.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {
    private final UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> getGmailEntity(UserModel userModel) {


        UserEntity gmail = this.userRepository.findByEmail(userModel.getEmail());
        if(gmail == null){
            UserEntity gm = new UserEntity();
            gm.setEmail(userModel.getEmail());
            gm.setFirstName(userModel.getFirstName());
            gm.setLastName(userModel.getLastName());
            gm.setImageUrl(userModel.getImageUrl());
            gm.setCreateDate(LocalDateTime.now());
            this.userRepository.save(gm);
            return  ResponseEntity.status(HttpStatus.OK).body("Login success, gmail saved");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body("Login successful");
        }
    }
}
