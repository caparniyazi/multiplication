package microservices.book.multiplication.challenge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/challenges")
@RequiredArgsConstructor
@Slf4j  // Slf4j creates a logger named log.
public class ChallengeController {
    private final ChallengeGeneratorService challengeGeneratorService;

    /**
     * As a default in Spring Boot and if not instructed otherwise, the response will
     * be serialized as JSON and included in the response body.
     *
     * @return
     */
    @GetMapping("/random")
    Challenge getRandomChallenge() {
        Challenge challenge = challengeGeneratorService.randomChallenge();
        log.info("Generating a random challenge: {}", challenge);

        return challenge;
    }
}
