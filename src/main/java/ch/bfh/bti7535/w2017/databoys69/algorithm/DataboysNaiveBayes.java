package ch.bfh.bti7535.w2017.databoys69.algorithm;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class DataboysNaiveBayes implements Runnable {

    private File trainingData;

    private File testData;

    /**
     * Creates an Instance of a @{@link DataboysNaiveBayes} algorithm
     * @param trainingData the training data file in the <code>.arff</code> format
     * @param testData the test data file in the <code>.arff</code> format
     */
    public DataboysNaiveBayes(File trainingData, File testData) {
        CheckExistsAndNotNull(trainingData);
        CheckExistsAndNotNull(testData);

        this.trainingData = trainingData;
        this.testData = testData;
    }

    /**
     * Runs Databoys Naive Bayes algorithm
     */
    public void run() {
        try {
            //filter
            StringToWordVector filter = new StringToWordVector();
            Classifier naive = new NaiveBayes();

            //training data
            Instances train = new Instances(new BufferedReader(new FileReader(trainingData)));
            int lastIndex = train.numAttributes() - 1;
            train.setClassIndex(lastIndex);
            filter.setInputFormat(train);
            train = Filter.useFilter(train, filter);

            //testing data
            Instances test = new Instances(new BufferedReader(new FileReader(testData)));
            test.setClassIndex(lastIndex);
            filter.setInputFormat(test);

            naive.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(naive, test);
            String strSummary = eval.toSummaryString();
            System.out.println(strSummary);

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
