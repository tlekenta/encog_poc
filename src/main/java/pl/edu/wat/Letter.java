package pl.edu.wat;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Letter {

    private List<double[]> trainingInputs = new ArrayList<double[]>();

    private List<double[]> testingInputs = new ArrayList<double[]>();

    private double[] output;

    private String symbol;
}
