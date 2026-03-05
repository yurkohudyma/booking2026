package ua.hudyma.mapper;

import org.springframework.stereotype.Component;
import ua.hudyma.domain.User;
import ua.hudyma.dto.UserReqDto;
import ua.hudyma.dto.UserRespDto;
import ua.hudyma.enums.GeniusLevel;
import ua.hudyma.enums.UserType;

import static ua.hudyma.enums.Sex.OTHER;
import static ua.hudyma.util.IdGenerator.*;

@Component
public class UserMapper extends BaseMapper<UserRespDto, User, UserReqDto>{

    @Override
    public UserRespDto toDto(User user) {
        return new UserRespDto(
                user.getId(),
                user.getUserId(),
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getGeniusLevel().name(),
                user.getUserType().name()
        );
    }

    @Override
    public User toEntity(UserReqDto dto) {
        var userName = dto.name();
        if (userName == null || userName.isEmpty()) throw new IllegalArgumentException("Username cannot be null or empty");
        var userType = dto.userType() == null ? UserType.VISITOR : dto.userType();
        var genius = dto.geniusLevel();
        var sex = dto.sex() == null ? OTHER : dto.sex();
        if (genius == null || genius.name().isEmpty())
            genius = GeniusLevel.NA;
        var user = new User();
        user.setName(userName);
        user.setUserType(userType);
        user.setUserId(generateId(userType));
        user.setEmail(generateEmail(userName));
        user.setSex(sex);
        user.setGeniusLevel(genius);
        return user;
    }
}
