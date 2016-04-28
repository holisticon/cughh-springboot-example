package de.holisticon.bpm.cughh.twitter.service.command;

import java.util.ArrayList;
import java.util.List;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import de.holisticon.bpm.cughh.externaltask.client.ExternalTask;
import de.holisticon.bpm.cughh.externaltask.client.ExternalTaskClient;


/**
 * Command for retrieving the external tasks to twitter.
 *
 * @author Simon Zambrovski, Holisticon AG
 *
 */
public class TwitterExternalTaskQueryCommand extends HystrixCommand<List<ExternalTask>> {

  private static final HystrixCommand.Setter SETTER = Setter
      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("camunda-rest"))
      .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000));;
  private ExternalTaskClient client;
  private String topicName;
  private String[] variables;

  public TwitterExternalTaskQueryCommand(final ExternalTaskClient client, final String topicName,
      final String[] variables) {
    super(SETTER);
    this.client = client;
    this.topicName = topicName;
    this.variables = variables;
  }

  @Override
  protected List<ExternalTask> run() throws Exception {
    return client.getExternalTasks(topicName, variables);
  }

  @Override
  protected List<ExternalTask> getFallback() {
    return new ArrayList<>();
  }

}
