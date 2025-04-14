package dev.emmily.oersted.earthing;

import dev.emmily.oersted.calculator.ResistorCalculator;

/**
 * Represents an earthing system of an electrical installation.
 * <p>
 * Provides methods to calculate the earthing resistance based on the mesh and rod configurations.
 * This implementation follows UTE's "Reglamento de Baja Tensión", which assumes a minimum
 * cross-section of 35 mm² for bare copper conductors used in earthing meshes.
 * </p>
 * <p>
 * TODO: Implement Dwight/Sunde models for more accurate resistance calculations.
 * </p>
 *
 * @see <a href="https://www.ute.com.uy/sites/default/files/files-cuerpo-paginas/C-23.pdf">UTE RBT C-23</a>
 */
public class EarthingSystem {
  public static EarthingSystem create(double maxResistance,
                                      double soilResistivity,
                                      double maxMeshLength,
                                      double rodLengthModel) {
    return new EarthingSystem(maxResistance, soilResistivity, maxMeshLength, rodLengthModel);
  }

  private final double maxResistance;
  private final double soilResistivity;
  private final double maxMeshLength;
  private final double rodLengthModel;

  private double totalRods;
  private double totalMeshLength;

  /**
   * @param maxResistance   the maximum allowed resistance (Ω)
   * @param soilResistivity the resistivity of the soil (Ω·m)
   * @param maxMeshLength   the maximum mesh length allowed (m)
   * @param rodLengthModel  the standard rod length used (m)
   */
  public EarthingSystem(double maxResistance,
                        double soilResistivity,
                        double maxMeshLength,
                        double rodLengthModel) {
    this.maxResistance = maxResistance;
    this.soilResistivity = soilResistivity;
    this.maxMeshLength = maxMeshLength;
    this.rodLengthModel = rodLengthModel;
  }

  public double getMaxResistance() {
    return maxResistance;
  }

  public double getSoilResistivity() {
    return soilResistivity;
  }

  public double getMaxMeshLength() {
    return maxMeshLength;
  }

  public double getRodLengthModel() {
    return rodLengthModel;
  }

  public double getTotalRods() {
    return totalRods;
  }

  public double getTotalMeshLength() {
    return totalMeshLength;
  }

  /**
   * Calculates the resistance of rods given the total effective buried length.
   *
   * @param totalRodLength total effective rod length (m)
   * @return the resistance of the rods (Ω)
   */
  public double calculateRodResistance(double totalRodLength) {
    return totalRodLength == 0 ? 0 : soilResistivity / totalRodLength;
  }

  /**
   * Calculates the resistance of the mesh.
   *
   * @param meshLength total mesh conductor length (m)
   * @return the resistance of the mesh (Ω)
   */
  public double calculateMeshResistance(double meshLength) {
    return 2 * (soilResistivity / meshLength);
  }

  /**
   * Calculates the total earthing resistance, using both mesh and rods if applicable.
   *
   * @return the total earthing resistance (Ω)
   */
  public double calculateEarthingResistance() {
    totalMeshLength = calculateIdealMeshLength();
    totalMeshLength = capMeshLength(totalMeshLength);

    if (isMeshUnused(totalMeshLength)) {
      return calculateResistanceWithRodsOnly();
    }

    return calculateCombinedResistance();
  }

  private double calculateIdealMeshLength() {
    return (soilResistivity / maxResistance) * 2;
  }

  private double capMeshLength(double meshLength) {
    return Math.min(meshLength, maxMeshLength);
  }

  private boolean isMeshUnused(double meshLength) {
    return meshLength == 0;
  }

  private double calculateResistanceWithRodsOnly() {
    double rodsLength = soilResistivity / maxResistance;
    totalRods = Math.round(rodsLength / rodLengthModel);
    return calculateRodResistance(rodsLength);
  }

  private double calculateCombinedResistance() {
    double meshResistance = calculateMeshResistance(totalMeshLength);
    double rodsResistance = (maxResistance * meshResistance) / (meshResistance - maxResistance);
    double rodsLength = soilResistivity / rodsResistance;
    totalRods = Math.round(rodsLength / rodLengthModel);
    return ResistorCalculator.sumParallelResistors(meshResistance, rodsResistance);
  }
}