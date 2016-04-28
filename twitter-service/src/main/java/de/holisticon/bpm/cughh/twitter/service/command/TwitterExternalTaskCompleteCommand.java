package de.holisticon.bpm.cughh.twitter.service.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import de.holisticon.bpm.cughh.externaltask.client.ExternalTask;
import de.holisticon.bpm.cughh.externaltask.client.ExternalTaskClient;


/**
 * Command for completing the external tasks.
 *
 * @author Simon Zambrovski, Holisticon AG
 *
 */
public class TwitterExternalTaskCompleteCommand extends HystrixCommand<Void> {

  private static final HystrixCommand.Setter SETTER = Setter
      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("camunda-rest"))
      .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000));;

  private ExternalTaskClient client;
  private ExternalTask task;

  public TwitterExternalTaskCompleteCommand(final ExternalTaskClient client, final ExternalTask task) {
    super(SETTER);
    this.client = client;
    this.task = task;
  }

  @Override
  protected Void run() throws Exception {
    client.completeTask(task);
    return null;
  }
}
