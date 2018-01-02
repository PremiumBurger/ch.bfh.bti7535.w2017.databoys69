package ch.bfh.bti7535.w2017.databoys69.algorithm;

import ch.bfh.bti7535.w2017.databoys69.evaluation.DataboysEvaluator;
import ch.bfh.bti7535.w2017.databoys69.filters.DataboysFilterFactory;
import ch.bfh.bti7535.w2017.databoys69.filters.DataboysStopWordHandler;
import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.stemmers.SnowballStemmer;
import weka.filters.Filter;
import weka.filters.MultiFilter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DataboysNaiveBayes implements Runnable {


    private File dataSet;

    /**
     * Creates an Instance of a @{@link DataboysNaiveBayes} algorithm
     */
    public DataboysNaiveBayes(File inputFile) {
        CheckExistsAndNotNull(inputFile);
        this.dataSet = inputFile;
    }

    /**
     * Runs Databoys Naive Bayes algorithm
     */
    public void run() {
        try {
            // classifier
            Classifier naive = new NaiveBayesMultinomial();

            //training data
            Instances train = new Instances(new BufferedReader(new FileReader(dataSet)));
            int lastIndex = train.numAttributes() - 1;
            train.setClassIndex(lastIndex);

            // filter


            List<Filter> filters = new ArrayList<>();
             filters.add(DataboysFilterFactory.buildStringToVectorFilter(train));
             filters.add(DataboysFilterFactory.buildGoodBadWordFilter(train));

             MultiFilter multiFilter = new MultiFilter();
             Filter[] filterArray = new Filter[filters.size()];
             filterArray = filters.toArray(filterArray);
             multiFilter.setFilters(filterArray);
             multiFilter.setInputFormat(train);
             train = Filter.useFilter(train, multiFilter);


             train = Filter.useFilter(train, DataboysFilterFactory.buildAttributeSelectionFilter(train));

            ArffSaver saver = new ArffSaver();
            saver.setInstances(train);
            saver.setFile(new File("./test.arff"));
            saver.setDestination(new File("./test.arff"));   // **not** necessary in 3.5.4 and later
            saver.writeBatch();

            // build classifier
            naive.buildClassifier(train);

            // evaluate
            DataboysEvaluator evaluator = new DataboysEvaluator(10);
            evaluator.evaluate(naive, train);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void CheckExistsAndNotNull(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File must exists and must not be null");
        }
    }
}
