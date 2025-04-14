package dev.emmily.oersted.system.threephase;

import dev.emmily.oersted.system.ElectricalSystem;
import dev.emmily.oersted.earthing.EarthingSystem;

public class ThreePhaseElectricalSystem implements ElectricalSystem {

  private String id;
  private int frequency;
  private int phaseVoltage;
  private int lineVoltage;
  private double activePower;
  private double reactivePower;
  private double apparentPower;
  private double powerFactor;
  private EarthingSystem earthingSystem;

  public ThreePhaseElectricalSystem(String id,
                                    int frequency,
                                    int phaseVoltage,
                                    int lineVoltage,
                                    double activePower,
                                    double reactivePower,
                                    EarthingSystem earthingSystem) {
    this.id = id;
    this.frequency = frequency;
    this.phaseVoltage = phaseVoltage;
    this.lineVoltage = lineVoltage;
    this.activePower = activePower;
    this.reactivePower = reactivePower;
    this.apparentPower = calculateApparentPower();
    this.powerFactor = calculatePowerFactor();
    this.earthingSystem = earthingSystem;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public int getFrequency() {
    return frequency;
  }

  @Override
  public void setFrequency(int frequency) {
    this.frequency = frequency;
  }

  @Override
  public int getPhaseVoltage() {
    return phaseVoltage;
  }

  @Override
  public void setPhaseVoltage(int phaseVoltage) {
    this.phaseVoltage = phaseVoltage;

    this.lineVoltage = phaseVoltage * (int) SQRT_3;
  }

  @Override
  public int getLineVoltage() {
    return lineVoltage;
  }

  @Override
  public void setLineVoltage(int lineVoltage) {
    this.lineVoltage = lineVoltage;

    this.phaseVoltage = lineVoltage / (int) SQRT_3;
  }

  @Override
  public double getActivePower() {
    return activePower;
  }

  @Override
  public void setActivePower(double activePower) {
    this.activePower = activePower;
    this.apparentPower = calculateApparentPower();
    this.powerFactor = calculatePowerFactor();
  }

  @Override
  public double getReactivePower() {
    return reactivePower;
  }

  @Override
  public void setReactivePower(double reactivePower) {
    this.reactivePower = reactivePower;
    this.apparentPower = calculateApparentPower(); // Update apparent power based on reactive power
    this.powerFactor = calculatePowerFactor(); // Update power factor based on reactive power
  }

  @Override
  public double getApparentPower() {
    return apparentPower;
  }

  @Override
  public double setApparentPower(double apparentPower) {
    this.apparentPower = apparentPower;
    this.powerFactor = calculatePowerFactor();
    return apparentPower;
  }

  @Override
  public double getPowerFactor() {
    return powerFactor;
  }

  @Override
  public double getPhi() {
    return Math.acos(powerFactor);
  }

  @Override
  public double setPowerFactor(double powerFactor) {
    this.powerFactor = powerFactor;
    this.apparentPower = calculateApparentPower();
    return powerFactor;
  }

  @Override
  public double calculatePowerFactor() {
    return activePower / apparentPower;
  }

  @Override
  public double calculatePowerFactorCorrection() {
    double requiredReactivePower = activePower * Math.tan(Math.acos(powerFactor));
    return requiredReactivePower - reactivePower;
  }

  private double calculateApparentPower() {
    return Math.sqrt(Math.pow(activePower, 2) + Math.pow(reactivePower, 2));
  }

  @Override
  public EarthingSystem getEarthingSystem() {
    return earthingSystem;
  }

  @Override
  public void setEarthingSystem(EarthingSystem earthingSystem) {
    this.earthingSystem = earthingSystem;
  }
}
