package microservices.book.multiplication.challenge;

import microservices.book.multiplication.user.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

// @ExtendWith => Makes sure that the JUnit5 test loads the extension for Spring so you can use a test context.
@ExtendWith(SpringExtension.class)
// @AutoConfigureJsonTesters: tells Spring to configure beans of type JacksonTester for some fields you declare in the test.
@AutoConfigureJsonTesters
// @WebMvcTest: with the controller class as a parameter, makes Spring treat this as a presentation layer test.
// Thus, it’ll load only the relevant configuration around the controller: validation, serializers, security, error handlers, and so on.
@WebMvcTest(ChallengeAttemptController.class)
public class ChallengeAttemptControllerTest {
    // Data fields
    /**
     * comes with the Spring Boot Test module and helps you develop proper unit tests
     * by allowing you to mock other layers and beans you’re not testing.
     * In this case, you replace the service bean in the context by a mock.
     * You set the expected return values within the test methods, using BDDMockito’s given().
     */
    @MockBean
    private ChallengeService challengeService;

    // MockMvc class is used in Spring to simulate requests to the presentation layer
    // when you make a test that doesn’t load a real server.
    // It’s provided by the test context so you can just inject it in your test.
    @Autowired
    private MockMvc mvc;

    /**
     * JacksonTester may be used to serialize and deserialize objects using the same configuration (i.e., ObjectMapper)
     * as the app would do at runtime.
     * <p>
     * Autowired injects two JacksonTester beans from the test context.
     */
    @Autowired
    private JacksonTester<ChallengeAttemptDTO> jsonRequestAttempt;

    @Autowired
    private JacksonTester<ChallengeAttempt> jsonResultAttempt;

    @Test
    void postValidResult() throws Exception {
        // given
        Users user = new Users(1L, "Nyz");
        long attemptId = 5L;

        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(50, 70, "Nyz", 3500);
        ChallengeAttempt expectedResponse = new ChallengeAttempt(attemptId, user, 50, 70, 3500, true);
        given(challengeService.verifyAttempt(eq(attemptDTO))).willReturn(expectedResponse);

        // when
        MockHttpServletResponse response = mvc.perform(
                        post("/attempts").contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequestAttempt.write(attemptDTO).getJson()))
                .andReturn().getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        then(response.getContentAsString()).isEqualTo(jsonResultAttempt.write(expectedResponse).getJson());
    }

    @Test
    void postInvalidResult() throws Exception {
        // given an attempt with invalid input data
        ChallengeAttemptDTO attemptDTO = new ChallengeAttemptDTO(2000, -70, "Nyz", 1);

        // When
        MockHttpServletResponse response = mvc.perform(
                        post("/attempts").contentType(MediaType.APPLICATION_JSON)
                                .content(jsonRequestAttempt.write(attemptDTO).getJson()))
                .andReturn().getResponse();

        // then
        then(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
