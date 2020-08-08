/* This is free and unencumbered software released into the public domain. */

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
  void testCloneable() {
    final Longitude longitude = Longitude.of(42.12345678);
    assertDoesNotThrow(() -> longitude.clone());
    assertNotNull(longitude.clone());
    assertTrue(longitude.clone() instanceof Longitude);
    assertEquals(longitude.getRadians(), longitude.clone().getRadians());
  }

  @Test
  void testComparable() {
    assertTrue(Longitude.of(0).compareTo(Longitude.of(0)) == 0);
    assertTrue(Longitude.of(0).compareTo(Longitude.of(-1)) > 0);
    assertTrue(Longitude.of(-1).compareTo(Longitude.of(0)) < 0);
  }

  @Test
  void testSerializable() {
    final Longitude longitude1 = Longitude.of(42.12345678);
    final Longitude longitude2 = Helpers.roundtrip(longitude1);
    assertEquals(longitude1.getDegrees(), longitude2.getDegrees());
  }

  @Test
  void testJacksonJSON() {
    final Longitude longitude = Longitude.of(42.12345678);
    assertDoesNotThrow(() -> {
      assertEquals("42.12345678", new ObjectMapper().writeValueAsString(longitude));
    });
  }

  @Test
  void testGsonJSON() {
    final Longitude longitude = Longitude.of(42.12345678);
    assertDoesNotThrow(() -> {
      final Gson gson = new GsonBuilder()
          .registerTypeHierarchyAdapter(Longitude.class, new Longitude.GsonSerializer())
          .create();
      assertEquals("42.12345678", gson.toJson(longitude));
    });
  }
}
