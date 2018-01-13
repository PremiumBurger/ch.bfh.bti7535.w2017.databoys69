package ch.bfh.bti7535.w2017.databoys69.algorithm;

import ch.bfh.bti7535.w2017.databoys69.filters.DataboysDictionary;
import weka.core.Instance;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

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
     * Runs Databoys Baseline algorithm
     */
    public void run() {
        FileReader fReader = null;
        BufferedReader bReader = null;
        try {

            fReader = new FileReader(dataSet);
            bReader = new BufferedReader(fReader);
            Instances data = new Instances(bReader);

            double numberOfReviews = 0;
            double numberOfCorrectClassifiedReviews = 0;

            DataboysDictionary dictionary = new DataboysDictionary();

            // loop over every data row
            for (int i = 0; i < 2000; i++) {

                String review = data.get(i).stringValue(0);
                String intention = data.get(i).stringValue(1);
                StringTokenizer tokenizer = new StringTokenizer(review);

                numberOfReviews++;

                int positiveWords = 0;
                int negativeWords = 0;

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
            }

            // show results
            double percentage = (numberOfCorrectClassifiedReviews / numberOfReviews) * 100;
            System.out.println("*** DATABOYS 69 BASELINE ***");
            System.out.println("Number of reviews: " + numberOfReviews);
            System.out.println("Number of correct classified reviews: " + numberOfCorrectClassifiedReviews);
            System.out.println("Accuracy: " + percentage + "%");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bReader.close();
                fReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void CheckExistsAndNotNull(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File must exists and must not be null");
        }
    }
}
