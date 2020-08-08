/* This is free and unencumbered software released into the public domain. */

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Helpers {
  private Helpers() {}

  static <T> T roundtrip(T object) {
    return deserialize(serialize(object));
  }

  static <T> byte[] serialize(T object) {
    try {
      final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      try (final ObjectOutputStream out = new ObjectOutputStream(bytes)) {
        out.writeObject(object);
      }
      return bytes.toByteArray();
    }
    catch (final IOException error) {
      throw new RuntimeException(error);
    }
  }

  @SuppressWarnings("unchecked")
  static <T> T deserialize(byte[] data) {
    try {
      final ByteArrayInputStream bytes = new ByteArrayInputStream(data);
      try (final ObjectInputStream in = new ObjectInputStream(bytes)) {
        return (T)in.readObject();
      }
      }
    catch (final IOException error) {
      throw new RuntimeException(error);
    }
    catch (final ClassNotFoundException error) {
      throw new RuntimeException(error);
    }
  }
}
