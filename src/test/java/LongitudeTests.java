/* This is free and unencumbered software released into the public domain. */

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import dogma.Longitude;

public class LongitudeTests {
  @Test
  void testValid() {
    assertEquals(0, Longitude.of(0).getDegrees());
    assertEquals(-180, Longitude.of(-180).getDegrees());
    assertEquals(180, Longitude.of(180).getDegrees());
  }

  @Test
  void testInvalid() {
    assertThrows(IllegalArgumentException.class, () -> Longitude.of(-181));
    assertThrows(IllegalArgumentException.class, () -> Longitude.of(181));
  }

  @Test
  void testJacksonJSON() {
    final Longitude longitude = Longitude.of(42.12345678);
    assertDoesNotThrow(() -> {
      assertEquals("42.12345678", new ObjectMapper().writeValueAsString(longitude));
    });
  }
}
