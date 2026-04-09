package microservices.book.multiplication.challenge;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The class represents a Challenge to solve a multiplication (a * b).
 */
@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Challenge {
    // Data fields
    private int factorA;
    private int factorB;
}
