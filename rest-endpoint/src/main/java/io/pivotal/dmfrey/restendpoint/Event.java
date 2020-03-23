package io.pivotal.dmfrey.restendpoint;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class Event {

	String message;

	@JsonCreator
	public Event(
			@JsonProperty( "message" ) final String message
	) {

		this.message = message;

	}

}
