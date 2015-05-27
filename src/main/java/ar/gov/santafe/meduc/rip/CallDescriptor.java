package ar.gov.santafe.meduc.rip;

import java.util.Map;

public class CallDescriptor {

	private String path;
	private Map<String, Object> parameters;
	private Metodo metodo;
	private Class<?> returnType;

	public CallDescriptor(String path, Map<String, Object> parameters,Metodo metodo,Class<?> returnType) {
		this.path = path;
		this.parameters = parameters;
		this.metodo = metodo;
		this.returnType = returnType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Map<String, Object> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, Object> parameters) {
		this.parameters = parameters;
	}
	public Metodo getMetodo() {
		return metodo;
	}
	public void setMetodo(Metodo metodo) {
		this.metodo = metodo;
	}
	public Class<?> getReturnType() {
		return returnType;
	}
	public void setReturnType(Class<?> returnType) {
		this.returnType = returnType;
	}

}

