package ch.bfh.bti7535.w2017.databoys69.filters;

import opennlp.tools.doccat.FeatureGenerator;
import opennlp.tools.stemmer.PorterStemmer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Stemmer for open nlp naive bayes
 */
public class OpenNlpStemFeature implements FeatureGenerator {

    @Override
    public Collection<String> extractFeatures(String[] strings, Map<String, Object> map) {
        List<String> result = new ArrayList<>();
        PorterStemmer stemmer = new PorterStemmer();

        // Filter all stopwords and return only the relevant words
        for (String token : strings) {
            result.add(stemmer.stem(token));
        }

        return result;
    }
}
