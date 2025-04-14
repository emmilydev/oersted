package dev.emmily.oersted.system;

import dev.emmily.oersted.earthing.EarthingSystem;

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

  /**
   * Gets the active power of the electrical system.
   * @return The active power in watts.
   */
  double getActivePower();

  /**
   * Sets the active power of the electrical system.
   * @param activePower The active power in watts.
   */
  void setActivePower(double activePower);

  /**
   * Gets the reactive power of the electrical system.
   * @return The reactive power in volt-amperes reactive (VAR).
   */
  double getReactivePower();

  /**
   * Sets the reactive power of the electrical system.
   * @param reactivePower The reactive power in volt-amperes reactive (VAR).
   */
  void setReactivePower(double reactivePower);

  /**
   * Gets the apparent power of the electrical system.
   * @return The apparent power in volt-amperes (VA).
   */
  double getApparentPower();

  /**
   * Sets the apparent power of the electrical system.
   * @param apparentPower The apparent power in volt-amperes (VA).
   */
  double setApparentPower(double apparentPower);

  /**
   * Gets the power factor of the electrical system.
   * @return The power factor (dimensionless).
   */
  double getPowerFactor();

  /**
   * Gets the phase angle (phi) of the electrical system.
   * @return The phase angle in radians.
   */
  double getPhi();

  /**
   * Sets the power factor of the electrical system.
   * @param powerFactor The power factor (dimensionless).
   */
  double setPowerFactor(double powerFactor);

  /**
   * Calculates the power factor based on the system's active and apparent power.
   * @return The calculated power factor.
   */
  double calculatePowerFactor();

  /**
   * Calculates the power factor correction required to adjust the system's power factor to a desired value.
   * @return The power factor correction in volt-amperes reactive (VAR).
   */
  double calculatePowerFactorCorrection();

  EarthingSystem getEarthingSystem();

  void setEarthingSystem(EarthingSystem earthingSystem);
}
