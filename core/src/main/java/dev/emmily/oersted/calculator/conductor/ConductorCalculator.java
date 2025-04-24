package dev.emmily.oersted.calculator.conductor;

import dev.emmily.oersted.circuit.Circuit;
import dev.emmily.oersted.system.ElectricalSystem;

public interface ConductorCalculator {
  private static float calculateByVoltageDrop(Circuit circuit,
                                              Conductor.Builder builder) {
    ElectricalSystem electricalSystem = circuit.electricalSystem();
    float length = builder.length();
    float voltage = switch (electricalSystem.distributionSystem()) {
      case IT -> electricalSystem.getLineVoltage();
      case TT -> switch (circuit.circuitSystemType()) {
        case SINGLE_PHASE -> electricalSystem.getPhaseVoltage();
        case THREE_PHASE -> electricalSystem.getLineVoltage();
      };
    };

    return switch (electricalSystem.systemType()) {
      case SINGLE_PHASE -> 2 * length * circuit.apparentPower();
      case THREE_PHASE -> length * circuit.apparentPower();
    } / (builder.conductor().resistivity() * circuit.circuitType().calculateMaxVoltageDrop(voltage) * voltage);
  }

  private static float calculateByAdmissibleCurrent(Circuit circuit,
                                                    Conductor.Builder builder) {
    return 0f; // must be defined based on UTE's tables
  }
}
