package com.ice.realtimecache.Realtime.Controller;

import com.ice.realtimecache.Realtime.Service.SseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/realtime")
@RequiredArgsConstructor
public class SseController {
    private final SseService sseService;

    @GetMapping("/stream")
    public SseEmitter stream(@RequestParam Long userId) {
        return sseService.connect(userId);
    }
}
