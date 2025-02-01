package org.example.services.loader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.services.report.ReportGenerator;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentLoader {

    private static final Logger logger = LogManager.getLogger(ContentLoader.class);

    public static Map<String, String> getPageStates(String sourceFile) {
        Map<String, String> state = new HashMap<>();
        List<URL> urlList = pageList(sourceFile);
        for (URL url : urlList) {
            String html = readPage(url);
            state.put(url.toString(), html);
        }

        return state;
    }

    /**
     * load list of pages from source file.
     *
     * @param sourceFile - path to file
     * @return - list of urls
     */
    private static List<URL> pageList(String sourceFile) {
        List<URL> urlList = new ArrayList<>();

        try ( BufferedReader bufferedReader = new BufferedReader(new FileReader(sourceFile)) ) {
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null) {
                urlList.add(new URL(inputLine));
            }
            return urlList;
        } catch (IOException | NullPointerException e) {
            logger.error("Failed to read source file: " + e.getMessage());
            return urlList;
        }
    }

    /**
     * get content from page url.
     *
     * @param url - page url
     * @return - content
     */
    private static String readPage(URL url) {

        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            StringBuilder sb = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine);
            in.close();

            return sb.toString();
        } catch (IOException e) {
            logger.error("Failed to read content from " + url + "\ntrace: " + e.getMessage());
            return "";
        }
    }
}