package org.g7i.four.metrics.models.restservices;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.g7i.four.metrics.models.entities.issue.GithubIssueEvent;
import org.g7i.four.metrics.models.entities.issue.GithubIssueResult;
import org.g7i.four.metrics.models.entities.stash.GithubRelease;

@Path("/")
@RegisterRestClient
@RegisterClientHeaders(GitHubRestServiceHeaderFactory.class)
public interface GitHubRestService {

	int MAXRETRIES = 5;

	@GET
	@Path("repos/{owner}/{repo}/releases")
	@Produces(MediaType.APPLICATION_JSON)
	@Retry(maxRetries = MAXRETRIES)
	List<GithubRelease> getGithubReleases(@PathParam("owner") String owner, @PathParam("repo") String repo,
								@QueryParam("page") Integer page, @QueryParam("per_page") Integer per_page);

	@GET
	@Path("search/issues")
	@Produces(MediaType.APPLICATION_JSON)
	@Retry(maxRetries = MAXRETRIES)
	GithubIssueResult getGithubIssues(@QueryParam("q") String query, @QueryParam("page") Integer page,
							   @QueryParam("per_page") Integer per_page);

	@GET
	@Path("repos/{owner}/{repo}/issues/{number}/events")
	@Produces(MediaType.APPLICATION_JSON)
	@Retry(maxRetries = MAXRETRIES)
	List<GithubIssueEvent> getGithubIssuesEvents(@PathParam("owner") String owner, @PathParam("repo") String repo,
										@PathParam("number") Integer number);

}
