# Logic Gates Perceptron

This repository implements **perceptron models for logic gates** using Java. It contains:

- **SLP (Single-Layer Perceptron)** for the **AND gate**  
- **Linear SLP** for non-classification problems (e.g., regression: y = 2x + 1)  
- **MLP (Multi-Layer Perceptron)** for the **XOR gate**  

The project also includes tracking of the learning process and visualization of weights, biases, and outputs during training.

---

## Table of Contents

- [SLP AND](#slp-and)
- [SLP Linear](#slp-linear)
- [XOR Model](#xor-model)
- [Usage](#usage)
- [Observations](#observations)
- [License](#license)

---

## SLP AND

![AND +2 Threshold](src/main/resources/io/github/rajveer/logicgatesperceptron/andPositive2Thresh.png)
![AND -2 Threshold](src/main/resources/io/github/rajveer/logicgatesperceptron/andNegative2Thresh.png)

- A **single-layer perceptron** for the AND gate.  
- **Learning rate:** Can be increased to hasten learning but should be chosen cautiously; too high may prevent fine-tuning.  
- **Activation function:** Step function (0, 1) for classification.  

> Key idea: For classification problems, the perceptron outputs discrete values using a step function.

---

## SLP Linear

![Linear Unfinished](src/main/resources/io/github/rajveer/logicgatesperceptron/linearUnfinishedTrainSLP.png)
![Linear Finished](src/main/resources/io/github/rajveer/logicgatesperceptron/linearFinishedTrainSLP.png)

- For **non-classification / regression problems**.  
- **Regression function used for testing:** `y = 2x + 1`  
- **Activation function:** Identity (linear), not a step function.  

> Notes:
> - Step functions are avoided because the model must approximate a **continuous mapping** (`y = 2x + 1`).  
> - Regression problems use **linear output** (output = input) so that the network can learn continuous values.  
> - Using a nonlinear step function in regression would throw away useful information.

---

## XOR Model

![XOR Model](src/main/resources/io/github/rajveer/logicgatesperceptron/xorModel.png)

- **Multi-layer perceptron (MLP)** implemented to solve XOR, which cannot be solved by a single-layer perceptron.  
- Tracks **Mean Squared Error (MSE)** loss and weight/bias changes during training.

**Training Results:**  

**Final Predictions:**  

Epoch 0, MSE Loss: 0.308586  
Epoch 1000, MSE Loss: 0.252861

| Input      | Predicted | Target |
|-----------|-----------|--------|
| [0, 0]    | 0.4733    | 0.0    |
| [0, 1]    | 0.5152    | 1.0    |
| [1, 0]    | 0.5043    | 1.0    |
| [1, 1]    | 0.5232    | 0.0    |

![XOR MLP](src/main/resources/io/github/rajveer/logicgatesperceptron/xorMLP.png)

**Observations:**  

- Loss stabilizes around **0.25**, not decreasing further due to the simplicity of the network.  
- Weights like `w2[1]`, `w1[1][1]`, and output bias `b2` decrease while balancing the increasing `w2[0]`, `w1[0][0]`, and `w1[1][0]`.  
- Network finds an approximate solution, showing the limitations of small MLPs for XOR with sigmoid activations.

---

## Usage

1. Clone the repository:

```bash
git clone https://github.com/your-username/logic-gates-perceptron.git
