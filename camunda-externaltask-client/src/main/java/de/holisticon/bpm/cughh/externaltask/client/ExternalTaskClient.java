package de.holisticon.bpm.cughh.externaltask.client;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.client.RestTemplate;

/**
 * F Client for working with external tasks.
 *
 * @author Simon Zambrovski, Holisticon AG.
 *
 */
public class ExternalTaskClient {

  private final RestTemplate rest = new RestTemplate();

  private String baseUrl;
  private String worker;

  public ExternalTaskClient(String worker, String baseUrl) {
    this.worker = worker;
    this.baseUrl = baseUrl;
  }

  /**
   * Reads external tasks.
   *
   * @return list of external tasks.
   */
  public List<ExternalTask> getExternalTasks(final String topic, final String[] variableNames) {
    final String url = baseUrl + "/external-task/fetchAndLock";
    final String result = rest.postForObject(url, new ExternalTaskRequestBuilder(worker, topic).apply(variableNames),
        String.class);
    final List<ExternalTask> externalTasks = new ExternalTasksExtractor().apply(result);
    return externalTasks;
  }

  /**
   * Completes an external task.
   *
   * @param task
   *          external task to complete.
   */
  public void completeTask(final ExternalTask task) {
    final String url = String.format(baseUrl + "/external-task/%s/complete", task.getId());
    rest.postForObject(url, new ExternalTaskCompleteRequestBuilder(worker).apply(new HashMap<>()), String.class);
  }

}
