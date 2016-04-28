package de.holisticon.bpm.cughh.twitter.service.command;

import static org.slf4j.LoggerFactory.getLogger;

import de.holisticon.bpm.cughh.twitter.service.TwitterService;
import org.slf4j.Logger;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

public class TwitterCommand extends HystrixCommand<Void> {

  private static final HystrixCommand.Setter SETTER = Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("twitterService"))
    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000));
  ;
  private final Logger logger = getLogger(this.getClass());

  private TwitterService twitterService;
  private String message;

  public TwitterCommand(final TwitterService twitterService, final String message) {
    super(SETTER);
    this.twitterService = twitterService;
    this.message = message;
  }

  @Override
  protected Void run() throws Exception {
    twitterService.sendTweet(message);
    return null;
  }

}
