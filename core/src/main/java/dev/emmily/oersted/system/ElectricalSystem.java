package dev.emmily.oersted.system;

/**
 * Represents an electrical system, which could be either single-phase or three-phase.
 * It includes essential properties such as voltage, frequency, and active/reactive/apparent power.
 * This interface also provides methods for calculating and correcting the power factor, among other key operations.
 */
public interface ElectricalSystem {
  /**
   * Constant for the square root of 3, useful in three-phase system calculations.
   */
  double SQRT_3 = Math.sqrt(3);

  /**
   * Gets the unique identifier for the electrical system.
   * @return The system's ID.
   */
  String getId();

  SystemType systemType();

  DistributionSystem distributionSystem();

  void setDistributionSystem(DistributionSystem distributionSystem);

  /**
   * Gets the frequency of the electrical system (Hz).
   * @return The frequency in Hz.
   */
  int getFrequency();

  /**
   * Sets the frequency of the electrical system (Hz).
   * @param frequency The frequency in Hz.
   */
  void setFrequency(int frequency);

  /**
   * Gets the phase voltage of the electrical system.
   * @return The phase voltage in volts.
   */
  int getPhaseVoltage();

  /**
   * Sets the phase voltage of the electrical system.
   * @param phaseVoltage The phase voltage in volts.
   */
  void setPhaseVoltage(int phaseVoltage);

  /**
   * Gets the line voltage of the electrical system.
   * @return The line voltage in volts.
   */
  int getLineVoltage();

  /**
   * Sets the line voltage of the electrical system.
   * @param lineVoltage The line voltage in volts.
   */
  void setLineVoltage(int lineVoltage);

  enum SystemType {
    SINGLE_PHASE,
    THREE_PHASE
  }

  enum DistributionSystem {
    IT,
    TT
  }
}
