package de.holisticon.bpm.cughh.externaltask.client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


/**
 * Extracts external tasks.
 */
public class ExternalTasksExtractor implements Function<String, List<ExternalTask>> {

  @SuppressWarnings("unchecked")
  @Override
  public List<ExternalTask> apply(final String json) {

    final List<ExternalTask> result = new ArrayList<>();

    final JSONArray externalTasks = new JSONArray(json);
    for (int i = 0; i < externalTasks.length(); i++) {
      final JSONObject externalTaskJson = externalTasks.getJSONObject(i);
      final String id = externalTaskJson.getString("id");
      final String topicName = externalTaskJson.getString("topicName");
      final String activityId = externalTaskJson.getString("activityId");
      // extract variables
      final JSONObject variables = externalTaskJson.getJSONObject("variables");
      final Map<String, Object> vars = new HashMap<>();
      final Iterator<String> variableIterator = variables.keys();
      while (variableIterator.hasNext()) {
        final String varName = variableIterator.next();
        final String type = variables.getJSONObject(varName).getString("type");
        switch (type) {
        case "String":
          vars.put(varName, variables.getJSONObject(varName).getString("value"));
          break;
        case "Int":
          vars.put(varName, variables.getJSONObject(varName).getInt("value"));
          break;
        default:
          // TODO implement all types
          // unknown type, skip it....
          break;
        }
      }
      result.add(new ExternalTask(activityId, topicName, id, vars));
    }
    return result;
  }

}
