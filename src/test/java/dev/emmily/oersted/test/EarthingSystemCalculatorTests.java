package dev.emmily.oersted.test;

import dev.emmily.oersted.earthing.EarthingSystem;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EarthingSystemCalculatorTests {
  @Test
  public void testEarthingSystem(){
    EarthingSystem earthingSystem = EarthingSystem
      .create(4.31, 500, 200, 2);

    assertEquals(4.31, earthingSystem.calculateEarthingResistance(), 1e-7);
    assertEquals(200, earthingSystem.getTotalMeshLength());
    assertEquals(8, earthingSystem.getTotalRods());
  }

  @Test
  public void testEarthingSystem2() {
    EarthingSystem earthingSystem = EarthingSystem
      .create(5, 50, 0, 2);
    earthingSystem.calculateEarthingResistance();

    assertEquals(5, earthingSystem.getTotalRods());
  }
}
