package ch.bfh.bti7535.w2017.databoys69.algorithm;

import opennlp.tools.doccat.*;
import opennlp.tools.ml.naivebayes.NaiveBayesTrainer;
import opennlp.tools.util.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class OpenNLPNaiveBayes {

    private String _modelPath = ".\\trainedModel.bin";

    public void train(File data)
    {
        DoccatModel model;
        InputStreamFactory dataIn;
        try {
            dataIn = new MarkableFileInputStreamFactory(data);
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

            DocumentCategorizer docCat = new DocumentCategorizerME(model);
        }
        catch (IOException e) {
            // Failed to read or parse training data, training failed
            e.printStackTrace();
        }
    }

    public void test(String cat, String content) throws IOException {
        InputStream stream = new FileInputStream(_modelPath);
        DoccatModel model = new DoccatModel(stream);
        DocumentCategorizerME docCat = new DocumentCategorizerME(model);
        DocumentCategorizerEvaluator evaluator = new DocumentCategorizerEvaluator(docCat);

        DocumentSample sample = new DocumentSample(cat, content.split(" "));
        double[] catProbability = docCat.categorize(content.split(" "));
        String category = docCat.getBestCategory(catProbability);

        evaluator.evaluateSample(sample);

        double accuracy = evaluator.getAccuracy();
        System.out.println("Category predication: " + category);
        System.out.println("Accuracy: " + accuracy);

    }

}
