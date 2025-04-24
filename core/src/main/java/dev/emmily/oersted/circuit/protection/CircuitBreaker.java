package dev.emmily.oersted.circuit.protection;

import dev.emmily.oersted.hierarchy.Hierarchical;

/**
 * Represents a generic circuit breaker.
 * All circuit breakers should implement this interface to provide consistent access
 * to fundamental electrical protection parameters.
 */
public interface CircuitBreaker extends Hierarchical {
  /**
   * Returns the nominal current (In) of the circuit breaker in amperes.
   * This is the maximum continuous current the device is designed to carry.
   *
   * @return the nominal current in amperes.
   */
  int nominalCurrent();

  /**
   * Returns the operating frequency in hertz (Hz).
   *
   * @return the operating frequency.
   */
  int frequency();

  /**
   * Returns the number of poles of the circuit breaker.
   * Common values are 1, 2, 3, or 4, depending on the circuitType of system.
   *
   * @return the number of poles.
   */
  int poles();
}
