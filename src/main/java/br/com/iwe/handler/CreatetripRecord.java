package br.com.iwe.handler;

import java.io.IOException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.iwe.dao.tripRepository;
import br.com.iwe.model.HandlerRequest;
import br.com.iwe.model.HandlerResponse;
import br.com.iwe.model.trip;

public class CreatetripRecord implements RequestHandler<HandlerRequest, HandlerResponse> {
	
	private final tripRepository repository = new tripRepository();

	@Override
	public HandlerResponse handleRequest(final HandlerRequest request, final Context context) {

		trip trip = null;
		try {
			trip = new ObjectMapper().readValue(request.getBody(), trip.class);
		} catch (IOException e) {
			return HandlerResponse.builder().setStatusCode(400).setRawBody("There is a error in your trip!").build();
		}
		context.getLogger().log("Creating a new trip record for the topic " + trip.getTopic());
		final trip tripRecorded = repository.save(trip);
		return HandlerResponse.builder().setStatusCode(201).setObjectBody(tripRecorded).build();
	}
}