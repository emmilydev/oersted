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

  enum Material {
    PVC,
    GALVANIZED_IRON,
    POLYPROPYLENE;
  }

  enum Type {
    PIPE,
    CORRUGATED_PIPE,
    PERFORATED_TRAY,
    LADDER_TRAY,
    WIRE_MESH_TRAY,
    SOLID_BOTTOM_TRAY;
  }
}
