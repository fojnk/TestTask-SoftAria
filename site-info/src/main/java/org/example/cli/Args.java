package org.example.cli;

import com.beust.jcommander.Parameter;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Args {
    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(
            names = "--name",
            description = "User name",
            required = true
    )
    private String name;

    @Parameter(
            names = "--recipients",
            description = "addresses to which messages will be sent"
    )
    private String recipients;

    @Parameter(
            names = "--today",
            description = "path to file that contains list of today urls",
            required = true
    )
    private String todayPath;

    @Parameter(
            names = "--yesterday",
            description = "path to file that contains previous state of pages",
            required = true
    )
    private String yesterdayPath;

    @Parameter(
            names = "--parallel",
            description = "Parallel search of changes"
    )
    private boolean parallel = false;

    @Parameter(
            names = "--update",
            description = "Update current state of pages"
    )
    private boolean update = false;
}