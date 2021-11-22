package org.apache.zookeeper.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * This is the ZKTrustManager proxy class.
 * This is used to expose ZKTrustManager class through proxy instance to avoid class cast error
 * as X509TrustManagerImpl will conflict with ZKTrustManager class
 */
public class ZKTrustManagerProxy implements InvocationHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ZKTrustManagerProxy.class);
    private final ZKTrustManager zkTrustManager;

    public ZKTrustManagerProxy(ZKTrustManager zkTrustManager) {
        this.zkTrustManager = zkTrustManager;
    }

    /**
     * Invoke method implementation of proxy instance
     *
     * @param proxy  proxy object
     * @param method method object
     * @param args   method arguments
     * @return return the output which comes from invoke mrthod
     * @throws Throwable it throws any kind of exception which pop up in executing the given method
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        LOG.debug("Invoking method {} with arguments {}", method.getName(), args);
        return method.invoke(zkTrustManager, args);
    }
}
