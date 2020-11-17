package br.com.iwe.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = "trip")
public class trip {

	@DynamoDBHashKey(attributeName = "topic")
	private String topic;
	@DynamoDBRangeKey(attributeName = "date")
	private String date;

	@DynamoDBIndexRangeKey(attributeName = "country", localSecondaryIndexName = "countryIndex")
	private String country;

	@DynamoDBAttribute(attributeName = "url")
	private String url;
	@DynamoDBAttribute(attributeName = "description")
	private String description;

	@DynamoDBIndexRangeKey(attributeName = "city", localSecondaryIndexName = "cityIndex")
	private String city;

	public trip(String topic, String date, String country, String url, String description, String city) {
		super();
		this.topic = topic;
		this.date = date;
		this.country = country;
		this.url = url;
		this.description = description;
		this.city = city;
	}

	public trip() {
		super();
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getdate() {
		return date;
	}

	public void setdate(String date) {
		this.date = date;
	}

	public String getcountry() {
		return country;
	}

	public void setcountry(String country) {
		this.country = country;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getcity() {
		return city;
	}

	public void setcity(String city) {
		this.city = city;
	}

}
