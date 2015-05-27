package ar.gov.santafe.meduc.rip;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

import ar.com.norrmann.remote.interfaces.Ruta;

public class MethodMapper {

	public static CallDescriptor getDescriptor(Method method, Object[] args) {

		Map<String, Object> parameterMap = getParameterMap(method, args);
		String path = getPath(method);
		Class<?> returnType = method.getReturnType();
		
		Metodo metodo = null;
		if (method.getAnnotation(GET.class)!=null){
			metodo = Metodo.GET;
		} else if (method.getAnnotation(POST.class)!=null){
			metodo = Metodo.POST;
		}

		CallDescriptor cd = new CallDescriptor(path, parameterMap, metodo,returnType);

		return cd;

	}

	private static String getPath(Method method) {
		String path = "";
		Class<?> clazz = method.getDeclaringClass();
		Annotation rootPath = clazz.getAnnotation(Ruta.class);
		if (rootPath != null) {
			path = path + getStringValue(rootPath); 
		}
		Annotation pathAnotation = method.getAnnotation(Ruta.class);
		if (pathAnotation != null) {
			path = path + getStringValue(pathAnotation);
		}
		return path;

	}

	private static String getStringValue(Annotation anotation) {
		final String valueString = "value=";
		String value = anotation.toString();
		value = value.substring(value.indexOf(valueString) + valueString.length());
		value = value.substring(0, value.length() - 1);
		return value;
	}

	private static Map<String, Object> getParameterMap(Method method, Object[] args) {
		Annotation[][] anotations = method.getParameterAnnotations();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for (int i = 0; i < anotations.length; i++) {
			Annotation[] paramAnotationsArray = anotations[i];
			for (Annotation anAnotation : paramAnotationsArray) {
				if (anAnotation instanceof PathParam) {
					paramMap.put(getStringValue(anAnotation), args[i]);
				}
			}
		}
		return paramMap;

	}

}
