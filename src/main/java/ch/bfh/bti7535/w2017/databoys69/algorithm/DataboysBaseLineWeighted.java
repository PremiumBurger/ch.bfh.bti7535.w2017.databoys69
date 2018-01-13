package ch.bfh.bti7535.w2017.databoys69.algorithm;

import ch.bfh.bti7535.w2017.databoys69.filters.DataboysDictionary;
import ch.bfh.bti7535.w2017.databoys69.sentiwordnet.SentiwordDictionary;
import weka.core.Instance;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class DataboysBaseLineWeighted implements Runnable {

    private File dataSet;
    private SentiwordDictionary dictionary;

    /**
     * Creates an Instance of a @{@link DataboysBaseLineWeighted} algorithm
     */
    public DataboysBaseLineWeighted(File inputFile) {
        CheckExistsAndNotNull(inputFile);
        this.dataSet = inputFile;
        try {
            this.dictionary = new SentiwordDictionary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs Databoys Weighted Baseline algorithm
     */
    public void run() {

        try {

            Instances data = new Instances(new BufferedReader(new FileReader(dataSet)));

            double numberOfReviews = 0;
            double numberOfCorrectClassifiedReviews = 0;

            // loop over every data row
            for (int i = 0; i < 2000; i++) {

                String review = data.get(i).stringValue(0);
                String intention = data.get(i).stringValue(1);
                StringTokenizer tokenizer = new StringTokenizer(review);

                numberOfReviews++;

                double weight = 0.0;

                // count sentiment words
                while (tokenizer.hasMoreTokens()) {
                    String token = tokenizer.nextToken();
                    if (dictionary.contains(token)) {
                        weight += dictionary.extract(token);
                    }
                }

                // calculate intention
                if (weight >= 0 && intention.equals("pos")) {
                    numberOfCorrectClassifiedReviews++;
                } else if (weight < 0 && intention.equals("neg")) {
                    numberOfCorrectClassifiedReviews++;
                }
            }

            // show results
            double percentage = (numberOfCorrectClassifiedReviews / numberOfReviews) * 100;
            System.out.println("*** DATABOYS 69 WEIGHTED BASELINE ***");
            System.out.println("Number of reviews: " + numberOfReviews);
            System.out.println("Number of correct classified reviews: " + numberOfCorrectClassifiedReviews);
            System.out.println("Accuracy: " + percentage + "%");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void CheckExistsAndNotNull(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File must exists and must not be null");
        }
    }
}
