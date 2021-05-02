package com.vartdalen.imagestoresql.validation;

import com.vartdalen.imagestoresql.model.DatabaseRecord;
import com.vartdalen.imagestoresql.util.ReflectionUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;
import javax.persistence.EntityNotFoundException;
import java.util.Optional;

public class QueryValidation {
    public static <T extends DatabaseRecord> T put(T object, JpaRepository<T, Long> repository) {
        Optional<T> match = repository.findById(object.getId());
        if (match.isEmpty()) {
            throw new EntityNotFoundException(String.format("%1$ with id %2$ was not found", object.getClass().getSimpleName(), object.getId()));
        }
        T merged;
        try {
            merged = ReflectionUtil.mergeObjects(match.get(), object);
        } catch (IllegalAccessException e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Unable to merge %1$ with id %2$", object.getClass().getSimpleName(), object.getId()));
        }
        return merged;
    }
}
