package ru.kpekepsalt.diary.functional;

import org.springframework.http.ResponseEntity;

public interface ParamResponseFunctional<V> {
    ResponseEntity<V> action(V param);
}
