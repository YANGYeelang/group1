package group1.habitAnalysis.controller;

import group1.habitAnalysis.entity.UserEntity;
import group1.habitAnalysis.model.UserModel;
import group1.habitAnalysis.repository.UserRepository;
import group1.habitAnalysis.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {


    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> postUser(@RequestBody UserModel userModel){
        return this.userService.getGmailEntity(userModel);
    }

    @PutMapping("/api/put/tel")
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user){
        Optional<UserEntity> userEntity = this.userRepository.findById(user.getEmail());
        if (userEntity.isPresent()){
            UserEntity newUser = userEntity.get();
            newUser.setPhoneNumber(user.getPhoneNumber());
            userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.OK).body("Ok");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid email");
    }

    @GetMapping("/api/get/tel")
    public ResponseEntity<Integer> getTel(@RequestParam String email){
        try {
            UserEntity user = userRepository.findByEmail(email);
            if (user.getPhoneNumber() != null){
                return ResponseEntity.status(HttpStatus.OK).body(1);
            }
            return ResponseEntity.status(HttpStatus.OK).body(0);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(3);
        }

    }
}
