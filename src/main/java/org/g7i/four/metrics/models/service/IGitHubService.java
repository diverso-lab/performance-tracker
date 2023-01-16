package org.g7i.four.metrics.models.service;

import java.util.List;

import org.g7i.four.metrics.models.entities.stash.GithubRelease;

public interface IGitHubService {

	List<GithubRelease> getGithubReleases();

	List<Long> getGithubDeploymentFrequency();

	List<Long> getGithubLeadTimeForChanges();

	List<Long> getGithubTimeToRestoreService();

	Double getGithubChangeFailureRate();


}
