package ua.hudyma.dto;

public record UserRespDto(
        Long id,
        String userId,
        String name,
        String email,
        String phone,
        String geniusLevel,
        String userType
) {

}
