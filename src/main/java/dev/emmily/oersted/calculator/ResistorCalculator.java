package dev.emmily.oersted.calculator;

import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

public interface ResistorCalculator {
  static double sumParallelResistors(double resistor1, double resistor2) {
    return 1 / reduceResistors(new double[]{resistor1, resistor2}, resistor -> 1.0 / resistor, Double::sum);
  }

  static double sumParallelResistors(double... resistors) {
    return 1 / reduceResistors(resistors, resistor -> 1.0 / resistor, Double::sum);
  }

  static double sumSeriesResistors(double... resistors) {
    return reduceResistors(resistors, DoubleUnaryOperator.identity(), Double::sum);
  }

  static double reduceResistors(double[] resistors, DoubleUnaryOperator transformer, DoubleBinaryOperator reducer) {
    if (resistors.length < 2) {
      throw new IllegalArgumentException("at least 2 resistors are required");
    }

    double result = 0;

    for (double resistor : resistors) {
      if (resistor <= 0) {
        throw new IllegalArgumentException("resistor value must be greater than 0");
      }

      result = reducer.applyAsDouble(result, transformer.applyAsDouble(resistor));
    }

    return result;
  }
}
