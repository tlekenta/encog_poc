package pl.edu.wat;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class LetterService {

    private List<Letter> letters = new ArrayList<Letter>();

    public LetterService() {
        this.letters.add(initL());
        this.letters.add(initE());
        this.letters.add(initK());
        this.letters.add(initN());
        this.letters.add(initT());
    }

    public String getClosestLetter(double[] arr) {
        String result = "_";
        boolean matchFound = false;
        double error = 0.5;

        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > 1 - error && arr[i] < 1 + error) {
                if(matchFound) {
                    return "_";
                }
                result = letters.get(i).getSymbol();
                matchFound = true;
            }
        }

        return result;
    }

    private Letter initL() {
        Letter letterL = new Letter();
        letterL.setSymbol("L");
        letterL.setOutput(new double[] {1, 0, 0, 0, 0});

        double[] input1 = {1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
        double[] input2 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0};
        double[] input3 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0};
        double[] input4 = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0};

        letterL.getTrainingInputs().add(input1);
        letterL.getTrainingInputs().add(input2);
        letterL.getTrainingInputs().add(input3);
        letterL.getTestingInputs().add(input4);

        return letterL;
    }

    private Letter initE() {
        Letter letterE = new Letter();
        letterE.setSymbol("E");
        letterE.setOutput(new double[] {0, 1, 0, 0, 0});

        double[] input1 = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0};
        double[] input2 = {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
        double[] input3 = {1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1};
        double[] input4 = {0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0};

        letterE.getTrainingInputs().add(input1);
        letterE.getTrainingInputs().add(input2);
        letterE.getTrainingInputs().add(input3);
        letterE.getTestingInputs().add(input4);

        return letterE;
    }

    private Letter initK() {
        Letter letterK = new Letter();
        letterK.setSymbol("K");
        letterK.setOutput(new double[] {0, 0, 1, 0, 0});

        double[] input1 = {1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0};
        double[] input2 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0};
        double[] input3 = {0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0};
        double[] input4 = {1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0};

        letterK.getTrainingInputs().add(input1);
        letterK.getTrainingInputs().add(input2);
        letterK.getTrainingInputs().add(input3);
        letterK.getTestingInputs().add(input4);

        return letterK;
    }

    private Letter initN() {
        Letter letterN = new Letter();
        letterN.setSymbol("N");
        letterN.setOutput(new double[] {0, 0, 0, 1, 0});

        double[] input1 = {1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1};
        double[] input2 = {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1};
        double[] input3 = {0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1};
        double[] input4 = {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 1, 0};

        letterN.getTrainingInputs().add(input1);
        letterN.getTrainingInputs().add(input2);
        letterN.getTrainingInputs().add(input3);
        letterN.getTestingInputs().add(input4);

        return letterN;
    }

    private Letter initT() {
        Letter letterT = new Letter();
        letterT.setSymbol("T");
        letterT.setOutput(new double[] {0, 0, 0, 0, 1});

        double[] input1 = {1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0};
        double[] input2 = {1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0};
        double[] input3 = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0};
        double[] input4 = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0};

        letterT.getTrainingInputs().add(input1);
        letterT.getTrainingInputs().add(input2);
        letterT.getTrainingInputs().add(input3);
        letterT.getTestingInputs().add(input4);

        return letterT;
    }


}
