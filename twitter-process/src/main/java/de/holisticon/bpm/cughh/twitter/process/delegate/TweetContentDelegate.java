package de.holisticon.bpm.cughh.twitter.process.delegate;

import de.holisticon.bpm.cughh.twitter.service.TwitterService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TweetContentDelegate implements JavaDelegate {

  @Autowired
  private TwitterService twitterService;

  public void execute(DelegateExecution execution) throws Exception {
    final String content = (String) execution.getVariable("content");

    twitterService.sendTweet(content);
  }

}
