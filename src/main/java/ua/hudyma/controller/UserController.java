package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.dto.UserRespDto;
import ua.hudyma.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<String> createUser (@RequestBody UserReqDto dto){
        userService.createUser(dto);
        return ResponseEntity.ok("User created");
    }

    @GetMapping
    public ResponseEntity<UserRespDto> getUser (@RequestParam String userId){
        return ResponseEntity.ok(userService.getUserDto(userId));
    }

}
