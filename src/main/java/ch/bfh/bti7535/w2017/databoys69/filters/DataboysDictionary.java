package ch.bfh.bti7535.w2017.databoys69.filters;

import ch.bfh.bti7535.w2017.databoys69.App;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author databoys69
 * Implementation of a dictionary wrapper which consists
 * of two word lists for positive and negative words.
 */
public class DataboysDictionary {

    private List<String> positiveWords;
    private List<String> negativeWords;

    /**
     * Constructor to load positive and negative words
     * into separate @{@link ArrayList}
     */
    public DataboysDictionary() {

        try {

            // load word lists
            ClassLoader classLoader = this.getClass().getClassLoader();

            String filePos = classLoader.getResource("positiveWords.txt").getFile();
            String fileNeg = classLoader.getResource("negativeWords.txt").getFile();

            // remove leading slash on windows
            if (!App.isLinuxOs) {
                filePos = filePos.substring(1);
                fileNeg = fileNeg.substring(1);
            }

            positiveWords = Files.lines(Paths.get(filePos)).collect(Collectors.toList());
            negativeWords = Files.lines(Paths.get(fileNeg)).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Please include dictionaries for positive and negative words.");
            e.printStackTrace();
        }
    }

    /**
     * Checks if a given string has a positive meaning
     * @param input word
     * @return true if input is positive
     */
    public boolean isPositiveWord(String input) {
        return positiveWords.contains(input);
    }

    /**
     * Checks if a given string has a negative meaning
     * @param input word
     * @return true if input is negative
     */
    public boolean isNegativeWord(String input) {
        return negativeWords.contains(input);
    }
}
