package com.egemsoft.application.rickandmortyapi.repository;

import com.egemsoft.application.rickandmortyapi.model.History;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for store all history data in memory
 */
@Repository
public class ReportingRepository {

    private List<History> historyList = new ArrayList<>();

    /**
     * @param history historic data for every request/response action
     */
    public void addHistory(History history) {
        this.historyList.add(history);
    }

    /**
     * @return all history data since application up and run
     */
    public List<History> getHistoryList() {
        return this.historyList;
    }
}
