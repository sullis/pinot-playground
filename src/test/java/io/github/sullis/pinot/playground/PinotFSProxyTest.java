package io.github.sullis.pinot.playground;

import java.net.URI;
import org.apache.pinot.spi.filesystem.LocalPinotFS;
import org.apache.pinot.spi.filesystem.PinotFS;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class PinotFSProxyTest {
  @Test
  void test() throws Exception {
    PinotFS target = new LocalPinotFS();
    PinotFS proxy = PinotFSProxy.createProxy(target);

    assertFalse(proxy.exists(URI.create("non-existent-file.txt")));
    assertTrue(proxy.mkdir(URI.create("test-dir")));
  }
}
