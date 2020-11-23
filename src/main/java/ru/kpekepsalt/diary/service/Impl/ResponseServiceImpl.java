package ru.kpekepsalt.diary.service.Impl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.kpekepsalt.diary.service.ResponseService;

@Service
public class ResponseServiceImpl<V> implements ResponseService<V> {

    @Override
    public ResponseEntity<V> badRequest() {
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<V> status(HttpStatus status) {
        return ResponseEntity.status(status).build();
    }

    @Override
    public ResponseEntity<V> ok() {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<V> ok(V object) {
        return ResponseEntity.ok(object);
    }

    @Override
    public ResponseEntity<V> notFound() {
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<V> forbidden() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
