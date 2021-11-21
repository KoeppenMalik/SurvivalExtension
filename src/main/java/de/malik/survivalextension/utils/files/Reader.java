// Created on 20.11.2021, 19:05

package de.malik.survivalextension.utils.files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {

    public static ArrayList<String> readLines(File file) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = createReader(file);
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(Parser.COMMENT_PREFIX))
                    lines.add(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return lines;
    }

    private static BufferedReader createReader(File file) throws IOException {
        return new BufferedReader(new FileReader(file));
    }
}
