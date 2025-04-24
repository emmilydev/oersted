package dev.emmily.oersted.circuit.dsl;

import dev.emmily.oersted.circuit.Circuit;
import dev.emmily.oersted.circuit.conductor.Conductor;
import dev.emmily.oersted.circuit.conduit.Conduit;

import java.util.function.Predicate;

public class TableRule {
  private final Predicate<Circuit> rule;

  private TableRule(Predicate<Circuit> rule) {
    this.rule = rule;
  }

  public static TableRule where(Predicate<Circuit> predicate) {
    return new TableRule(predicate);
  }

  public static TableRule withInsulator(Conductor.Material material) {
    return new TableRule(circuit -> circuit.phaseConductors().insulator() == material);
  }

  public TableRule andConductor(Conductor.Material material) {
    return new TableRule(circuit -> rule.test(circuit) && circuit.phaseConductors().conductor() == material);
  }

  public TableRule andConduit(Conduit.Type type) {
    return new TableRule(c -> rule.test(c) && c.conduit().type() == type);
  }

  public Predicate<Circuit> build() {
    return rule;
  }

  public boolean applies(Circuit circuit) {
    return rule.test(circuit);
  }
}
