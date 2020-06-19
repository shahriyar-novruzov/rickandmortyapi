package com.egemsoft.application.rickandmortyapi.service;

import com.egemsoft.application.rickandmortyapi.model.Character;
import com.egemsoft.application.rickandmortyapi.model.ThreadDemo;
import com.egemsoft.application.rickandmortyapi.repository.CharacterRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ThreadDemoService {

    private static final String TOTAL_CHAR_COUNT = "totalCharCount";
    private static final String CHAR_COUNT = "CharCount";

    private final CharacterRepository characterRepository;
    private Map<String, Integer> threadMap;
    private CountDownLatch countDownLatch;

    public ThreadDemoService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

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
            String currentThreadName = Thread.currentThread().getName();
            threadMap.put(currentThreadName, threadMap.getOrDefault(currentThreadName, 0) + name.length());
            countDownLatch.countDown();
        }
    }

    public ThreadDemo countNameCharacters() {

        List<Character> characters = characterRepository.getCharacters();
        countDownLatch = new CountDownLatch(characters.size());
        threadMap = new HashMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(25);

        for (Character character : characterRepository.getCharacters()) {
            NameCharacterCounter nameCharacterCounter = new NameCharacterCounter(character.getName());
            executorService.submit(nameCharacterCounter);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        int totalCount = 0;

        for (String threadName : threadMap.keySet()) {
            totalCount += threadMap.get(threadName);
        }

        List<String> result = new ArrayList<>();
        result.add(TOTAL_CHAR_COUNT.concat(": ").concat(String.valueOf(totalCount)));
        for (String threadName : threadMap.keySet())
            result.add(threadName.concat(CHAR_COUNT.concat(": ")).concat(String.valueOf(threadMap.get(threadName))));

        return ThreadDemo
                .builder()
                .result(result)
                .build();
    }
}
