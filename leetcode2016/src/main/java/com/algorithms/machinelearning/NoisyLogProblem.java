package com.algorithms.machinelearning;

/**
 * Print necessary logs by suppressing out noisy logs.
 * Input: 
 * Set of numbers - Each indicating the number of log statements from each log source. 
 * Log Space - Number of log statements that can be printed out on console at a time. 
 *
 * Output: A number of log statements, that needs to be taken from logs so we best utilize the available console space. Not exceeding the available space. 
 *
 * @author Ram Komma
 *
 */
public class NoisyLogProblem {

  /**
   * @param args
   */
  public static void main(String[] args) {
    int[] samples = {20, 60, 90, 300, 1000};
    NoisyLogProblem logger = new NoisyLogProblem();
    int logNumber = logger.logNumberHypothesis(samples, 300);
    System.out.println(logNumber);
  }

  public int logNumberHypothesis(int[] samples, int logSpace) {
    int sum = 0;
    int cost = 0;
    int hypothesis = -1;

    for (int sample : samples) {
      sum += sample;
    }

    if (sum > logSpace) {
      //Initial hypothesis.
      hypothesis = logSpace / samples.length;
      do {
        cost = costFunction(hypothesis, samples, logSpace);
        hypothesis = hypothesis + cost;
      } while (cost > 0);
    } else {
      return sum / samples.length;
    }

    return hypothesis;
  }

  private int costFunction(int hypothesis, int[] samples, double logSpace) {
    double cost = 0;
    double logsPrinted = 0;
    for (int sample : samples) {
      logsPrinted = logsPrinted + Math.min(sample, hypothesis);
    }

    cost = (logSpace - logsPrinted) / samples.length;
    return (int) Math.round(cost);
  }
}
