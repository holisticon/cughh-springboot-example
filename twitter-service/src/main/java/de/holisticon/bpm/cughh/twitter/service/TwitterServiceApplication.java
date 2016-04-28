package de.holisticon.bpm.cughh.twitter.service;

import de.holisticon.bpm.cughh.externaltask.client.ExternalTask;
import de.holisticon.bpm.cughh.externaltask.client.ExternalTaskClient;
import de.holisticon.bpm.cughh.twitter.service.command.TwitterCommand;
import de.holisticon.bpm.cughh.twitter.service.command.TwitterExternalTaskCompleteCommand;
import de.holisticon.bpm.cughh.twitter.service.command.TwitterExternalTaskQueryCommand;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import twitter4j.Twitter;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@SpringBootApplication
@EnableScheduling
public class TwitterServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(TwitterServiceApplication.class, args);
  }


  private final Logger logger = getLogger(this.getClass());

  @Bean
  public ExternalTaskClient externalTaskClient(@Value("${twitter.worker.name}") String workerName,
                                               @Value("${camunda.rest.base.url}") String baseUrl) {
    return new ExternalTaskClient(workerName, baseUrl);
  }

  @Autowired
  private ExternalTaskClient externalTaskClient;

  @Autowired
  private TwitterService twitterService;

  @Scheduled(fixedRate = 10000)
  public void executeExternalTasks() {

    // query
    final TwitterExternalTaskQueryCommand queryCommand = new TwitterExternalTaskQueryCommand(externalTaskClient, "twitter",
      new String[] { "content" });
    final List<ExternalTask> externalTasks = queryCommand.execute();
    if (externalTasks.isEmpty()) {
      logger.info("ZZzzzz...");
    } else {
      logger.info("Found {} external tasks for twitter.", externalTasks.size());
    }

    for (final ExternalTask task : externalTasks) {
      // send the tweet
      final TwitterCommand twitterCommand = new TwitterCommand(twitterService, (String) task.getVariables().get("content"));
      twitterCommand.execute();

      logger.info("Completing the task {}.", task.getName());
      // complete the task
      final TwitterExternalTaskCompleteCommand completeCommand = new TwitterExternalTaskCompleteCommand(externalTaskClient, task);
      completeCommand.execute();
    }
  }
}
