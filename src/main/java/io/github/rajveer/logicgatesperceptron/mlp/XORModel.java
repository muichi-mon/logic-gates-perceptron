package io.github.rajveer.logicgatesperceptron.mlp;

public class XORModel {

    public double[][] W1;  // weights input → hidden (2x2)
    public double[] b1;    // biases for hidden layer (2)

    double[] hidden; // hidden layer activations

    public double[] W2;  // weights hidden → output (2x1)
    public double b2; // bias for output (1)

    double output;

    double learningRate = 0.1;

    public XORModel() {

        // Initialize weights and biases with random values
        W1 = new double[][]{
                {Math.random(), Math.random()}, // weights from input[0]
                {Math.random(), Math.random()}  // weights from input[1]
        };

        b1 = new double[]{Math.random(), Math.random()};

        W2 = new double[]{Math.random(), Math.random()};
        b2 = Math.random();
    }

    // sigmoid activation
    private double sigmoid(double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }

    // forward function
    public double forward(double[] x) {
        hidden = new double[2];

        // hidden layer
        for (int i = 0; i < 2; i++) {
            hidden[i] = W1[0][i] * x[0] + W1[1][i] * x[1] + b1[i];
            hidden[i] = sigmoid(hidden[i]);
        }

        // output layer
        double z2 = W2[0] * hidden[0] + W2[1] * hidden[1] + b2;
        output = sigmoid(z2);

        return output;
    }

    // backward function
    public void backward(double[] x, double target) {
        // ----- Output delta -----
        double delta_o = (output - target) * output * (1 - output);

        // ----- Hidden deltas -----
        double[] delta_h = new double[2];
        for (int i = 0; i < 2; i++) {
            double h = hidden[i];
            delta_h[i] = W2[i] * delta_o * h * (1 - h);
        }

        // ----- Update weights hidden → output -----
        for (int i = 0; i < 2; i++) {
            W2[i] -= learningRate * delta_o * hidden[i];
        }
        b2 -= learningRate * delta_o;

        // ----- Update weights input → hidden -----
        for (int i = 0; i < 2; i++) {          // hidden neuron index
            for (int j = 0; j < 2; j++) {      // input index
                W1[j][i] -= learningRate * delta_h[i] * x[j];
            }
            b1[i] -= learningRate * delta_h[i];
        }
    }

}