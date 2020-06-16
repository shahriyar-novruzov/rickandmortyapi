package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Base;
import com.egemsoft.application.rickandmortyapi.model.ResponseInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AbstractServiceImpl<T extends Base> {

    @Value("${pagination.limit}")
    private long limit;

    public List<T> findPaginatedData(List<T> data, Integer pageNumber) {

        if (pageNumber == null) pageNumber = 1;

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

    public ResponseInfo getResponseInfo(int count, Integer pageNumber, String url) {
        if (pageNumber == null) pageNumber = 1;
        int pages = (int) Math.ceil(((double) count) / limit);
        return ResponseInfo
                .builder()
                .count(count)
                .pages(pages)
                .next(getPageUrl(url, pageNumber + 1, pages))
                .prev(getPageUrl(url, pageNumber - 1, pages))
                .build();
    }

    private String getPageUrl(String url, int pageNumber, int pages) {
        if (pageNumber < 1 || pageNumber > pages)
            return null;
        return url.concat(String.valueOf(pageNumber));
    }
}
