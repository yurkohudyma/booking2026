package ua.hudyma.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.hudyma.dto.RoomReqDto;
import ua.hudyma.dto.RoomRespDto;
import ua.hudyma.mapper.RoomMapper;
import ua.hudyma.repository.RoomRepository;
import ua.hudyma.service.RoomService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomRespDto>createRoom (@RequestBody RoomReqDto dto){
        return ResponseEntity.ok(roomService.createRoom(dto));
    }
}
