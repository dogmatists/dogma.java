/* This is free and unencumbered software released into the public domain. */

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

import dogma.Location;

public class LocationTests {
  @Test
  void testValid() {
    final Location location = Location.of(1, 2);
    assertNotNull(location.getLatitude());
    assertNotNull(location.getLongitude());
    assertEquals(1, location.getLatitude().getDegrees());
    assertEquals(2, location.getLongitude().getDegrees());
  }

  @Test
  void testInvalidLatitude() {
    assertThrows(IllegalArgumentException.class, () -> Location.of(-91, 0));
    assertThrows(IllegalArgumentException.class, () -> Location.of(91, 0));
  }

  @Test
  void testInvalidLongitude() {
    assertThrows(IllegalArgumentException.class, () -> Location.of(0, -181));
    assertThrows(IllegalArgumentException.class, () -> Location.of(0, 181));
  }

  @Test
  void testJacksonJSON() {
    final Location location = Location.of(-10.12345678, 20.12345678);
    assertDoesNotThrow(() -> {
      assertEquals("{\"latitude\":-10.12345678,\"longitude\":20.12345678}", new ObjectMapper().writeValueAsString(location));
    });
  }
}
