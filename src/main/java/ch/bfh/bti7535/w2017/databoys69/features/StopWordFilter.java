package ch.bfh.bti7535.w2017.databoys69.features;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class StopWordFilter {

    List<String> stopWords;

    public StopWordFilter() throws IOException {
        ClassLoader clsLoader = this.getClass().getClassLoader();
        stopWords = Files.lines(Paths.get(clsLoader.getResource("stopwords.txt").getFile())).collect(Collectors.toList());
    }

    public List<String> filter(String text){
        StringTokenizer tokenizer = new StringTokenizer(text.toLowerCase());

        List<String> result = new ArrayList<>();
        while (tokenizer.hasMoreTokens()){
            String token = tokenizer.nextToken();

            if (stopWords.contains(token))
                continue;

            result.add(token);
        }

        return result;
    }
}
