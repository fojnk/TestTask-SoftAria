package org.example.repository;

import java.io.IOException;
import java.util.Map;

public interface IPageRepository {
    void saveState(String filepath, Map<String, String> state) throws IOException;
    Map<String, String> loadState(String filepath) throws IOException, ClassNotFoundException;
}