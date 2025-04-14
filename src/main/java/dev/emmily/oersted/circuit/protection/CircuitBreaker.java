package dev.emmily.oersted.circuit.protection;

import java.util.List;

/**
 * Represents a generic circuit breaker.
 * All circuit breakers should implement this interface to provide consistent access
 * to fundamental electrical protection parameters.
 */
public interface CircuitBreaker {
  /**
   * Returns the unique identifier for the circuit breaker.
   *
   * @return the identifier
   */
  String id();

  /**
   * Returns the identifier of the upstream circuit breaker.
   * This represents the protective device located electrically before (towards the power source)
   * this one in the installation hierarchy.
   *
   * @return the identifier of the upstream circuit breaker.
   */
  String upstreamCircuitBreaker();

  /**
   * Returns the identifiers of the downstream circuit breakers.
   * Represents the protective devices or loads located electrically after this one.
   *
   * @return the list of downstream circuit breakers.
   */
  List<String> downstreamCircuitBreakers();

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
   * Common values are 1, 2, 3, or 4, depending on the type of system.
   *
   * @return the number of poles.
   */
  int poles();
}
