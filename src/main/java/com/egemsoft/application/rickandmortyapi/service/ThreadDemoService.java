package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.helper.CountHelper;
import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.ThreadDemo;
import com.egemsoft.application.rickandmortyapi.repository.CharacterRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * Service for count Characters name characters by multiple thread concurrently
 */
@Component
public class ThreadDemoService {

    private static final String TOTAL_CHAR_COUNT = "totalCharCount";
    private static final String CHAR_COUNT = "CharCount";

    private final CharacterRepository characterRepository;
    private final ExecutorService executorService;
    private final CountHelper countHelper;
    private CountDownLatch countDownLatch;

    class NameCharacterCounter implements Runnable {
        private String name;

        public NameCharacterCounter(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public void run() {
            countHelper.increment(name.length());
            countDownLatch.countDown();
        }
    }

    public ThreadDemoService(CharacterRepository characterRepository,
                             ExecutorService executorService,
                             CountHelper countHelper) {
        this.characterRepository = characterRepository;
        this.executorService = executorService;
        this.countHelper = countHelper;
    }

    /**
     * @return data about total characters count and count by threads
     */
    public ThreadDemo countNameCharacters() {

        List<Character> characters = characterRepository.getCharacters();
        countHelper.clear();
        countDownLatch = new CountDownLatch(characters.size());

        for (Character character : characters) {
            executorService.submit(new NameCharacterCounter(character.getName()));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int totalCount = 0;

        Map<String, Integer> countsByThreads = countHelper.getCountsByThreads();

        for (String threadName : countsByThreads.keySet()) {
            totalCount += countsByThreads.get(threadName);
        }

        List<String> result = new ArrayList<>();
        result.add(TOTAL_CHAR_COUNT.concat(": ").concat(String.valueOf(totalCount)));
        for (String threadName : countsByThreads.keySet())
            result.add(
                    threadName.concat(CHAR_COUNT.concat(": ")).concat(String.valueOf(countsByThreads.get(threadName))));

        return ThreadDemo
                .builder()
                .result(result)
                .build();
    }
}
