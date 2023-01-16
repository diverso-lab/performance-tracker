package org.g7i.four.metrics.models.entities.issue;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Issue
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubIssue {

	@NonNull
    @JsonProperty("number")
	Integer number;

	@NonNull
	@JsonProperty("created_at")
	Date createdAt;

	@NonNull
	@JsonProperty("updated_at")
	Date updatedAt;

	Date lastCommitDate;

	@JsonProperty("closed_at")
	Date closedAt;

    @NonNull
	@JsonProperty("title")
	String title;

}