/* This is free and unencumbered software released into the public domain. */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import dogma.Latitude;

public class LatitudeTests {
  @Test
  void testValid() {
    assertEquals(0, Latitude.of(0).getDegrees());
    assertEquals(-90, Latitude.of(-90).getDegrees());
    assertEquals(90, Latitude.of(90).getDegrees());
  }

  @Test
  void testInvalid() {
    assertThrows(IllegalArgumentException.class, () -> Latitude.of(-91));
    assertThrows(IllegalArgumentException.class, () -> Latitude.of(91));
  }
}
