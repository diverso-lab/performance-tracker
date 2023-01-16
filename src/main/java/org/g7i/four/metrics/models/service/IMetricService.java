package org.g7i.four.metrics.models.service;

import io.smallrye.mutiny.tuples.Tuple2;

public interface IMetricService {

	Integer calculateGithubNumberOfReleases();

	Tuple2<Double,Double> calculateGithubDeploymentFrequency();

	Tuple2<Double,Double> calculateGithubLeadTimeForChanges();

	Tuple2<Double,Double> calculateGithubTimeToRestoreService();

	Double calculateGithubChangeFailureRate();

}
