package dev.emmily.oersted.system.threephase;

import dev.emmily.oersted.system.ElectricalSystem;

public class ThreePhaseElectricalSystem implements ElectricalSystem {

  private final String id;
  private DistributionSystem distributionSystem;
  private int frequency;
  private int phaseVoltage;
  private int lineVoltage;

  public ThreePhaseElectricalSystem(String id,
                                    DistributionSystem distributionSystem,
                                    int frequency,
                                    int phaseVoltage,
                                    int lineVoltage) {
    this.id = id;
    this.distributionSystem = distributionSystem;
    this.frequency = frequency;
    this.phaseVoltage = phaseVoltage;
    this.lineVoltage = lineVoltage;
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public DistributionSystem distributionSystem() {
    return distributionSystem;
  }

  @Override
  public void setDistributionSystem(DistributionSystem distributionSystem) {
    this.distributionSystem = distributionSystem;
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
}
