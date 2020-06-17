package com.egemsoft.application.rickandmortyapi.service.impl;

import com.egemsoft.application.rickandmortyapi.exception.NotFoundException;
import com.egemsoft.application.rickandmortyapi.model.Base;
import com.egemsoft.application.rickandmortyapi.model.ResponseInfo;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Generic abstract service for same operations for types which extends Base class
 *
 * @param <T> the type of elements in this list
 * @author  Shahriyar Novruzov
 * since 1.0
 */
public abstract class AbstractServiceImpl<T extends Base> {

    /**
     * Count limit of records on every page
     */
    @Value("${pagination.countLimit}")
    private long countLimit;

    /**
     * @param data List of generic data for Characters, Episode and Location
     * @param id record id
     * @return found record for given id
     *
     * Time complexity O(logN) - N size of the List
     * Space complexity O(1)
     *
     * Note: If it is guarantee that, every record id is consecutive in the List, we can find it O(1), data.get(id - 1).
     * But, in rickandmortyapi documentation says that, records sorted by id, not any information about to be consecutive,
     * so in this situation we can use Binary search to find record quickly.
     *
     * Also can be used Map to store data and get O(1) operation, but it uses more memory consumption
     */
    public T findById(List<T> data, Long id) {

        int foundIndex = binarySearch(data, id);
        if (foundIndex == -1) throw new NotFoundException(id + " id not found");
        return data.get(foundIndex);
    }

    private int binarySearch(List<T> data, Long id) {
        int low = 0, high = data.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (data.get(mid).getId().equals(id)) return mid;
            else if (data.get(mid).getId().compareTo(id) > 0)
                high = mid - 1;
            else low = mid + 1;
        }

        return -1;
    }

    /**
     * @param data List of generic data for Characters, Episode and Location
     * @param pageNumber requested page number, if it is not given, it will be considered 1
     * @return List of paginated records
     *
     * Time complexity O(N) - N number of records in List
     * Space complexity O(K) - K number of records on requested page
     */
    public List<T> findPaginatedData(List<T> data, Integer pageNumber) {

        if (pageNumber == null) pageNumber = 1;

        long skip = (pageNumber - 1) * countLimit;

        return data
                .stream()
                .skip(skip)
                .limit(countLimit)
                .collect(Collectors.toList());
    }

    /**
     * @param count count of records on requested page
     * @param pageNumber requested page number, if it is not given, it will be considered 1
     * @param url full url for every page, next and prev page url
     * @return response info about record count, page number, next and prev url
     *
     * Time complexity O(1)
     * Space complexity O(1)
     */
    public ResponseInfo getResponseInfo(int count, Integer pageNumber, String url) {
        if (pageNumber == null) pageNumber = 1;
        int pages = (int) Math.ceil(((double) count) / countLimit);
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
