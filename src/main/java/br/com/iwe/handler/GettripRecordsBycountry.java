package br.com.iwe.handler;

import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import br.com.iwe.dao.tripRepository;
import br.com.iwe.model.HandlerRequest;
import br.com.iwe.model.HandlerResponse;
import br.com.iwe.model.trip;

public class GettripRecordsBycountry implements RequestHandler<HandlerRequest, HandlerResponse> {

	private final tripRepository repository = new tripRepository();

	@Override
	public HandlerResponse handleRequest(HandlerRequest request, Context context) {

		final String topic = request.getPathParameters().get("topic");
		final String country = request.getQueryStringParameters().get("country");

		context.getLogger().log("Searching for registered trips for " + topic + " and country equals " + country);

		final List<trip> trips = this.repository.findBycountry(topic, country);

		if (trips == null || trips.isEmpty()) {
			return HandlerResponse.builder().setStatusCode(404).build();
		}

		return HandlerResponse.builder().setStatusCode(200).setObjectBody(trips).build();
	}
}