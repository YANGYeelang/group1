package group1.habitAnalysis.controller;

import group1.habitAnalysis.entity.ResultEntity;
import group1.habitAnalysis.entity.UserEntity;
import group1.habitAnalysis.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> getGmailEntity(@RequestBody UserEntity userEntity){
        return this.userService.getGmailEntity(userEntity);
    }
}
