package org.example;

import org.example.cli.CommandLineParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        var clp = new CommandLineParser();
        clp.start(args);
    }
}