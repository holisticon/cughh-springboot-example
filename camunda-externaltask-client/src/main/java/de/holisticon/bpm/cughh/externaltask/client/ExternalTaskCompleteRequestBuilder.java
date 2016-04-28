package de.holisticon.bpm.cughh.externaltask.client;

import java.util.Map;
import java.util.function.Function;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class ExternalTaskCompleteRequestBuilder implements Function<Map<String, Object>, HttpEntity<String>> {


  private String worker;

  public ExternalTaskCompleteRequestBuilder(final String worker) {
    this.worker = worker;
  }

  @Override
  public HttpEntity<String> apply(final Map<String, Object> vars) {
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    return new HttpEntity<String>(new JSONObject() //
        .put("workerId", worker) //
        .toString(), headers);
  }

}
