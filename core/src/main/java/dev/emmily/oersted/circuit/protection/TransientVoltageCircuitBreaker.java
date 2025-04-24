package dev.emmily.oersted.circuit.protection;

import java.util.List;

/**
 * Represents a Transient Voltage Circuit Breaker (TVCB).
 * These devices are designed to protect against transient overvoltages, often caused
 * by lightning strikes or switching operations. Usually paired with surge protection devices (SPDs).
 *
 * @param id                        The identifier.
 * @param nominalCurrent            The nominal current (In) in amperes.
 * @param frequency                 The operating frequency in hertz.
 * @param poles                     The number of poles.
 */
public record TransientVoltageCircuitBreaker(String id,
                                             String parent,
                                             int nominalCurrent,
                                             int frequency,
                                             int poles) implements CircuitBreaker {
  // TODO: Add voltage clamping specs, response time, and classification (Type 1, 2, 3)
}
