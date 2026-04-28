package com.ice.realtimecache.Realtime.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SseService {
    private final Map<Long, SseEmitter> map = new ConcurrentHashMap<>();

    public SseEmitter connect(Long userId)
    {
        SseEmitter sseEmitter = new SseEmitter(Long.MAX_VALUE);

        map.put(userId, sseEmitter);

        sseEmitter.onCompletion(() -> map.remove(userId));
        sseEmitter.onTimeout(() -> map.remove(userId));
        sseEmitter.onError(error -> map.remove(userId));

        try {
            sseEmitter.send(
                    SseEmitter.event()
                            .name("connect")
                            .data("Connected to SSE")
            );
        } catch (Exception e) {
            map.remove(userId);
        }

        return sseEmitter;
    }

    public void sendToUser(Long userId, Object data)
    {
        SseEmitter sseEmitter = map.get(userId);
        try {
            sseEmitter.send(
                    SseEmitter.event()
                            .name("notification")
                            .data(data)
            );
        } catch (Exception e) {
            map.remove(userId);
        }
    }
}
