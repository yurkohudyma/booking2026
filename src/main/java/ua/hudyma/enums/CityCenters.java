package ua.hudyma.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CityCenters {

    SPLIT(43.5081, 16.4402),
    VALETTA(35.8989, 14.5146),
    NICE(43.7102, 7.2620);

    private final Double latitude;
    private final Double longitude;
}
