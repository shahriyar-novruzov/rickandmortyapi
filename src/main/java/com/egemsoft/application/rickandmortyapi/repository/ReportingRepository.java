package com.egemsoft.application.rickandmortyapi.repository;

import com.egemsoft.application.rickandmortyapi.model.History;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportingRepository {

    private List<History> historyList = new ArrayList<>();

    public void addHistory(History history) {
        this.historyList.add(history);
    }

    public List<History> getHistoryList() {
        return this.historyList;
    }
}
