package dev.emmily.oersted.installation;

import dev.emmily.oersted.circuit.Circuit;
import dev.emmily.oersted.system.ElectricalSystem;

import java.util.Map;

public record ElectricalInstallation(ElectricalSystem supplyingSystem,
                                     Map<String, Circuit> circuitTree) {
  public Circuit getParent(Circuit circuit) {
    String parent = circuit.parent();

    if (parent.equals(Circuit.PARENT_CIRCUIT)) {
      return circuit;
    }

    return circuitTree.get(parent);
  }
}
