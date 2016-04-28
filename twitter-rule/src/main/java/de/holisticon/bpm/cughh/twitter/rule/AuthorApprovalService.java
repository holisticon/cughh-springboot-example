package de.holisticon.bpm.cughh.twitter.rule;


import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.engine.DecisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class AuthorApprovalService implements Function<String, String> {

  public static final String EMAIL = "email";
  public static final String APPROVAL_STATUS = "approvalStatus";
  public static final String AUTHOR_DMN_RESOURCE = "TwitterDemoProcess_AuthorApproval.dmn";
  public static final String DECISION_KEY = "TwitterDemoProcess_AuthorApproval";

  @Autowired
  private DecisionService decisionService;

  public String apply(final String emailAddress) {

    Objects.requireNonNull(emailAddress, "E-Mail address must be not null");

    final Map<String, Object> context = new HashMap<>();
    context.put(EMAIL, emailAddress);

    final DmnDecisionTableResult result = decisionService.evaluateDecisionTableByKey(DECISION_KEY, context);

    return (String) result.get(0).get(APPROVAL_STATUS);
  }
}
