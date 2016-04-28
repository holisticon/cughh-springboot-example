package de.holisticon.bpm.cughh.twitter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
@ComponentScan
public class TwitterConfiguration {

  @Value("${twitter4j.debug:false}")
  private boolean debug;

  @Value("${twitter4j.oauth.consumerKey}")
  private String consumerKey;

  @Value("${twitter4j.oauth.consumerSecret}")
  private String consumerSecret;

  @Value("${twitter4j.oauth.accessToken}")
  private String accessToken;

  @Value("${twitter4j.oauth.accessTokenSecret}")
  private String accessTokenSecret;

  @Bean
  public Twitter twitter() {
    final ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
    configurationBuilder.setDebugEnabled(debug).setOAuthConsumerKey(consumerKey).setOAuthConsumerSecret(consumerSecret)
      .setOAuthAccessToken(accessToken).setOAuthAccessTokenSecret(accessTokenSecret);

    return new TwitterFactory(configurationBuilder.build()).getInstance();
  }

}
