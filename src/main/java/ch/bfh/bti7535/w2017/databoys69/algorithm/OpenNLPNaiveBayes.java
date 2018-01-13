package ch.bfh.bti7535.w2017.databoys69.algorithm;

import opennlp.tools.doccat.*;
import opennlp.tools.ml.naivebayes.NaiveBayesTrainer;
import opennlp.tools.util.*;
import weka.core.Instances;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @author databoys69
 * Implementation of a Naive Bayes algorithm with the OpenNLP library.
 */
public class OpenNLPNaiveBayes {

    private String _modelPath = ".\\trainedModel.bin";

    /**
     * Train the Naive bayes
     */
    public void train()
    {
        // load training file
        ClassLoader classLoader = this.getClass().getClassLoader();
        File trainingData = new File(classLoader.getResource("openNLP_training.txt").getFile());

        DoccatModel model;
        InputStreamFactory dataIn;
        try {
            dataIn = new MarkableFileInputStreamFactory(trainingData);
            ObjectStream<String> lineStream =
                    new PlainTextByLineStream(dataIn, "UTF-8");
            ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);


            TrainingParameters params = new TrainingParameters();
            params.put(TrainingParameters.CUTOFF_PARAM, Integer.toString(0));
            params.put(TrainingParameters.ALGORITHM_PARAM, NaiveBayesTrainer.NAIVE_BAYES_VALUE);

            DoccatFactory factory = new DoccatFactory();

            // Now the parameter TrainingParameters.ALGORITHM_PARAM ensures
            // that we train a Naive Bayes model instead
            model = DocumentCategorizerME.train("en", sampleStream, params, factory);
            // Comment in if you want to export the model for later use
            BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream(_modelPath));
            model.serialize(modelOut);
        }
        catch (IOException e) {
            // Failed to read or parse training data, training failed
            e.printStackTrace();
        }
    }

    /**
     * test the accuracy
     * @param dataSet
     */
    public void test(File dataSet) {

        FileReader fReader = null;
        BufferedReader bReader = null;
        try {

            fReader = new FileReader(dataSet);
            bReader = new BufferedReader(fReader);
            Instances data = new Instances(bReader);

            // Init evaluator
            InputStream stream = new FileInputStream(_modelPath);
            DoccatModel model = new DoccatModel(stream);
            DocumentCategorizerME docCat = new DocumentCategorizerME(model);
            DocumentCategorizerEvaluator evaluator = new DocumentCategorizerEvaluator(docCat);

            // Evaluate each review
            for (int i = 0; i < 2000; i++) {
                String review = data.get(i).stringValue(0);
                String intention = data.get(i).stringValue(1);
                StringTokenizer tokenizer = new StringTokenizer(review);
                ArrayList<String> tokens = new ArrayList<>();
                while (tokenizer.hasMoreTokens()){
                    tokens.add(tokenizer.nextToken());
                }

                DocumentSample sample = new DocumentSample(intention, tokens.stream().toArray(String[]::new));
                evaluator.evaluateSample(sample);
            }

            System.out.println("*** NAIVE BAYES (OpenNLP) ALGORITHM ***");
            System.out.println();
            System.out.println("Number of reviews: " + evaluator.getDocumentCount());
            System.out.println("Accuracy: " + (evaluator.getAccuracy() * 100));
            System.out.println();
            System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - ");
            System.out.println();
        } catch (Exception e) {
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
}
