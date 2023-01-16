package org.g7i.four.metrics.models.entities.issue;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Issue Result
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubIssueResult {

	@NonNull
    @JsonProperty("total_count")
	Long totalCount;

	@NonNull
	@JsonProperty("items")
	List<GithubIssue> items;

}