package dev.emmily.oersted.circuit.dsl;

import dev.emmily.oersted.circuit.Circuit;
import dev.emmily.oersted.circuit.conductor.Conductor;
import dev.emmily.oersted.circuit.conduit.Conduit;

import java.util.Map;
import java.util.function.Predicate;

public class TableSelectionStrategy {
  private final Map<String, Table> tableRegistry;

  public TableSelectionStrategy(Map<String, Table> tableRegistry) {
    this.tableRegistry = tableRegistry;
  }

  public void registerTable(Table table) {
    this.tableRegistry.put(table.id(), table);
  }

  public void removeTable(Table table) {
    this.tableRegistry.remove(table.id());
  }

  public void removeTable(String id) {
    this.tableRegistry.remove(id);
  }

  public Table selectTable(String table) {
    return this.tableRegistry.get(table);
  }

  public Table selectTable(Circuit circuit) {
    return tableRegistry
      .values()
      .stream()
      .filter(table -> table.rule().applies(circuit))
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException("No applicable table found for the circuit " + circuit.id()));
  }

  private static final Map<String, Predicate<Circuit>> TABLE_MATCHER = Map.of(
    "table-i", TableRule
      .withInsulator(Conductor.Material.XLPE)
      .andConduit(Conduit.Type.OUTDOOR)
      .build(),
    "table-vi", TableRule
      .withInsulator(Conductor.Material.PVC)
      .andConduit(Conduit.Type.OUTDOOR_UNDER_ROOF)
      .build(),
    "table-viii", TableRule
      .withInsulator(Conductor.Material.PVC)
      .andConductor(Conductor.Material.ALUMINUM)
      .andConduit(Conduit.Type.OUTDOOR_UNDER_ROOF)
      .build(),
    "table-ix", circuit -> true,
    "table-x", TableRule
      .withInsulator(Conductor.Material.PVC)
      .andConductor(Conductor.Material.COPPER)
      .andConduit(Conduit.Type.PIPE)
      .build(),
    "table-xi", TableRule
      .withInsulator(Conductor.Material.XLPE)
      .andConductor(Conductor.Material.COPPER)
      .andConduit(Conduit.Type.PIPE)
      .build(),
    "table-xii", TableRule
      .withInsulator(Conductor.Material.PVC)
      .andConductor(Conductor.Material.ALUMINUM)
      .andConduit(Conduit.Type.PIPE)
      .build(),
    "table-xiii", TableRule
      .withInsulator(Conductor.Material.XLPE)
      .andConductor(Conductor.Material.ALUMINUM)
      .andConduit(Conduit.Type.PIPE)
      .build()
  );


}
