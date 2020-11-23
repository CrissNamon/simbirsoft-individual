package ru.kpekepsalt.diary.functional;

import org.springframework.http.ResponseEntity;

public interface ResponseFunctional<V> {
    ResponseEntity<V> action();
}
