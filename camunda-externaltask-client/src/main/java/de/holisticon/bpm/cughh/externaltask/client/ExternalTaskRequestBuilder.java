package de.holisticon.bpm.cughh.externaltask.client;

import java.util.function.Function;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 * External command request builder.
 */
public class ExternalTaskRequestBuilder implements Function<String[], HttpEntity<String>> {

  private static final int MAX_TASKS = 5;
  private static final int DURATTION = 1000;
  private String topicName;
  private String worker;

  public ExternalTaskRequestBuilder(final String worker, final String topicName) {
    this.worker = worker;
    this.topicName = topicName;
  }

  @Override
  public HttpEntity<String> apply(final String[] variableNames) {
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    // prepare vars
    final JSONArray vars = new JSONArray();
    for (String varName : variableNames) {
      vars.put(varName);
    }

    return new HttpEntity<String>(new JSONObject() //
        .put("workerId", worker) //
        .put("maxTasks", MAX_TASKS) //
        .put("topics",
            new JSONArray().put(new JSONObject() //
                .put("topicName", topicName) //
                .put("lockDuration", DURATTION) //
                .put("variables", vars //
    ))).toString(), headers);
  }
}
