package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import ua.hudyma.domain.User;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.dto.UserRespDto;
import ua.hudyma.mapper.UserMapper;
import ua.hudyma.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper mapper;

    public void createUser (UserReqDto dto){
        userRepository.save(mapper.toEntity(dto));
    }
    
    public User getUser (Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException
                        ("User " + id + " not found"));
    }

    public User getUser (String userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException
                ("User " + userId + " not found"));
    }
    
    public UserRespDto getUserDto (String userId){
        var user = userRepository.findByUserId(userId).orElseThrow();
        return mapper.toDto(user);
    }
}
