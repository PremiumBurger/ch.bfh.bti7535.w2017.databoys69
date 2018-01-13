package ch.bfh.bti7535.w2017.databoys69.filters;

import ch.bfh.bti7535.w2017.databoys69.algorithm.DataboysNaiveBayes;
import weka.attributeSelection.*;
import weka.core.Instances;
import weka.core.stemmers.NullStemmer;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 * @author databoys69
 * Factory to create and adjust the WEKA filters we use
 * in our @{@link DataboysNaiveBayes} implementation.
 */
public class DataboysFilterFactory {

    /**
     * Creates a string to vector filter and applies our
     * own @{@link DataboysStopWordHandler} to it.
     * @param train training data
     * @return filter
     */
    public static Filter buildStringToVectorFilter(Instances train) throws Exception {
        StringToWordVector vector = new StringToWordVector();
        vector.setStopwordsHandler(new DataboysStopWordHandler());
        vector.setStemmer(new NullStemmer());
        vector.setInputFormat(train);
        vector.setAttributeIndices("first");
        vector.setWordsToKeep(3000);
        return vector;
    }

    /**
     * Creates a filter to reduce the number of attributes
     * for the @{@link DataboysNaiveBayes} to look over.
     * @param train training data
     * @return filter
     */
    public static Filter buildAttributeSelectionFilter(Instances train) throws Exception {
        AttributeSelection selectionFilter = new AttributeSelection();
        selectionFilter.setInputFormat(train);
        selectionFilter.setEvaluator(new ClassifierAttributeEval());
        selectionFilter.setSearch(new Ranker());
        return selectionFilter;
    }

    /**
     * We tried to weight positive and negative sentiment words,
     * but the filter affected the result negatively.
     * @param instances training data
     * @return filter
     */
    public static Filter buildSentimentLexiconFilter(Instances instances) throws Exception {
        DataboysSentimentLexiconFilter lexiconFilter = new DataboysSentimentLexiconFilter();
        lexiconFilter.setInputFormat(instances);
        return lexiconFilter;
    }
}
