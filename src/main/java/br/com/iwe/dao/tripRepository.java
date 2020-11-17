package br.com.iwe.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import br.com.iwe.model.trip;

public class tripRepository {

	private static final DynamoDBMapper mapper = DynamoDBManager.mapper();

	public trip save(final trip trip) {
		mapper.save(trip);
		return trip;
	}

	public List<trip> findByPeriod(final String topic, final String starts, final String ends) {

		final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(topic));
		eav.put(":val2", new AttributeValue().withS(starts));
		eav.put(":val3", new AttributeValue().withS(ends));

		final DynamoDBQueryExpression<trip> queryExpression = new DynamoDBQueryExpression<trip>()
				.withKeyConditionExpression("topic = :val1 and date between :val2 and :val3")
				.withExpressionAttributeValues(eav);

		final List<trip> trips = mapper.query(trip.class, queryExpression);

		return trips;
	}

	public List<trip> findBycountry(final String topic, final String country) {

		final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(topic));
		eav.put(":val2", new AttributeValue().withS(country));

		final DynamoDBQueryExpression<trip> queryExpression = new DynamoDBQueryExpression<trip>()
				.withIndexName("countryIndex").withConsistentRead(false)
				.withKeyConditionExpression("topic = :val1 and country=:val2").withExpressionAttributeValues(eav);

		final List<trip> trips = mapper.query(trip.class, queryExpression);

		return trips;
	}

	public List<trip> findByIscity(final String topic, final String iscity) {

		final Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":val1", new AttributeValue().withS(topic));
		eav.put(":val2", new AttributeValue().withS(iscity));

		final Map<String, String> expression = new HashMap<>();

		// city is a reserver word in DynamoDB
		expression.put("#city", "city");

		final DynamoDBQueryExpression<trip> queryExpression = new DynamoDBQueryExpression<trip>()
				.withIndexName("cityIndex").withConsistentRead(false)
				.withKeyConditionExpression("topic = :val1 and #city=:val2").withExpressionAttributeValues(eav)
				.withExpressionAttributeNames(expression);

		final List<trip> trips = mapper.query(trip.class, queryExpression);

		return trips;
	}
}