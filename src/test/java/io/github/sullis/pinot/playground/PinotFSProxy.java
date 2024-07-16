package io.github.sullis.pinot.playground;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.apache.pinot.spi.filesystem.PinotFS;


public class PinotFSProxy {
  public static PinotFS createProxy(PinotFS target)  {
    return (PinotFS) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
        new Class[] { PinotFS.class },
        new PinotFSInvocationHandler(target));
  }

  static class PinotFSInvocationHandler implements InvocationHandler {
    private PinotFS target;

    public PinotFSInvocationHandler(PinotFS target) {
      this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
        throws Throwable {
      System.out.println("invoking method: " + method);
      return method.invoke(target, args);
    }
  }
}
