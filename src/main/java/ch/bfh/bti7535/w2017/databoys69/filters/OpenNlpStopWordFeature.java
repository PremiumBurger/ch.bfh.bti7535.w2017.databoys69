package ch.bfh.bti7535.w2017.databoys69.filters;

import ch.bfh.bti7535.w2017.databoys69.App;
import opennlp.tools.doccat.FeatureGenerator;
import opennlp.tools.stemmer.PorterStemmer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Stop word filter for open nlp naive bayes
 */
public class OpenNlpStopWordFeature implements FeatureGenerator {

    private List<String> stopWords;

    public OpenNlpStopWordFeature()
    {
        try {

            // load word lists
            ClassLoader classLoader = this.getClass().getClassLoader();

            String fileStopWords = classLoader.getResource("stopwords.txt").getFile();

            // remove leading slash on windows
            if (!App.isLinuxOs) {
                fileStopWords = fileStopWords.substring(1);
            }

            stopWords = Files.lines(Paths.get(fileStopWords)).collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Please include dictionaries for positive and negative words.");
            e.printStackTrace();
        }
    }

    @Override
    public Collection<String> extractFeatures(String[] strings, Map<String, Object> map) {
        List<String> result = new ArrayList<>();
        // Filter all stopwords and return only the relevant words
        for (String token : strings) {
            if (stopWords.contains(token)) {
                continue;
            }

            result.add(token);
        }

        return result;
    }
}
