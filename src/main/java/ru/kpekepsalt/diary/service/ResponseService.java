package ru.kpekepsalt.diary.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface ResponseService<V> {

    ResponseEntity<V> badRequest();
    ResponseEntity<V> status(HttpStatus status);
    ResponseEntity<V> ok();
    ResponseEntity<V> ok(V object);
    ResponseEntity<V> notFound();
    ResponseEntity<V> forbidden();

}
