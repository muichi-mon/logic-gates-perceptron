package io.github.rajveer.logicgatesperceptron.mlp;

import io.github.rajveer.fxplot.Figure;

import java.util.ArrayList;
import java.util.List;

public class XORMain {

    public static void main(String[] args) {

        XORModel model = new XORModel();

        // XOR input-output pairs
        double[][] inputs = {
                {0, 0},
                {0, 1},
                {1, 0},
                {1, 1}
        };
        double[] targets = {0, 1, 1, 0};

        int epochs = 2000;

        // Tracking lists
        List<double[]> weight00Series = new ArrayList<>();
        List<double[]> weight01Series = new ArrayList<>();
        List<double[]> weight10Series = new ArrayList<>();
        List<double[]> weight11Series = new ArrayList<>();
        List<double[]> hiddenBiasSeries = new ArrayList<>();
        List<double[]> outputWeightSeries0 = new ArrayList<>();
        List<double[]> outputWeightSeries1 = new ArrayList<>();
        List<double[]> outputBiasSeries = new ArrayList<>();
        List<double[]> lossSeries = new ArrayList<>();

        double iteration = 0;

        for (int epoch = 0; epoch < epochs; epoch++) {
            double[] predictions = new double[4];

            for (int i = 0; i < inputs.length; i++) {
                iteration++;

                predictions[i] = model.forward(inputs[i]);
                model.backward(inputs[i], targets[i]);

                // Track hidden layer weights (input->hidden)
                weight00Series.add(new double[]{iteration, model.W1[0][0]});
                weight01Series.add(new double[]{iteration, model.W1[0][1]});
                weight10Series.add(new double[]{iteration, model.W1[1][0]});
                weight11Series.add(new double[]{iteration, model.W1[1][1]});

                // Track hidden biases
                hiddenBiasSeries.add(new double[]{iteration, model.b1[0]});
                hiddenBiasSeries.add(new double[]{iteration, model.b1[1]});

                // Track hidden->output weights
                outputWeightSeries0.add(new double[]{iteration, model.W2[0]});
                outputWeightSeries1.add(new double[]{iteration, model.W2[1]});

                // Track output bias
                outputBiasSeries.add(new double[]{iteration, model.b2});
            }

            // Compute MSE loss for this epoch
            double loss = XORLoss.mseLoss(predictions, targets);
            lossSeries.add(new double[]{iteration, loss});

            // Print every 1000 epochs
            if (epoch % 1000 == 0) {
                System.out.printf("Epoch %d, MSE Loss: %.6f%n", epoch, loss);
            }
        }

        // Print final predictions
        System.out.println("\nFinal predictions:");
        for (int i = 0; i < inputs.length; i++) {
            double yhat = model.forward(inputs[i]);
            System.out.printf("Input: [%d, %d] -> Predicted: %.4f, Target: %.1f%n",
                    (int)inputs[i][0], (int)inputs[i][1], yhat, targets[i]);
        }

        // Plotting weights, biases, and loss
        Figure figure = new Figure("XOR MLP Training Tracker", "l");
        figure.setXLabel("Iterations");
        figure.setYLabel("Value");

        figure.addNumericSeries("W1[0][0]", weight00Series);
        figure.addNumericSeries("W1[0][1]", weight01Series);
        figure.addNumericSeries("W1[1][0]", weight10Series);
        figure.addNumericSeries("W1[1][1]", weight11Series);
        //figure.addNumericSeries("Hidden Biases", hiddenBiasSeries);
        figure.addNumericSeries("W2[0]", outputWeightSeries0);
        figure.addNumericSeries("W2[1]", outputWeightSeries1);
        figure.addNumericSeries("Output Bias", outputBiasSeries);
        figure.addNumericSeries("MSE Loss", lossSeries);

        figure.show();
    }
}
