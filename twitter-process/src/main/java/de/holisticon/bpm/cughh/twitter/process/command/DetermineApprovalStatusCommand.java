package de.holisticon.bpm.cughh.twitter.process.command;

  import static org.slf4j.LoggerFactory.getLogger;

  import java.util.function.Function;

  import org.camunda.bpm.engine.impl.util.json.JSONArray;
  import org.camunda.bpm.engine.impl.util.json.JSONObject;
  import org.slf4j.Logger;
  import org.springframework.http.HttpEntity;
  import org.springframework.http.HttpHeaders;
  import org.springframework.http.MediaType;
  import org.springframework.web.client.RestTemplate;

  import com.netflix.hystrix.HystrixCommand;
  import com.netflix.hystrix.HystrixCommandGroupKey;
  import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Command for determining the approval status for the tweet for a given email
 * address.
 *
 * @author Simon Zambrovski, Holisticon AG
 */
public class DetermineApprovalStatusCommand extends HystrixCommand<String> {

  private static final String DEFAULT_VALUE = "REQUIRED";
  private static final HystrixCommand.Setter SETTER = Setter
    .withGroupKey(HystrixCommandGroupKey.Factory.asKey("RestService"))
    .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000));

  private final Logger logger = getLogger(this.getClass());
  private final RestTemplate rest = new RestTemplate();
  private final HttpEntity<String> request;
  private String serviceUrl;

  /**
   * Constructs the command.
   *
   * @param emailAddress
   *          email address to check for.
   * @param serviceUrl
   *          SEI of the service.
   */
  public DetermineApprovalStatusCommand(final String emailAddress, final String serviceUrl) {
    super(SETTER);
    this.serviceUrl = serviceUrl;
    this.request = RequestBuilder.DEFAULT.apply(emailAddress);
    logger.info("Determining approval status for {}", emailAddress);

  }

  @Override
  protected String getFallback() {
    logger.info("RuleService not available - using fallback: {}", DEFAULT_VALUE);
    return DEFAULT_VALUE;
  }

  @Override
  protected String run() throws Exception {

    final String result = rest.postForObject(serviceUrl, this.request, String.class);
    final String approvalStatus = ResultExtractor.DEFAULT.apply(result);

    logger.info("Evaluated result: {}", approvalStatus);

    return approvalStatus;
  }

  /**
   * REST request builder.
   */
  public static class RequestBuilder implements Function<String, HttpEntity<String>> {

    public static final RequestBuilder DEFAULT = new RequestBuilder();

    @Override
    public HttpEntity<String> apply(final String emailAddress) {
      final HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      return new HttpEntity<String>(new JSONObject()
        .put("variables",
          new JSONObject().put("email", new JSONObject().put("value", emailAddress).put("type", "String")))
        .toString(), headers);
    }
  }

  /**
   * REST result extractor.
   */
  public static class ResultExtractor implements Function<String, String> {

    public static final ResultExtractor DEFAULT = new ResultExtractor();

    @Override
    public String apply(final String result) {
      return new JSONArray(result).getJSONObject(0).getJSONObject("approvalStatus").getString("value");
    }

  }
}
