package de.holisticon.bpm.cughh.twitter.process.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class RejectionNotificationDelegate implements JavaDelegate {

  private final Logger logger = getLogger(this.getClass());

  public void execute(DelegateExecution execution) throws Exception {
    String content = (String) execution.getVariable("content");
    String comments = (String) execution.getVariable("comments");

    logger.info("\n\n\nHi!\n\n"
      + "Unfortunately your tweet has been rejected.\n\n"
      + "Original content: {}\n\n"
      + "Comment: {}\n\n"
      + "Sorry, please try with better content the next time :-)\n\n\n", content, comments);
  }

}
