package org.example.repository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PageRepository implements IPageRepository {
    public void saveState(String filepath, Map<String, String> state) throws IOException {
        FileOutputStream fos = new FileOutputStream(filepath);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(state);
        oos.flush();
        oos.close();
    }

    public Map<String, String> loadState(String filepath) {
        Map<String, String> state = new HashMap<>();
        try {
            FileInputStream fis = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            state = (Map<String, String>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return state;
    }
}