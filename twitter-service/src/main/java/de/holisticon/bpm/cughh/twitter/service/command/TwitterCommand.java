package de.holisticon.bpm.cughh.twitter.service.command;

import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

import twitter4j.Twitter;

public class TwitterCommand extends HystrixCommand<Void> {

  private static final HystrixCommand.Setter SETTER = Setter
      .withGroupKey(HystrixCommandGroupKey.Factory.asKey("twitter"))
      .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000));;
  private final Logger logger = getLogger(this.getClass());

  private Twitter twitter;
  private String message;
  private boolean disable;

  public TwitterCommand(final Twitter worker, final String message, final boolean disable) {
    super(SETTER);
    this.twitter = worker;
    this.message = message;
    this.disable = disable;
  }

  @Override
  protected Void run() throws Exception {
    if (disable) {
      logger.info("Twitter Service is disabled. Content was {}", message);
    } else {
      logger.info("Tweeting the message {}", message);
      twitter.updateStatus(message);
    }
    return null;
  }

}
