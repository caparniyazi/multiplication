package microservices.book.multiplication.challenge;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class provides a REST API to POST the attempts from users.
 * Spring's core feature is the ability to manage and wire up beans in a Spring application context.
 * When your Spring Boot application starts, it performs a component scan to find classes annotated with various stereotype annotations,
 * like @Controller, @Service, @Repository, and in this case, @RestController.
 * This phase is known as the component scan.
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/attempts")
public class ChallengeAttemptController {
    // Data fields
    private final ChallengeService challengeService;

    /**
     * When you add @Valid annotation, Spring Boot will analyze the constraints and
     * throw an Exception if they do not match.
     * By default, the error handler constructs a response with a 400 BAD_REQUEST status code.
     * <p/>
     * Starting with Spring Boot version 2.3, the validation messages are no longer included in the error response by default.
     * This might be confusing for the callers since they don’t know exactly what’s wrong with the request.
     * The reason to not include them is that these messages could potentially expose information to a malicious API client.
     *
     * @param challengeAttemptDTO
     * @return
     */
    @PostMapping
    ResponseEntity<ChallengeAttempt> postResult(@RequestBody @Valid ChallengeAttemptDTO challengeAttemptDTO) {
        return ResponseEntity.ok(challengeService.verifyAttempt(challengeAttemptDTO));
    }

    @GetMapping
    ResponseEntity<List<ChallengeAttempt>> getStatistics(
            @RequestParam("alias") String alias) {
        return ResponseEntity.ok(
                challengeService.getStatsForUser(alias)
        );
    }
}
