package ar.gov.santafe.meduc.rip;

public class Service<T> {
	public final T call;

	public Service(T call) {
		this.call = call;
	}
}