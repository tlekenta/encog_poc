package pl.edu.wat;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

import java.text.MessageFormat;

public class App {

    private static LetterService letterService = new LetterService();

    public static void main(String[] args) {
        BasicNetwork network = configureNetwork();

        MLDataSet trainingSet = prepareTrainingSet();

        MLDataSet testingSet = prepareTestingSet();

        int trainsCount = 0;
        do {
            trainsCount++;
            train(network, trainingSet);
        } while (test(network, testingSet));

        Encog.getInstance().shutdown();

        System.out.println(MessageFormat.format("Ukończono uczenie po {0} cyklach", trainsCount));

    }

    private static void train(BasicNetwork network, MLDataSet trainingSet) {
        network.reset();
        ResilientPropagation train = new ResilientPropagation(network, trainingSet);

        int epoch = 1;

        do {
            train.iteration();
            System.out.println(MessageFormat.format("Epoka #{0} Błąd: {1}", epoch, train.getError()));
            epoch++;
        } while (train.getError() > 0.0001 || epoch > 100000);

        train.finishTraining();
    }

    private static boolean test(BasicNetwork network, MLDataSet testingSet) {
        boolean result = true;
        for(MLDataPair pair: testingSet) {
            MLData output = network.compute(pair.getInput());
            String ideal = letterService.getClosestLetter2(pair.getIdeal().getData());
            String closestLetter2 = letterService.getClosestLetter2(output.getData());
            String closestLetter = letterService.getClosestLetter(output.getData());

            System.out.print(MessageFormat
                    .format("wynik = {0}, idealny = {1}: ",
                            Utill.doubleArrayToString(output.getData()),
                            Utill.doubleArrayToString(pair.getIdeal().getData())));
            System.out.println(MessageFormat.format("{0} -> {1} -> {2}",
                    ideal, closestLetter2, closestLetter));

            result &= ideal.equals(closestLetter) || ideal.equals(closestLetter2);
        }

        return !result;
    }

    private static BasicNetwork configureNetwork() {
        BasicNetwork network = new BasicNetwork () ;
        network.addLayer(new BasicLayer(null, true , 36 )) ;
        network.addLayer(new BasicLayer(new ActivationSigmoid(),true,36 )) ;
        network.addLayer(new BasicLayer(new ActivationSigmoid(),false,5 )) ;
        network.getStructure().finalizeStructure();
        network.reset();
        return network;
    }

    private static MLDataSet prepareTrainingSet() {
        MLDataSet trainingSet = new BasicMLDataSet();

        for(Letter letter: letterService.getLetters()) {
            for (double[] input: letter.getTrainingInputs()) {
                trainingSet.add(new BasicMLData(input), new BasicMLData(letter.getOutput()));
            }
        }

        return trainingSet;
    }

    private static MLDataSet prepareTestingSet() {
        MLDataSet testingSet = new BasicMLDataSet();

        for(Letter letter: letterService.getLetters()) {
            for (double[] input: letter.getTestingInputs()) {
                testingSet.add(new BasicMLData(input), new BasicMLData(letter.getOutput()));
            }
        }

        return testingSet;
    }

}
