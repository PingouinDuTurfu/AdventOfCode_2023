package common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadFile {

    public static String[] read(String name) throws IOException {
        ClassLoader classLoader = ReadFile.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(name);
        if (inputStream == null)
            throw new IOException("File not found: " + name);
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        return br.lines().toArray(String[]::new);
    }
}
