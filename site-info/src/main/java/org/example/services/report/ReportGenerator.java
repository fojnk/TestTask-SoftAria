package org.example.services.report;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.services.statistics.StateDiff;

public class ReportGenerator {
    private static final Logger logger = LogManager.getLogger(ReportGenerator.class);
    public static String generate(StateDiff diff, String username) {
        logger.debug("generating report...");
        var formDisappearedPages = String.join(Constants.DELIM, diff.disappearedPages());
        var formNewPages = String.join(Constants.DELIM, diff.newPages());
        var formChangedPages = String.join(Constants.DELIM, diff.changedPages());

        return String.format(Constants.BASE_TEMPLATE, username,
                formDisappearedPages, formNewPages, formChangedPages);
    }
}