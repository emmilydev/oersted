package dev.emmily.oersted.circuit.conductor;

public record Conductor(Material conductor,
                        Material insulator,
                        float length,
                        float crossSection) {
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

    public float getResistivity() {
      return resistivity;
    }
  }
}
