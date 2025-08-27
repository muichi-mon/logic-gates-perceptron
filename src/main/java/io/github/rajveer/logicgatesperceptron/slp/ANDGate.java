package io.github.rajveer.logicgatesperceptron.slp;

import io.github.rajveer.fxplot.Figure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ANDGate {

    public static void main(String[] args) {

        // Trackers
        double loopCounter = 0;
        List<double[]> epochWeight0Series = new ArrayList<>();
        List<double[]> epochWeight1Series = new ArrayList<>();
        List<double[]> epochBiasSeries = new ArrayList<>();

        // AND Gate Example
        List<double[]> andInputs = new ArrayList<>();
        andInputs.add(new double[]{0, 0});
        andInputs.add(new double[]{0, 1});
        andInputs.add(new double[]{1, 0});
        andInputs.add(new double[]{1, 1});

        List<Double> andOutputs = new ArrayList<>();
        andOutputs.add(0.0);
        andOutputs.add(0.0);
        andOutputs.add(0.0);
        andOutputs.add(1.0);

        // Initialize random weights, bias and learning rate
        double[] andWeights = {Math.random(), Math.random()};
        System.out.println("Initial input weights: " + Arrays.toString(andWeights));
        double andBias = 0.5;
        System.out.println("Initial bias: " + andBias);
        double andBiasWeight = Math.random();
        System.out.println("Initial bias weight: " + andBiasWeight);
        double andLearningRate = 0.1;

        // Track
        epochWeight0Series.add(new double[]{0, andWeights[0]});
        epochWeight1Series.add(new double[]{0, andWeights[1]});
        epochBiasSeries.add(new double[]{0, andBias});

        for (int epoch = 0; epoch < 50; epoch++) {

            for (int i = 0; i < andInputs.size(); i++) {

                // Track weights and bias for each epoch
                loopCounter++;

                System.out.println("\nEpoch: " + epoch + " Input: " + Arrays.toString(andInputs.get(i)));
                double weightSum = summationFunction(andInputs.get(i), andWeights);
                double target = andOutputs.get(i);

                double output = activationFunction(weightSum + andBias);
                System.out.println("Weight Sum: " + weightSum + " Output: " + output + " Target: " + target);

                double error = target - output;
                System.out.println("Error: " + error);

                // Update weights and bias
                for (int j = 0; j < andWeights.length; j++) {
                    andWeights[j] += andLearningRate * error * andInputs.get(i)[j];
                }

                andBias += andLearningRate * error * andBiasWeight;

                System.out.println("Updated Weights: " + Arrays.toString(andWeights) + " Updated Bias: " + andBias);
                epochWeight0Series.add(new double[]{loopCounter, andWeights[0]});
                epochWeight1Series.add(new double[]{loopCounter, andWeights[1]});
                epochBiasSeries.add(new double[]{loopCounter, andBias});
            }
        }

        System.out.println("\nTraining complete!");
        System.out.println("Total epochs: " + loopCounter/andInputs.size());
        System.out.println("Final input weights: " + Arrays.toString(andWeights));
        System.out.println("Final bias: " + andBias);

        Figure figure = new Figure("Perceptron Training Tracker", "l");
        figure.setXLabel("Epochs");
        figure.setYLabel("Weight");

        figure.addNumericSeries("Weight 0", epochWeight0Series);
        figure.addNumericSeries("Weight 1", epochWeight1Series);
        figure.addNumericSeries("Bias", epochBiasSeries);

        figure.show();
    }

    private static double summationFunction(double[] inputs, double[] weights) {
        double sum = 0.0;
        for (int i = 0; i < inputs.length; i++) {
            sum += inputs[i] * weights[i];
        }
        return sum;
    }

    private static double activationFunction(double x) {
        return x >= 2 ? 1.0 : 0.0;
        // Step function : Threshold can be any positive value (adjusted according to no. of epochs available to reach the threshold at current learning rate)
        // If, -ve threshold, the error has to be direction based (i.e. absolute value of error won't work)
    }
}
