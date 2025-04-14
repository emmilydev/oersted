package dev.emmily.oersted.circuit;

import dev.emmily.oersted.circuit.conduit.Conduit;
import dev.emmily.oersted.circuit.protection.CircuitBreaker;
import dev.emmily.oersted.circuit.conductor.Conductor;

import java.util.List;
import java.util.Map;

/**
 * Represents an electrical circuit within an installation.
 * A circuit consists of conductors, a conduit, protection devices, and is categorized
 * by its type (power, lighting, control).
 *
 * @param id                 Unique identifier for the circuit.
 * @param parent             The parent circuit.
 * @param description        The description of the circuit.
 * @param circuitBreakerTree Tree of protection devices (e.g., thermal-magnetic, residual current).
 * @param conduit            The physical conduit carrying the conductors.
 * @param phaseConductors    List of phase conductors (e.g., L1, L2, L3).
 * @param neutralConductor   The neutral conductor if applicable.
 * @param earthConductor     The protective earth conductor.
 * @param type               The usage type of the circuit (power, lighting, or control).
 * @param apparentPower      The total apparent power of the circuit in volt-amperes.
 */
public record Circuit(String id,
                      String parent,
                      String description,
                      Map<String, CircuitBreaker> circuitBreakerTree,
                      Conduit conduit,
                      List<Conductor> phaseConductors,
                      Conductor neutralConductor,
                      Conductor earthConductor,
                      Type type,
                      int apparentPower) {
  public static final String PARENT_CIRCUIT = "parent";

  public static Circuit create(String id,
                               String parent,
                               String description,
                               Map<String, CircuitBreaker> circuitBreakerTree,
                               Conduit conduit,
                               List<Conductor> phaseConductors,
                               Conductor neutralConductor,
                               Conductor earthConductor,
                               Type type,
                               int apparentPower) {
    return new Circuit(
      id,
      parent,
      description,
      circuitBreakerTree,
      conduit,
      phaseConductors,
      neutralConductor,
      earthConductor,
      type,
      apparentPower
    );
  }

  @Override
  public String toString() {
    return "Circuit{" +
      "id='" + id + '\'' +
      ", parent='" + parent + '\'' +
      ", description='" + description + '\'' +
      ", circuitBreakerTree=" + circuitBreakerTree +
      ", conduit=" + conduit +
      ", phaseConductors=" + phaseConductors +
      ", neutralConductor=" + neutralConductor +
      ", earthConductor=" + earthConductor +
      ", type=" + type +
      ", apparentPower=" + apparentPower +
      '}';
  }

  /**
   * Enumerates the types of circuits based on their function in the installation.
   */
  public enum Type {
    /**
     * Power circuits typically used for high-load equipment like motors or heaters.
     */
    POWER(0.05f),

    /**
     * Lighting circuits used to supply luminaries and related control gear.
     */
    LIGHTING(0.03f),

    /**
     * Control circuits used for automation, signaling, or remote control systems.
     */
    CONTROL(0.03f),

    MAIN_SUPPLY_LINE(0.03f);

    final float maxVoltageDrop;

    Type(float maxVoltageDrop) {
      this.maxVoltageDrop = maxVoltageDrop;
    }
  }
}
