package dev.emmily.oersted.circuit.conductor;

public record Conductor(Material conductor,
                        Material insulator,
                        float length,
                        float crossSection) {
  public static Builder builder() {
    return new Builder();
  }
  public static Conductor aluminumAndPVC(float length, float crossSection) {
    return new Conductor(Material.ALUMINUM, Material.PVC, length, crossSection);
  }

  public static Conductor aluminumAndXLPE(float length, float crossSection) {
    return new Conductor(Material.ALUMINUM, Material.XLPE, length, crossSection);
  }

  public static Conductor copperAndPVC(float length, float crossSection) {
    return new Conductor(Material.COPPER, Material.PVC, length, crossSection);
  }

  public static Conductor copperAndXLPE(float length, float crossSection) {
    return new Conductor(Material.COPPER, Material.XLPE, length, crossSection);
  }

  public enum Material {
    // CONDUCTOR
    ALUMINUM(34.7f),
    COPPER(56.9f),

    // INSULATOR
    PVC(Long.MAX_VALUE),
    XLPE(Long.MAX_VALUE);

    final float resistivity;

    Material(float resistivity) {
      this.resistivity = resistivity;
    }

    public float resistivity() {
      return resistivity;
    }
  }

  public static class Builder {
    private Material conductor;
    private Material insulator;
    private float length;
    private float crossSection;

    public Builder conductor(Material conductor) {
      this.conductor = conductor;
      return this;
    }

    public Material conductor() {
      return conductor;
    }

    public Builder insulator(Material insulator) {
      this.insulator = insulator;
      return this;
    }

    public Material insulator() {
      return insulator;
    }

    public Builder length(float length) {
      this.length = length;
      return this;
    }

    public float length() {
      return length;
    }

    public Builder crossSection(float crossSection) {
      this.crossSection = crossSection;
      return this;
    }

    public float crossSection() {
      return crossSection;
    }


    public Conductor build() {
      return new Conductor(conductor, insulator, length, crossSection);
    }
  }

}
