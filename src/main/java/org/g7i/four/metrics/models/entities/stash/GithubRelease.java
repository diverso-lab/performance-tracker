package org.g7i.four.metrics.models.entities.stash;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * Release
 */
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRelease {

	@NonNull
	@JsonProperty("prerelease")
	Boolean prerelease;

	@NonNull
	@JsonProperty("id")
	Long id;

	@NonNull
	@JsonProperty("name")
	String name;

	@NonNull
	@JsonProperty("created_at")
	Date createdAt;

	@NonNull
	@JsonProperty("published_at")
	Date publishedAt;

}
