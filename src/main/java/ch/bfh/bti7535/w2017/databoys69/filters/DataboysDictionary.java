package ch.bfh.bti7535.w2017.databoys69.filters;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yvesbeutler
 * INSERT DESCRIPTION HERE
 */
public class DataboysDictionary {

    private List<String> positiveWords;
    private List<String> negativeWords;

    public DataboysDictionary() {
        try {

            // load word lists
            ClassLoader classLoader = this.getClass().getClassLoader();

            String filePos = classLoader.getResource("positiveWords.txt").getFile();
            String fileNeg = classLoader.getResource("negativeWords.txt").getFile();

            // remove leading slash
            filePos = filePos.substring(1);
            fileNeg = fileNeg.substring(1);

            positiveWords = Files.lines(Paths.get(filePos)).collect(Collectors.toList());
            negativeWords = Files.lines(Paths.get(fileNeg)).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Please include dictionaries for positive and negative words.");
            e.printStackTrace();
        }
    }

    public boolean isPositiveWord(String input) {
        return positiveWords.contains(input);
    }

    public boolean isNegativeWord(String input) {
        return negativeWords.contains(input);
    }
}
