package ua.hudyma.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Log4j2
public class DateService {

    void checkDatesConsistency(LocalDate start, LocalDate finish) {
        if (start == null) throw new IllegalArgumentException("Start date is not provided");
        else if (finish == null) throw new IllegalArgumentException("Finish date is not provided");
        else if (start.isEqual(finish)) throw new IllegalArgumentException
                ("Start and finish date shall at least differ by 1 day");
        else if (finish.isBefore(start)) {
            throw new IllegalArgumentException("Finish date shall not be prior to start");
        } else if (start.isBefore(LocalDate.now()) || finish.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start and-or finish dates cannot be in the past");
        }
    }
}
