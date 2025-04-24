package dev.emmily.oersted.circuit.conduit;

public record Conduit(Material material,
                      Type type,
                      double diameter) {
  public static final Conduit AIR = new Conduit(null, null, Long.MAX_VALUE);
  public static final Conduit CORRUGATED_PIPE_20MM = new Conduit(Material.PVC, Type.CORRUGATED_PIPE, 20);
  public static final Conduit CORRUGATED_PIPE_40MM = new Conduit(Material.PVC, Type.CORRUGATED_PIPE, 40);

  public double getUsableSpace() {
    return Math.PI * Math.pow((diameter / 2), 2);
  }

  public enum Material {
    AIR,
    PVC,
    GALVANIZED_IRON,
    POLYPROPYLENE;
  }

  public enum Type {
    OUTDOOR,
    OUTDOOR_UNDER_ROOF,
    PIPE;
  }
}
