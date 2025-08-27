package io.github.rajveer.logicgatesperceptron.slp;

import io.github.rajveer.fxplot.Figure;

import java.util.ArrayList;
import java.util.List;

public class LinearFunction {

    final static double LEARNING_RATE = 0.01;

    public static void main(String[] args) {

        // Trackers
        double loopCounter = 0;
        List<double[]> epochWeightSeries = new ArrayList<>();
        List<double[]> epochBiasSeries = new ArrayList<>();

        // y = 2x + 1
        double[] inputs = {0, 1, 2, 3, 4, 5};
        double[] targets = new double[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            targets[i] = 2 * inputs[i] + 1;
        }

        double weight = Math.random() * 2 - 1;
        double bias = Math.random() * 2 - 1;

        double biasWeight = Math.random();


        // Track
        epochWeightSeries.add(new double[]{0, weight});
        epochBiasSeries.add(new double[]{0, bias});

        for (int epoch = 0; epoch < 1000; epoch++) {

            for (int i = 0; i < inputs.length; i++) {

                // Track weights and bias for each epoch
                loopCounter++;

                System.out.println("\nEpoch: " + epoch + " Input: " + inputs[i]);
                double weightSum = summationFunction(new double[]{inputs[i]}, new double[]{weight});
                double target = targets[i];

                double output = weightSum + bias;
                // double output = activationFunction(weightSum + bias);
                System.out.println("Weight Sum: " + weightSum + " Output: " + output + " Target: " + target);

                double error = target - output;
                System.out.println("Error: " + error);

                // Update
                weight += LEARNING_RATE*error*inputs[i];
                bias += LEARNING_RATE*error* biasWeight;

                System.out.println("Updated Weight: " + weight + " Updated Bias: " + bias);
                epochWeightSeries.add(new double[]{loopCounter, weight});
                epochBiasSeries.add(new double[]{loopCounter, bias});
            }
        }

        System.out.println("\nTraining complete!");
        System.out.println("Total epochs: " + loopCounter/ inputs.length);
        System.out.println("Final input weight: " + weight);
        System.out.println("Final bias: " + bias);

        Figure figure = new Figure("Perceptron Training Tracker", "l");
        figure.setXLabel("Epochs");
        figure.setYLabel("Weight");

        figure.addNumericSeries("Weight", epochWeightSeries);
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

    // ACTIVATION FUNCTION IS USED FOR CLASSIFICATION, W/O PERCEPTRON IS A LINEAR FUNCTION AND NOT CLASSIFIER

    // Because you want the perceptron to approximate a continuous linear mapping, applying a step function (or any nonlinear activation) would throw away useful information and prevent learning.
    // You’re solving a regression problem, not a classification problem.
    // In regression, the “activation” is typically the identity function (output = input).

}
