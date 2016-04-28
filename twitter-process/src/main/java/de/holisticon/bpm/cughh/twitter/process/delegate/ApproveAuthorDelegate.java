package de.holisticon.bpm.cughh.twitter.process.delegate;

import de.holisticon.bpm.cughh.twitter.process.command.DetermineApprovalStatusCommand;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApproveAuthorDelegate implements JavaDelegate {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Value("${approval.service.url}")
  private String approvalServiceUrl;

  @Override
  public void execute(final DelegateExecution execution) throws Exception {
    final String emailAddress = (String) execution.getVariable("email");
    final DetermineApprovalStatusCommand command = new DetermineApprovalStatusCommand(emailAddress, approvalServiceUrl);
    final String approvalStatus = command.execute();
    logger.info("Approval status for {} is {}.", emailAddress, approvalStatus);
    execution.setVariable("approvalStatus", approvalStatus);
  }

}
