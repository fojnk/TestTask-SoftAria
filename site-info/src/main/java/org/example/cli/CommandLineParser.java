package org.example.cli;

import com.beust.jcommander.JCommander;
import org.example.repository.PageRepository;
import org.example.services.email.EmailSender;
import org.example.services.loader.ContentLoader;
import org.example.services.report.ReportGenerator;
import org.example.services.statistics.StateDiff;
import org.example.services.statistics.StateDiffDetector;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CommandLineParser implements ICommandLine {
    private Args getArgs(String[] args) {
        Args parsed = new Args();
        JCommander cmd = JCommander.newBuilder()
                .addObject(parsed)
                .build();
        cmd.parse(args);
        return parsed;
    }

    private boolean parse(Args args) {
        var result = true;
        if (checkInputFile(args.getTodayPath())) {
            System.out.println("File " + args.getTodayPath() + " doesn't exist");
            result = false;
        }

        if (checkInputFile(args.getYesterdayPath())) {
            System.out.println("File " + args.getYesterdayPath() + " doesn't exist");
            result = false;
        }

        return result;
    }

    public void start(String[] input_args) throws InterruptedException, IOException, ClassNotFoundException {
        Args args = getArgs(input_args);
        var pr = new PageRepository();

        if (!parse(args)) {
            return;
        }

        Map<String, String> yesterdayData = pr.loadState(args.getYesterdayPath());
        Map<String, String> todayData = ContentLoader.getPageStates(args.getTodayPath());

        if (args.isUpdate()) {
            pr.saveState(args.getYesterdayPath(), todayData);
        }

        StateDiff report;

        if (args.isParallel()) {
            report = StateDiffDetector.parallelFindChanges(yesterdayData, todayData);
        } else {
            report = StateDiffDetector.findChanges(yesterdayData, todayData);
        }


        var str = ReportGenerator.generate(report, args.getName());

        if (args.getRecipients() == null) {
            System.out.println(str);
        } else {
            EmailSender.sendEmail(str, args.getRecipients());
        }
    }

    private boolean checkInputFile(String path)  {
        try (var f = new FileReader(path)){
            return false;
        } catch (IOException e) {
            return true;
        }
    }
}