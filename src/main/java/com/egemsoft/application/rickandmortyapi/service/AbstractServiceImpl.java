package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Base;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbstractServiceImpl<T extends Base> {

    @Value("${pagination.limit}")
    private long limit;

    public List<T> findPaginatedData(List<T> data, Integer pageNumber) {

        if (pageNumber == null)
            pageNumber = 1;

        long skip = (pageNumber - 1) * limit;

        return data
                .stream()
                .skip(skip)
                .limit(limit)
                .collect(Collectors.toList());
    }

    public T findById(List<T> data, Long id) {
        return data
                .stream()
                .filter(record -> id.equals(record.getId()))
                .findAny()
                .orElse(null);
    }
}
