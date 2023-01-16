package org.g7i.four.metrics.models.entities.issue;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Issue Event
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubIssueEvent {

	@NonNull
    @JsonProperty("event")
	String event;

	@NonNull
	@JsonProperty("created_at")
	Date createdAt;

}