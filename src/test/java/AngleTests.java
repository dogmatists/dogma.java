/* This is free and unencumbered software released into the public domain. */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dogma.Angle;

public class AngleTests {
  @Test
  void testDegrees() {
    assertEquals(0, Angle.fromDegrees(0).getDegrees());
    assertEquals(90, Angle.fromDegrees(90).getDegrees());
    assertEquals(180, Angle.fromDegrees(180).getDegrees());
    assertEquals(360, Angle.fromDegrees(360).getDegrees());
  }

  @Test
  void testRadians() {
    assertEquals(0, Angle.fromRadians(0).getRadians());
    assertEquals(0.5*Math.PI, Angle.fromRadians(0.5*Math.PI).getRadians());
    assertEquals(1*Math.PI, Angle.fromRadians(1*Math.PI).getRadians());
    assertEquals(2*Math.PI, Angle.fromRadians(2*Math.PI).getRadians());
  }

  @Test
  void testTurns() {
    assertEquals(0, Angle.fromTurns(0).getTurns());
    assertEquals(0.25, Angle.fromTurns(0.25).getTurns());
    assertEquals(0.5, Angle.fromTurns(0.5).getTurns());
    assertEquals(1, Angle.fromTurns(1).getTurns());
  }

  @Test
  void testCloneable() {
    final Angle angle = Angle.fromTurns(0.5);
    assertDoesNotThrow(() -> angle.clone());
    assertNotNull(angle.clone());
    assertTrue(angle.clone() instanceof Angle);
    assertEquals(angle.getRadians(), angle.clone().getRadians());
  }

  @Test
  void testComparable() {
    assertTrue(Angle.fromDegrees(0).compareTo(Angle.fromDegrees(0)) == 0);
    assertTrue(Angle.fromDegrees(0).compareTo(Angle.fromDegrees(-1)) > 0);
    assertTrue(Angle.fromDegrees(-1).compareTo(Angle.fromDegrees(0)) < 0);
  }

  @Test
  void testSerializable() {
    final Angle angle1 = Angle.fromDegrees(180);
    final Angle angle2 = Helpers.roundtrip(angle1);
    assertEquals(angle1.getDegrees(), angle2.getDegrees());
  }
}
