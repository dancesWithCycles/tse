package de.swingbe.time_sheet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

public class TimeSheetReader {

    public final static Logger LOG = LoggerFactory.getLogger(TimeSheetReader.class);

    public static BufferedReader getReader(File file) {
        BufferedReader br;
        try {
            br = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file)));
        } catch (FileNotFoundException e) {
            LOG.error("failed opening file");
            e.printStackTrace();
            return null;
        }
        return br;
    }

    /**
     * Ignore heading lines
     *
     * @param br    Heading source
     * @param count Number of heading lines
     */
    public static void ignoreHeading(BufferedReader br, int count) {
        if (br != null) {
            try {
                //ignore heading lines containing descriptors
                for (int i = 1; i <= count; i++) {
                    br.readLine();
                }
            } catch (IOException e) {
                LOG.error("failed ignoring headings");
                e.printStackTrace();
            }
        } else {
            LOG.error("failed reading headings");
        }
    }
}
