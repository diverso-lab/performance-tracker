package org.g7i.four.metrics.scheduler;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.metrics.MetricRegistry;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.g7i.four.metrics.models.service.IMetricService;
import org.jboss.logging.Logger;

import io.quarkus.scheduler.Scheduled;
import io.smallrye.mutiny.tuples.Tuple2;

/**
 * This class catch all metrics each 24 hours.
 */
@ApplicationScoped
public class MetricsScheduler {

	private Integer githubNumberOfDeployments;
	private Tuple2<Double,Double> githubDeploymentFrequency;
	private Tuple2<Double,Double> githubLeadTimeForChanges;
	private Tuple2<Double,Double> githubTimeToRestoreService;
	private Double githubChangeFailureRate;

	@Inject
	MetricRegistry registry;

	@Inject
	IMetricService metricService;

	private static final Logger LOG = Logger.getLogger(MetricsScheduler.class);

	/**
	 * Scheduler method. Each x time to get new metrics.
	 */
	@Scheduled(every = "{metrics.scheduler}")
	void getMetrics() {
		LOG.info("Retrieving data and calculating metrics");
		LOG.info("Please note that this can take some time");
		LOG.info("Visit http://localhost:8080/q/metrics/application to see the results (metrics there will show in seconds)");
		try {

			//Number Of Deployments
			LOG.info("Number of Releases: "+ this.getGithubNumberOfReleases());
			//Deployment Frequency
			LOG.info("Deployment Frequency (days): "+ this.getGithubDeploymentFrequency());
			LOG.info("Deployment Frequency standard deviation: "+ this.getGithubDeploymentFrequencyVariance());
			//Lead Time For Changes
			LOG.info("Lead Time For Changes (days): "+ this.getGithubLeadTimeForChanges());
			LOG.info("Lead Time For Changes standard deviation: "+ this.getGithubLeadTimeForChangesVariance());
			//Time To Restore Service
			LOG.info("Time To Restore Service (days): "+ this.getGithubTimeToRestoreService());
			LOG.info("Time To Restore Service standard deviation: "+ this.getGithubTimeToRestoreServiceVariance());
			//Change Failure Rate
			LOG.info("Change Failure Rate (%): "+ this.getGithubChangeFailureRate()*100);
			
		} catch (Exception e) {
		}
	}

	///////////////////////////////////////////////////////////////////////////

	@Gauge(name = "github.number.deployments", unit = MetricUnits.NONE)
	public Integer getGithubNumberOfReleases() {
		this.githubNumberOfDeployments = this.githubNumberOfDeployments == null ? this.metricService.calculateGithubNumberOfReleases() : this.githubNumberOfDeployments;
		return this.githubNumberOfDeployments;
	}

	@Gauge(name = "github.deployment.frequency", unit = MetricUnits.MILLISECONDS)
	public Double getGithubDeploymentFrequency() {
		this.githubDeploymentFrequency = this.githubDeploymentFrequency == null ? this.metricService.calculateGithubDeploymentFrequency() : this.githubDeploymentFrequency;
		return this.githubDeploymentFrequency.getItem1();
	}

	@Gauge(name = "github.deployment.frequency.variance", unit = MetricUnits.MILLISECONDS)
	public Double getGithubDeploymentFrequencyVariance() {
		this.githubDeploymentFrequency = this.githubDeploymentFrequency == null ? this.metricService.calculateGithubDeploymentFrequency() : this.githubDeploymentFrequency;
		return this.githubDeploymentFrequency.getItem2();
	}

	@Gauge(name = "github.lead.time.for.changes", unit = MetricUnits.MILLISECONDS)
	public Double getGithubLeadTimeForChanges() {
		this.githubLeadTimeForChanges = this.githubLeadTimeForChanges == null ? this.metricService.calculateGithubLeadTimeForChanges() : this.githubLeadTimeForChanges;
		return this.githubLeadTimeForChanges.getItem1();
	}

	@Gauge(name = "github.lead.time.for.changes.variance", unit = MetricUnits.NONE)
	public Double getGithubLeadTimeForChangesVariance() {
		this.githubLeadTimeForChanges = this.githubLeadTimeForChanges == null ? this.metricService.calculateGithubLeadTimeForChanges() : this.githubLeadTimeForChanges;
		return this.githubLeadTimeForChanges.getItem2();
	}

	@Gauge(name = "github.time.to.restore.service", unit = MetricUnits.MILLISECONDS)
	public Double getGithubTimeToRestoreService() {
		this.githubTimeToRestoreService = this.githubTimeToRestoreService == null ? this.metricService.calculateGithubTimeToRestoreService() : this.githubTimeToRestoreService;
		return this.githubTimeToRestoreService.getItem1();
	}

	@Gauge(name = "github.time.to.restore.service.variance", unit = MetricUnits.NONE)
	public Double getGithubTimeToRestoreServiceVariance() {
		this.githubTimeToRestoreService = this.githubTimeToRestoreService == null ? this.metricService.calculateGithubTimeToRestoreService(): this.githubTimeToRestoreService;
		return this.githubTimeToRestoreService.getItem2();
	}

	@Gauge(name = "github.failure.rate", unit = MetricUnits.PERCENT)
	public Double getGithubChangeFailureRate() {
		this.githubChangeFailureRate = this.githubChangeFailureRate == null ? this.metricService.calculateGithubChangeFailureRate() : this.githubChangeFailureRate;
		return this.githubChangeFailureRate;
	}

}
