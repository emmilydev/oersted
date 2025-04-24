package dev.emmily.oersted.circuit.protection;

import java.util.Collections;
import java.util.List;

/**
 * Represents a Residual Current Circuit Breaker (RCCB).
 * These devices are designed to trip in the presence of a residual current, preventing
 * electrocution and reducing fire risk due to insulation faults.
 *
 * @param id                        The identifier.
 * @param nominalCurrent            The nominal current (In) in amperes.
 * @param frequency                 The operating frequency in hertz.
 * @param poles                     The number of poles (1P, 2P, 3P, 4P).
 * @param sensitivity               The residual current sensitivity in milliamperes (mA).
 * @param type                      The circuitType.
 */
public record ResidualCurrentCircuitBreaker(String id,
                                            String parent,
                                            int nominalCurrent,
                                            int frequency,
                                            int poles,
                                            int sensitivity,
                                            Type type) implements CircuitBreaker {
  public static ResidualCurrentCircuitBreaker create(String id,
                                                     String parent,
                                                     int nominalCurrent,
                                                     int frequency,
                                                     int poles,
                                                     int sensitivity,
                                                     Type type) {
    return new ResidualCurrentCircuitBreaker(id, parent, nominalCurrent, frequency, poles, sensitivity, type);
  }

  /**
   * Types of RCCBs, based on their response to different residual current waveforms.
   */
  public enum Type {
    /**
     * Type AC: Detects pure sinusoidal residual currents.
     */
    AC,

    /**
     * Type A: Detects sinusoidal and pulsating DC residual currents.
     */
    A,

    /**
     * Type B: Detects sinusoidal, pulsating, and smooth DC residual currents.
     */
    B,

    /**
     * Type F: Specialized for equipment with frequency inverters (e.g., washing machines).
     */
    F
  }
}
