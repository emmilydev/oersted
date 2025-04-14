package dev.emmily.oersted.test;

import dev.emmily.oersted.calculator.ResistorCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ResistorCalculatorTests {
  @Test
  public void testParallelSum() {
    assertEquals((double) 10 / 3, ResistorCalculator.sumParallelResistors(10, 5), 1e-7);
    assertEquals(11.1626547, ResistorCalculator.sumParallelResistors(15, 83, 92), 1e-7);


    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumParallelResistors(0, 0));
    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumParallelResistors(0, 1));
    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumParallelResistors(1, 0));
    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumParallelResistors(-1, 0));
    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumParallelResistors(0, -1));
    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumParallelResistors(13));
  }

  @Test
  public void testSeriesSum() {
    assertEquals(15, ResistorCalculator.sumSeriesResistors(5, 10));
    assertEquals(19, ResistorCalculator.sumSeriesResistors(3, 10, 6));

    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumSeriesResistors(0, 0));
    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumSeriesResistors(0, 1));
    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumParallelResistors(1, 0));
    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumParallelResistors(-1, 0));
    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumParallelResistors(0, -1));
    assertThrows(IllegalArgumentException.class, () -> ResistorCalculator.sumParallelResistors(5));
  }
}
