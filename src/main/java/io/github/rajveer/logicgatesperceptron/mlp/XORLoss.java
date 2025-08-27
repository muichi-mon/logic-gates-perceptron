package io.github.rajveer.logicgatesperceptron.mlp;

public class XORLoss {

    public static double mseLoss(double[] predictions, double[] targets) {
        double sum = 0.0;
        for (int i = 0; i < predictions.length; i++) {
            double diff = targets[i] - predictions[i];
            sum += diff * diff;
        }
        return sum / predictions.length;
    }

    public static double binaryCrossEntropy(double[] predictions, double[] targets) {
        double sum = 0.0;
        for (int i = 0; i < predictions.length; i++) {
            double y = targets[i];
            double yHat = predictions[i];

            // add small epsilon to avoid log(0)
            double eps = 1e-10;
            yHat = Math.max(eps, Math.min(1 - eps, yHat));

            sum += y * Math.log(yHat) + (1 - y) * Math.log(1 - yHat);
        }
        return -sum / predictions.length;
    }


}
