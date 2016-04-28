package de.holisticon.bpm.cughh.twitter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import twitter4j.Twitter;
import twitter4j.TwitterException;

@Component
public class TwitterService {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Autowired
  private Twitter twitter;

  @Value("${twitter.enabled:true}")
  private boolean twitterEnabled;

  public void sendTweet(String content) throws TwitterException {
    if (twitterEnabled) {
      twitter.updateStatus(content);
      logger.info("sent tweet: '{}'", content);
    } else {
      logger.info("twitterDisabled: would send '{}'", content);
    }
  }
}
