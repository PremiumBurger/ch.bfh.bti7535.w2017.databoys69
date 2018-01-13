package ch.bfh.bti7535.w2017.databoys69.algorithm;

import ch.bfh.bti7535.w2017.databoys69.filters.DataboysDictionary;

import weka.core.Instances;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author databoys69
 * Implementation of a baseline algorithm to count sentiment words and
 * compare the number of positive with the number of negative words.
 */
public class DataboysBaseLine implements Runnable {

    private File dataSet;

    /**
     * Creates an Instance of a @{@link DataboysBaseLine} algorithm
     */
    public DataboysBaseLine(File inputFile) {
        CheckExistsAndNotNull(inputFile);
        this.dataSet = inputFile;
    }

    /**
     * Runs Databoys Baseline algorithm to count all sentiment words
     * and compares the number of positive words with the negative
     * ones.
     */
    public void run() {

        try {

            Instances data = new Instances(new BufferedReader(new FileReader(dataSet)));

            double numberOfReviews = 0;
            double numberOfCorrectClassifiedReviews = 0;

            DataboysDictionary dictionary = new DataboysDictionary();

            // for each movie review
            for (int i = 0; i < 2000; i++) {

                int positiveWords = 0;
                int negativeWords = 0;

                // retrieve data
                String review = data.get(i).stringValue(0);
                String intention = data.get(i).stringValue(1);

                StringTokenizer tokenizer = new StringTokenizer(review);

                // count sentiment words
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    if (dictionary.isPositiveWord(token)) {
                        positiveWords++;
                    } else if (dictionary.isNegativeWord(token)) {
                        negativeWords++;
                    }
                }

                // calculate intention
                if (positiveWords > negativeWords && intention.equals("pos")) {
                    numberOfCorrectClassifiedReviews++;
                } else if (negativeWords > positiveWords && intention.equals("neg")) {
                    numberOfCorrectClassifiedReviews++;
                }

                numberOfReviews++;
            }

            // print results
            double percentage = (numberOfCorrectClassifiedReviews / numberOfReviews) * 100;
            System.out.println("*** BASELINE ALGORITHM ***");
            System.out.println("Number of reviews: " + (int)numberOfReviews);
            System.out.println("Number of correct classified reviews: " + (int)numberOfCorrectClassifiedReviews);
            System.out.println("Accuracy: " + percentage + "%");
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Ends process if the file doesn't exist
     * @param file arff file
     */
    private void CheckExistsAndNotNull(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File must exists and must not be null");
        }
    }
}
