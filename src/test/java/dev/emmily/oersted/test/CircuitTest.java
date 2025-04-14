package dev.emmily.oersted.test;

import dev.emmily.oersted.circuit.Circuit;
import dev.emmily.oersted.circuit.conductor.Conductor;
import dev.emmily.oersted.circuit.conduit.Conduit;
import dev.emmily.oersted.circuit.protection.ResidualCurrentCircuitBreaker;
import dev.emmily.oersted.circuit.protection.ThermalMagneticCircuitBreaker;
import dev.emmily.oersted.circuit.protection.CircuitBreaker;
import dev.emmily.oersted.earthing.EarthingSystem;
import dev.emmily.oersted.installation.ElectricalInstallation;
import dev.emmily.oersted.system.threephase.ThreePhaseElectricalSystem;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CircuitTest {
  @Test
  public void testCircuit() {
    Map<String, CircuitBreaker> mainLineBreakers = Stream.of(
      ThermalMagneticCircuitBreaker.create(
        "Q0", "ICP", "D0", 25, 50,
        4, 10,
        ThermalMagneticCircuitBreaker.TripCurve.B
      ),
      ResidualCurrentCircuitBreaker.create(
        "D0", "Q0", List.of("Q1", "Q2"), 40, 50,
        4, 30, ResidualCurrentCircuitBreaker.Type.AC
      )
    ).collect(Collectors.toMap(CircuitBreaker::id, Function.identity()));

    Circuit mainLine = Circuit.create(
      "línea general", "root", "Línea general, desde la instalación de enlace hasta el tablero general",
      mainLineBreakers,
      Conduit.AIR,
      List.of(Conductor.copperAndXLPE(48, 6), Conductor.copperAndXLPE(48, 6), Conductor.copperAndXLPE(48, 6)),
      Conductor.copperAndXLPE(48, 6),
      Conductor.copperAndXLPE(48, 6),
      Circuit.Type.MAIN_SUPPLY_LINE,
      1402
    );

    Circuit line1 = Circuit.create(
      "1", "línea general", "Circuito de iluminación número 1",
      Map.of("Q1", ThermalMagneticCircuitBreaker.create(
        "Q1", "D0", Collections.emptyList(), 10, 50, 2, 6, ThermalMagneticCircuitBreaker.TripCurve.B
      )),
      Conduit.CORRUGATED_PIPE_20MM,
      List.of(Conductor.copperAndPVC(20, 0.75f)),
      Conductor.copperAndPVC(20, 0.75f),
      Conductor.copperAndPVC(20, 2),
      Circuit.Type.LIGHTING,
      250
    );

    Circuit line2 = Circuit.create(
      "2", "línea general", "Circuito de iluminación número 2",
      Map.of("Q2", ThermalMagneticCircuitBreaker.create(
        "Q2", "D0", Collections.emptyList(), 10, 50, 2, 6, ThermalMagneticCircuitBreaker.TripCurve.B
      )),
      Conduit.CORRUGATED_PIPE_20MM,
      List.of(Conductor.copperAndPVC(20, 0.75f)),
      Conductor.copperAndPVC(20, 0.75f),
      Conductor.copperAndPVC(20, 2),
      Circuit.Type.LIGHTING,
      20
    );

    ElectricalInstallation installation = new ElectricalInstallation(
      new ThreePhaseElectricalSystem(
        "Red trifásica de UTE", 50, 230, 400, 0, 0,
        new EarthingSystem(5, 500, 200, 2)
      ),
      Stream.of(mainLine, line1, line2).collect(Collectors.toMap(Circuit::id, Function.identity()))
    );
  }
}
