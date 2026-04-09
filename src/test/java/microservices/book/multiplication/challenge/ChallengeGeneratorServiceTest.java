package microservices.book.multiplication.challenge;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Random;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
public class ChallengeGeneratorServiceTest {
    // Data fields
    private ChallengeGeneratorService challengeGeneratorService;

    @Spy    // To stub an object.
    private Random random;

    @BeforeEach
    public void setUp() {
        // This happens before each test starts.
        challengeGeneratorService = new ChallengeGeneratorServiceImpl(random);
    }

    @Test
    public void generateRandomFactorsBetweenExpectedLimits() {
        // The way to generate random numbers between 11 and 99 is to get a random number between 0 and 89 and add 11 to it.
        given(random.nextInt(89)).willReturn(20, 30);

        // When we generate a challenge
        Challenge challenge = challengeGeneratorService.randomChallenge();

        // then the challenge contains factors as expected.
        then(challenge).isEqualTo(new Challenge(31, 41));
    }
}
