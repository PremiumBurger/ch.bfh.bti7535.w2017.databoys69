# Databoys69 • Data Science (BTI7535)

This project was realized for the data science course of the Bern Universitiy of Applied Sciences.

## Authors
* Yves Beutler
* Sascha Wittwer
* Cédric von Allmen


## Run it!
The project ist based on maven. To run the project make sure maven is installed and simply run:

```mvn clean install```

The entry point (main class) of the application can be found in this class:

```ch.bfh.bti7535.w2017.databoys69.App```

If you run this class, several task will be executed:
* Baseline algorithm (unweighted) 
* Baseline algorithm (weighted)
* OpenNLP Naive Bayes
* WEKA Naive Bayes

In the console output you will see the results for every execution.

### Change input data
The program runs weka algorithms with our pre-generated ARFF-files.
If you want to change the input data, place the file in:

```src/main/resources```

folder and make sure to update the path in the ```App.java``` class. 
Also make sure your ARFF-File has got the same format. (pos/neg attribute classification).






