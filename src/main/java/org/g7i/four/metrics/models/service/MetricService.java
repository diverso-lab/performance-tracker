package org.g7i.four.metrics.models.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import io.smallrye.mutiny.tuples.Tuple2;

@ApplicationScoped
public class MetricService implements IMetricService {

	@Inject
	IGitHubService githubService;

	/**
	 * Returns mean and variance of the elements of a list
	 * 
	 * @return mean and variance of the elements of a list
	 */
	public Tuple2<Double,Double> calculateMeanAndVariance(List<Long> list) {

		//We divide the elements of the list by a number to transform the results to days
		int divider = 86400000;

		Double mean = 0.0;
		for(int i = 0; i < list.size(); i++){
			mean = mean + list.get(i)/divider;
		}
		mean = mean/list.size();

		Double variance = 0.0;
		for (int i = 0; i < list.size(); i++) {
			variance += Math.pow(list.get(i)/divider - mean, 2);
		}
		variance = variance/list.size();

		return Tuple2.of(mean,variance);
	}

	/**
	 * Returns GitHub number of releases
	 * 
	 * @return GitHub number of releases
	 */
	public Integer calculateGithubNumberOfReleases() {

		var deployments = this.githubService.getGithubReleases().size();

		return deployments;
	}

	/**
	 * Returns GitHub Deployment Frequency
	 * 
	 * @return GitHub Deployment Frequency
	 */
	public Tuple2<Double,Double> calculateGithubDeploymentFrequency() {

		var deployments = this.githubService.getGithubDeploymentFrequency();

		return this.calculateMeanAndVariance(deployments);
	}

	/**
	 * Returns GitHub Lead Time For Changes
	 * 
	 * @return GitHub Lead Time For Changes
	 */
	public Tuple2<Double,Double> calculateGithubLeadTimeForChanges() {

		var issues = this.githubService.getGithubLeadTimeForChanges();

		return this.calculateMeanAndVariance(issues);		
	}

	/**
	 * Returns GitHub Time To Restore Service
	 * 
	 * @return GitHub Time To Restore Service
	 */
	public Tuple2<Double,Double> calculateGithubTimeToRestoreService() {

		var incidents = this.githubService.getGithubTimeToRestoreService();

		return this.calculateMeanAndVariance(incidents);
	}

	/**
	 * Returns GitHub Change Failure Rate
	 * 
	 * @return GitHub Change Failure Rate
	 */
	public Double calculateGithubChangeFailureRate() {

		return this.githubService.getGithubChangeFailureRate();
	}

}
