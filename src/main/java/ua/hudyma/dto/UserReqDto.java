package ua.hudyma.dto;

import ua.hudyma.enums.GeniusLevel;
import ua.hudyma.enums.Sex;
import ua.hudyma.enums.UserType;

public record UserReqDto(
        String name,
        Sex sex,
        GeniusLevel geniusLevel,
        UserType userType
) {

}
