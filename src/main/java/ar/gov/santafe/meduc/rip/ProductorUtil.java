package ar.gov.santafe.meduc.rip;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;


public class ProductorUtil {

	public <T> T getProxyFor(Class<T> t) {

		@SuppressWarnings("unchecked")
		T implementing = (T) java.lang.reflect.Proxy.newProxyInstance(
				t.getClassLoader(), new java.lang.Class[] { t },
				new RestInvocationHandler());
		return implementing; 
	}
	
	
	@Produces
    public <T> Service<T> create(InjectionPoint p){ 
		 Class clazz = (Class)((ParameterizedType)p.getType()).getActualTypeArguments()[0];
		return new Service(getProxyFor(clazz));
    }
}
