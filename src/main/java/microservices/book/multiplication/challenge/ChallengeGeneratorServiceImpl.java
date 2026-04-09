package microservices.book.multiplication.challenge;

import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * An empty implementation of the ChallengeGeneratorService interface.
 * To instruct Spring to load this service implementation in the context, you annotate the class with @Service.
 * You can later inject this service into other layers by using the interface and not the implementation.
 * This way, you keep loose coupling since you could swap the implementation without needing to change anything in other layers.
 */
@Service
public class ChallengeGeneratorServiceImpl implements ChallengeGeneratorService {
    // Data fields
    private final static int MINIMUM_FACTOR = 11;
    private final static int MAXIMUM_FACTOR = 100;
    private final Random random;

    ChallengeGeneratorServiceImpl() {
        this.random = new Random();
    }

    protected ChallengeGeneratorServiceImpl(final Random random) {
        this.random = random;
    }

    private int next() {
        return random.nextInt(MAXIMUM_FACTOR - MINIMUM_FACTOR) + MINIMUM_FACTOR;
    }

    @Override
    public Challenge randomChallenge() {
        return new Challenge(next(), next());
    }
}
