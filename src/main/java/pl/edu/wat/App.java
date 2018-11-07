package pl.edu.wat;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.factory.method.RBFNetworkFactory;
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

        train(network, trainingSet);

        test(network, testingSet);

        Encog.getInstance().shutdown();

    }

    private static void train(BasicNetwork network, MLDataSet trainingSet) {
        ResilientPropagation train = new ResilientPropagation(network, trainingSet);

        int epoch = 1;

        do {
            train.iteration();
            System.out.println(MessageFormat.format("Epoka #{0} Błąd: {1}", epoch, train.getError()));
            epoch++;
        } while (train.getError() > 0.00000001);

        train.finishTraining();
    }

    private static void test(BasicNetwork network, MLDataSet testingSet) {
        for(MLDataPair pair: testingSet) {
            MLData output = network.compute(pair.getInput());

            System.out.print(MessageFormat
                    .format("wynik = {0}, idealny = {1}: ",
                            Utill.doubleArrayToString(output.getData()),
                            Utill.doubleArrayToString(pair.getIdeal().getData())));
            System.out.println(MessageFormat.format("{0} -> {1}",
                    letterService.getClosestLetter(pair.getIdeal().getData()),
                    letterService.getClosestLetter(output.getData())));
        }
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
