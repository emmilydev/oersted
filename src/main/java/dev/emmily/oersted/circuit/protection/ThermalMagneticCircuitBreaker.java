package dev.emmily.oersted.circuit.protection;

import java.util.Collections;
import java.util.List;

/**
 * Represents a Thermal-Magnetic Circuit Breaker (TMCB).
 * These breakers provide dual protection:
 * thermal protection against overloads and magnetic protection against short circuits.
 *
 * @param id                        The identifier.
 * @param upstreamCircuitBreaker    The identifier of the upstream protective device.
 * @param downstreamCircuitBreakers the identifiers of the downstream circuit breakers.
 * @param nominalCurrent            The nominal current (In) in amperes.
 * @param frequency                 The operating frequency in hertz.
 * @param poles                     The number of poles.
 * @param shortCircuitCurrent       The maximum short circuit current (Icc) in kiloamperes (kA).
 * @param tripCurve                 The tripping characteristic curve.
 */
public record ThermalMagneticCircuitBreaker(String id,
                                            String upstreamCircuitBreaker,
                                            List<String> downstreamCircuitBreakers,
                                            int nominalCurrent,
                                            int frequency,
                                            int poles,
                                            int shortCircuitCurrent,
                                            TripCurve tripCurve) implements CircuitBreaker {
  public static ThermalMagneticCircuitBreaker create(String id,
                                                     String upstreamCircuitBreaker,
                                                     List<String> downstreamCircuitBreakers,
                                                     int nominalCurrent,
                                                     int frequency,
                                                     int poles,
                                                     int shortCircuitCurrent,
                                                     TripCurve tripCurve) {
    return new ThermalMagneticCircuitBreaker(
      id, upstreamCircuitBreaker, downstreamCircuitBreakers,
      nominalCurrent, frequency, poles, shortCircuitCurrent, tripCurve
    );
  }

  public static ThermalMagneticCircuitBreaker create(String id,
                                                     String upstreamCircuitBreaker,
                                                     String downstreamCircuitBreaker,
                                                     int nominalCurrent,
                                                     int frequency,
                                                     int poles,
                                                     int shortCircuitCurrent,
                                                     TripCurve tripCurve) {
    return create(
      id, upstreamCircuitBreaker, Collections.singletonList(downstreamCircuitBreaker),
      nominalCurrent, frequency, poles, shortCircuitCurrent, tripCurve
    );
  }

  /**
   * Defines the magnetic tripping characteristics of the circuit breaker.
   * These curves indicate how quickly the breaker will trip at different overcurrent levels.
   *
   * @see <a href="https://www.se.com/cl/es/faqs/FA363567/">Trip Curves by Schneider Electric</a>
   */
  public enum TripCurve {
    /**
     * Trips between 3–5× In. Suitable for purely resistive loads.
     */
    B,

    /**
     * Trips between 5–10× In. Standard for lighting and mixed loads.
     */
    C,

    /**
     * Trips between 10–20× In. For loads with high inrush current (e.g., motors).
     */
    D,

    /**
     * MA (Magnetic-only): For motor protection, without thermal protection.
     */
    MA,

    /**
     * Extra-sensitive, used in specific electronic or medical applications.
     */
    Z
  }
}
