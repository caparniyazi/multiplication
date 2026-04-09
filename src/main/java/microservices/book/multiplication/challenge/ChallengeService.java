package microservices.book.multiplication.challenge;

import java.util.List;

public interface ChallengeService {
    /**
     * Verifies if an attempt coming from the presentation layer is correct or not.
     * Data Transfer Objects (DTOs) cary data between different parts of the system.
     *
     * @param resultAttempt
     * @return
     */
    ChallengeAttempt verifyAttempt(ChallengeAttemptDTO resultAttempt);


    /**
     * Gets the statistics for a given user.
     *
     * @param userAlias the user's alias
     * @return a list of the last 10 {@link ChallengeAttempt}
     * objects created by the user.
     */
    List<ChallengeAttempt> getStatsForUser(String userAlias);
}
