package ar.gov.santafe.meduc.rip;

import java.lang.reflect.InvocationHandler;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws java.lang.Throwable {
		CallDescriptor cd = MethodMapper.getDescriptor(method, args);
		Client client = ClientBuilder.newClient();

		Builder builder = client
				.target("http://localhost:8080/lce-persistence")
				.path(cd.getPath()).resolveTemplates(cd.getParameters())
				.request(MediaType.APPLICATION_JSON);

		Response response = null;
		
		if (cd.getMetodo().equals(Metodo.GET)) {
			if (cd.getReturnType().equals(List.class)) {
				return builder.get(new GenericType(method .getGenericReturnType()) {});
			} else {
				response = builder.get();
			}
		} else if (cd.getMetodo().equals(Metodo.POST)) {
			response = builder.post(Entity.json(args[0]));
		}
		return response.readEntity(cd.getReturnType());
	}

}