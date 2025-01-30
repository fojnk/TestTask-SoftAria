package org.example.statistics;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StateDiffDetector {
    public static StateDiff findChanges(Map<String, String> yesterdayData, Map<String, String> todayData) {
        Set<String> disappearedPages = new HashSet<>();
        Set<String> newPages = new HashSet<>();
        Set<String> changedPages = new HashSet<>();

        for (String url : yesterdayData.keySet()) {
            if (!todayData.containsKey(url)) {
                disappearedPages.add(url);
            }
        }

        for (String url : todayData.keySet()) {
            if (!yesterdayData.containsKey(url)) {
                newPages.add(url);
            } else if (!todayData.get(url).equals(yesterdayData.get(url))) {
                changedPages.add(url);
            }
        }

        return new StateDiff(disappearedPages, newPages, changedPages);
    }

    public static StateDiff parallelFindChanges(Map<String, String> yesterdayData, Map<String, String> todayData) throws InterruptedException {
        Set<String> disappearedPages = new HashSet<>();
        Set<String> newPages = new HashSet<>();
        Set<String> changedPages = new HashSet<>();

        Thread[] threads = new Thread[2];
        threads[0] = new Thread(() -> {
            for (String url : yesterdayData.keySet()) {
                if (!todayData.containsKey(url)) {
                    disappearedPages.add(url);
                }
            }
        });

        threads[1] = new Thread(() -> {
            for (String url : todayData.keySet()) {
                if (!yesterdayData.containsKey(url)) {
                    newPages.add(url);
                } else if (!todayData.get(url).equals(yesterdayData.get(url))) {
                    changedPages.add(url);
                }
            }
        });

        Arrays.stream(threads).forEach(Thread::start);

        for (var thread : threads) {
            thread.join();
        }

        return new StateDiff(disappearedPages, newPages, changedPages);
    }
}