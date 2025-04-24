package dev.emmily.oersted.circuit;

import dev.emmily.oersted.circuit.conductor.Conductor;
import dev.emmily.oersted.circuit.conduit.Conduit;
import dev.emmily.oersted.circuit.protection.CircuitBreaker;
import dev.emmily.oersted.hierarchy.Hierarchical;
import dev.emmily.oersted.system.ElectricalSystem;

import java.util.Map;

/**
 * Represents an electrical circuit in an installation.
 * A circuit consists of conductors, a conduit, protection devices, and is categorized
 * by its circuitType (power, lighting, control).
 *
 * @param id                 Unique identifier for the circuit.
 * @param parent             The parent circuit.
 * @param description        The description of the circuit.
 * @param circuitBreakerTree Tree of protection devices (e.g., thermal-magnetic, residual current).
 * @param conduit            The physical conduit carrying the conductors.
 * @param phaseConductors    The phase conductor model.
 * @param neutralConductor   The neutral conductor if applicable.
 * @param earthConductor     The protective earth conductor.
 * @param circuitType        The type of the circuit (power, lighting, or control).
 * @param apparentPower      The total apparent power of the circuit in volt-amperes.
 */
public record Circuit(String id,
                      String parent,
                      String description,
                      Map<String, CircuitBreaker> circuitBreakerTree,
                      Conduit conduit,
                      Conductor phaseConductors,
                      Conductor neutralConductor,
                      Conductor earthConductor,
                      CircuitType circuitType,
                      ElectricalSystem electricalSystem,
                      ElectricalSystem.SystemType circuitSystemType,
                      int apparentPower) implements Hierarchical {
  public static Circuit create(String id,
                               String parent,
                               String description,
                               Map<String, CircuitBreaker> circuitBreakerTree,
                               Conduit conduit,
                               Conductor phaseConductors,
                               Conductor neutralConductor,
                               Conductor earthConductor,
                               CircuitType type,
                               ElectricalSystem electricalSystem,
                               ElectricalSystem.SystemType circuitSystemType,
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
      electricalSystem,
      circuitSystemType,
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
      ", circuitType=" + circuitType +
      ", apparentPower=" + apparentPower +
      '}';
  }

  /**
   * Enumerates the types of circuits based on their function in the installation.
   */
  public enum CircuitType {
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

    CircuitType(float maxVoltageDrop) {
      this.maxVoltageDrop = maxVoltageDrop;
    }

    public float maxVoltageDrop() {
      return maxVoltageDrop;
    }

    public float calculateMaxVoltageDrop(float voltage) {
      return maxVoltageDrop * voltage;
    }
  }

  public static class Builder {
    private String id;
    private String parent = Hierarchical.PARENT; // Default parent
    private String description;
    private Map<String, CircuitBreaker> circuitBreakerTree;
    private Conduit conduit;
    private Conductor phaseConductors;
    private Conductor neutralConductor;
    private Conductor earthConductor;
    private CircuitType type;
    private ElectricalSystem electricalSystem;
    private ElectricalSystem.SystemType circuitSystemType;
    private int apparentPower;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder parent(String parent) {
      this.parent = parent;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder circuitBreakerTree(Map<String, CircuitBreaker> circuitBreakerTree) {
      this.circuitBreakerTree = circuitBreakerTree;
      return this;
    }

    public Builder conduit(Conduit conduit) {
      this.conduit = conduit;
      return this;
    }

    public Builder phaseConductors(Conductor phaseConductors) {
      this.phaseConductors = phaseConductors;
      return this;
    }

    public Builder neutralConductor(Conductor neutralConductor) {
      this.neutralConductor = neutralConductor;
      return this;
    }

    public Builder earthConductor(Conductor earthConductor) {
      this.earthConductor = earthConductor;
      return this;
    }

    public Builder type(CircuitType type) {
      this.type = type;
      return this;
    }

    public Builder electricalSystem(ElectricalSystem electricalSystem) {
      this.electricalSystem = electricalSystem;
      return this;
    }

    public Builder circuitSystemType(ElectricalSystem.SystemType circuitSystemType) {
      this.circuitSystemType = circuitSystemType;
      return this;
    }

    public Builder apparentPower(int apparentPower) {
      this.apparentPower = apparentPower;
      return this;
    }

    public Builder autoCalculateConductors() {
      this.phaseConductors = ConductorCalculator.calculatePhaseConductors(this.apparentPower, this.type);
      this.neutralConductor = ConductorCalculator.calculateNeutralConductor(this.apparentPower, this.type);
      this.earthConductor = ConductorCalculator.calculateEarthConductor(this.apparentPower, this.type);
      return this;
    }

    public Circuit build() {
      return new Circuit(
        this.id,
        this.parent,
        this.description,
        this.circuitBreakerTree,
        this.conduit,
        this.phaseConductors,
        this.neutralConductor,
        this.earthConductor,
        this.type,
        this.electricalSystem,
        this.circuitSystemType,
        this.apparentPower
      );
    }
  }
}