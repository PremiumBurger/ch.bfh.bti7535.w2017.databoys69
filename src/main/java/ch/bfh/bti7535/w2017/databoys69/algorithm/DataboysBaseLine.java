package ch.bfh.bti7535.w2017.databoys69.algorithm;

import java.io.File;

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

        // 1. load resource file (movie-reviews.csv)

        // 1.a create positiv / negativ word

        // 2. loop over each entry

        // 3. count sentiment words

    }

    private void CheckExistsAndNotNull(File file) {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File must exists and must not be null");
        }
    }
}
