package org.example.cli;

import java.io.IOException;

public interface ICommandLine {
    void start(String[] input_args) throws InterruptedException, IOException, ClassNotFoundException;
}