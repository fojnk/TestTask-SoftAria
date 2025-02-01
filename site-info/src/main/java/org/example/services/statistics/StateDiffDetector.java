package org.example.services.statistics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class StateDiffDetector {

    /**
     * find changes in pages state.
     *
     * @param yesterdayData - state of yesterday list of pages
     * @param todayData     - state of today list of pages
     * @return - difference of states
     */
    public static StateDiff findChanges(Map<String, String> yesterdayData, Map<String, String> todayData) {
        return new StateDiff(
                getDisappearedPages(yesterdayData, todayData),
                getNewPages(yesterdayData, todayData),
                getChangedPages(yesterdayData, todayData)
        );
    }

    // parallel difference search
    public static StateDiff parallelFindChanges(Map<String, String> yesterdayData, Map<String, String> todayData) throws InterruptedException {
        AtomicReference<List<String>> disappearedPages = new AtomicReference<>(new ArrayList<>());
        AtomicReference<List<String>> newPages = new AtomicReference<>(new ArrayList<>());
        AtomicReference<List<String>> changedPages = new AtomicReference<>(new ArrayList<>());

        Thread[] threads = new Thread[3];
        threads[0] = new Thread(() -> {
            disappearedPages.set(getDisappearedPages(yesterdayData, todayData));
        });

        threads[1] = new Thread(() -> {
            newPages.set(getNewPages(yesterdayData, todayData));
        });

        threads[2] = new Thread(() -> {
            changedPages.set(getChangedPages(yesterdayData, todayData));
        });

        Arrays.stream(threads).forEach(Thread::start);

        for (var thread : threads) {
            thread.join();
        }

        return new StateDiff(disappearedPages.get(), newPages.get(), changedPages.get());
    }

    private static List<String> getDisappearedPages(Map<String, String> yesterdayData, Map<String,
            String> todayData) {
        return yesterdayData.keySet().stream().filter(page -> !todayData.containsKey(page)).toList();
    }

    private static List<String> getNewPages(Map<String, String> yesterdayData,
                                            @org.jetbrains.annotations.NotNull Map<String, String> todayData) {
        return todayData.keySet().stream().filter(page -> !yesterdayData.containsKey(page)).toList();
    }

    private static List<String> getChangedPages(Map<String, String> yesterdayData, Map<String,
            String> todayData) {
        return todayData.keySet().stream().filter(page -> yesterdayData.containsKey(page)
                && !todayData.get(page).equals(yesterdayData.get(page))).toList();
    }
}