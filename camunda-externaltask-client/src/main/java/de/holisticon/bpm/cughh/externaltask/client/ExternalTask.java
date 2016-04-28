package de.holisticon.bpm.cughh.externaltask.client;

import java.util.Map;

/**
 * Simple abstraction for external task representing a unit of work.
 *
 * @author Simon Zambrovski, Holisticon AG.
 *
 */
public class ExternalTask {

  private String activityId;
  private String topicName;
  private String id;
  private Map<String, Object> variables;

  public ExternalTask(String activityId, String topicName, String id, Map<String, Object> variables) {
    this.activityId = activityId;
    this.topicName = topicName;
    this.id = id;
    this.variables = variables;
  }

  public String getName() {
    return activityId;
  }

  public void setName(String name) {
    this.activityId = name;
  }

  public String getTopic() {
    return topicName;
  }

  public void setTopic(String topic) {
    this.topicName = topic;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Map<String, Object> getVariables() {
    return variables;
  }

  public void setVariables(Map<String, Object> variables) {
    this.variables = variables;
  }

}
