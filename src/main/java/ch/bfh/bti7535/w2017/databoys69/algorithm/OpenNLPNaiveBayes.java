package ch.bfh.bti7535.w2017.databoys69.algorithm;

import opennlp.tools.doccat.*;
import opennlp.tools.ml.naivebayes.NaiveBayesTrainer;
import opennlp.tools.util.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OpenNLPNaiveBayes {



    public static void doStuff(File trainingData, File testData)
    {
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
            // BufferedOutputStream modelOut = new BufferedOutputStream(new FileOutputStream("C:\\Users\\liwan\\Documents\\classifier-naive-bayes.bin"));
            // model.serialize(modelOut);

            DocumentCategorizer docCat = new DocumentCategorizerME(model);

            // Todo -> test the trained data
        }
        catch (IOException e) {
            // Failed to read or parse training data, training failed
            e.printStackTrace();
        }
    }

}
