package org.example.report;

import org.example.statistics.StateDiff;

public class ReportGenerator {
    public static String generate(StateDiff diff, String username) {
        var formDisappearedPages = String.join(Constants.DELIM, diff.disappearedPages());
        var formNewPages = String.join(Constants.DELIM, diff.newPages());
        var formChangedPages = String.join(Constants.DELIM, diff.changedPages());

        return String.format(Constants.BASE_TEMPLATE, username, formDisappearedPages, formNewPages, formChangedPages);
    }
}