package microservices.book.multiplication.challenge;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Value;

/**
 * Attempt coming from the user.
 */
@Value
// A shortcut to create an immutable class with an all-args-constructor and toString, equals, and hashCode methods.
// It will also set your data fields to be private final.
public class ChallengeAttemptDTO {
    @Min(1)
    @Max(99)
    int factorA, factorB;
    @NotBlank
    String userAlias;
    @Positive
    int guess;
}
