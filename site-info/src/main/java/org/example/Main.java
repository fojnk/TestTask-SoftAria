package org.example;

import org.example.report.ReportGenerator;
import org.example.statistics.StateDiffDetector;

import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        // Пример данных для вчера и сегодня
        Map<String, String> yesterdayData = new HashMap<>();
        Map<String, String> todayData = new HashMap<>();

        // Заполнение данных
        yesterdayData.put("https://example.com/page1", "<html>Old content</html>");
        yesterdayData.put("https://example.com/page2", "<html>Old content</html>");
        yesterdayData.put("https://example.com/page3", "<html>Old content</html>");

        todayData.put("https://example.com/page1", "<html>New content</html>");
        todayData.put("https://example.com/page4", "<html>New content</html>");
        todayData.put("https://example.com/page3", "<html>New content</html>");

        // Формирование отчета
        var report = StateDiffDetector.findChanges(yesterdayData, todayData);

        // Вывод отчета
        System.out.println(ReportGenerator.generate(report, "Petrov Vladimir"));
    }
}