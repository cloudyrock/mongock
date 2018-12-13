package com.github.cloudyrock.mongock;

import net.sf.cglib.core.DefaultNamingPolicy;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.Factory;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.NoOp;
import org.objenesis.ObjenesisStd;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;

/**
 * Proxy wrapper generator
 *
 *
 * @since 04/04/2018
 */
class ProxyFactory {

  private static final ObjenesisStd objenesis = new ObjenesisStd();
  private final Set<String> proxyCreatorMethods;
  private final Set<String> uncheckedMethods;
  private final PreInterceptor preInterceptor;

  /**
   * <p>Constructor with PreInterceptor, which will be called before the method's invocation, list of method names
   * which will skip the PreInterceptor invocation and a list of method names that will return a proxy of the returned
   * object.</p>
   * <p>Notice that it's responsible of the developer using this class to ensure all the methods in the
   * proxyCreatorMethods return a class that can be proxied. This action is performed unsafely by this class</p>
   *
   * @param preInterceptor      PreInterceptor with the desired behaviour before the method's invocation
   * @param proxyCreatorMethods list of method names that will return a proxy of the returned object
   * @param uncheckedMethods    list of methods names that will skip the PreInterceptor invocation
   * @see PreInterceptor
   */
  ProxyFactory(PreInterceptor preInterceptor,
               Set<String> proxyCreatorMethods,
               Set<String> uncheckedMethods) {
    this.preInterceptor = preInterceptor;
    this.proxyCreatorMethods = proxyCreatorMethods;
    this.uncheckedMethods = uncheckedMethods;
  }

  private static Object createProxy(Class<?> proxyClass, MethodInterceptor interceptor) {
    Factory proxy = (Factory) objenesis.newInstance(proxyClass);

    proxy.setCallbacks(new Callback[]{interceptor, SerializableNoOp.SERIALIZABLE_INSTANCE});
    return proxy;
  }

  private static Class<?> createProxyClass(Class<?> mockedType) {
    Enhancer enhancer = new Enhancer() {
      @Override
      protected void filterConstructors(Class sc, List constructors) {
        //Ignoring filter filter
      }
    };
    enhancer.setClassLoader(mockedType.getClassLoader());
    enhancer.setUseFactory(true);
    enhancer.setSuperclass(mockedType);
    enhancer.setNamingPolicy(MongockLockCheckerProxyNamingPolicy.INSTANCE);
    enhancer.setCallbackTypes(new Class[]{MethodInterceptor.class, NoOp.class});

    enhancer.setCallbackFilter(new CallbackFilter() {
      public int accept(Method method) {
        return method.isBridge() ? 1 : 0;//Ignoring bridge methods
      }
    });

    return enhancer.createClass();
  }

  private static void setAllConstructorsAccessible(Class<?> mockedType, boolean accessible) {
    Constructor[] constructorsArr = mockedType.getDeclaredConstructors();
    for (Constructor aConstructorsArr : constructorsArr) {
      aConstructorsArr.setAccessible(accessible);
    }
  }

  /**
   * <p>Method to generate a proxy from a given object.</p>
   * <p>Please notice it won't create a new instance. Instead it will create a wrapper that will actually invoke
   * the method on the original object</p>
   *
   * @param original object to be proxied
   * @param <T>      Ooiginal's class
   * @return a proxy wrapper for the original object
   */
  @SuppressWarnings("unchecked")
  <T> T createProxyFromOriginal(final T original) {
    return createProxyFromOriginal(original, original.getClass());
  }


  <T> T createProxyFromOriginal(final T original, Class clazz) {
    try {
      setAllConstructorsAccessible(clazz, true);
      final Class<?> proxyClass = createProxyClass(clazz);
      final Object proxy = createProxy(proxyClass, createMethodInterceptor(original, preInterceptor));
      return (T) clazz.cast(proxy);
    } finally {
      setAllConstructorsAccessible(clazz, false);
    }
  }

  private <T> ProxyMethodInterceptor createMethodInterceptor(T original, PreInterceptor preInterceptor) {
    return new ProxyMethodInterceptor(original, this, preInterceptor, proxyCreatorMethods, uncheckedMethods);
  }

  private static class MongockLockCheckerProxyNamingPolicy extends DefaultNamingPolicy {
    static final MongockLockCheckerProxyNamingPolicy INSTANCE = new MongockLockCheckerProxyNamingPolicy();

    private MongockLockCheckerProxyNamingPolicy() {
    }

    @Override
    protected String getTag() {
      return "ByMongock";
    }
  }

}
