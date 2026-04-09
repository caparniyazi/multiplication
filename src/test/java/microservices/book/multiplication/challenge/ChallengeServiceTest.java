package microservices.book.multiplication.challenge;

import microservices.book.multiplication.user.Users;
import microservices.book.multiplication.user.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
public class ChallengeServiceTest {
    // Data fields
    private ChallengeService challengeService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private ChallengeAttemptRepository attemptRepository;

    @BeforeEach
    public void setUp() {
        challengeService = new ChallengeServiceImpl(
                usersRepository,
                attemptRepository);
    }


    @Test
    public void checkCorrectAttempts() {
        // given
        given(attemptRepository.save(any())).will(returnsFirstArg());

        ChallengeAttemptDTO attemptDTO =
                new ChallengeAttemptDTO(50, 60, "NÇ", 3000);

        // when
        ChallengeAttempt resultAttempt =
                challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isTrue();

        verify(usersRepository).save(new Users("NÇ"));
        verify(attemptRepository).save(resultAttempt);
    }

    @Test
    public void checkExistingUserTest() {
        // given
        Users existingUser = new Users(1L, "john_doe");
        given(usersRepository.findByAlias("john_doe"))
                .willReturn(Optional.of(existingUser));
        ChallengeAttemptDTO attemptDTO =
                new ChallengeAttemptDTO(50, 60, "john_doe", 5000);

        // when
        ChallengeAttempt resultAttempt =
                challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isFalse();
        then(resultAttempt.getUsers()).isEqualTo(existingUser);
        verify(usersRepository, never()).save(any());
        verify(attemptRepository).save(resultAttempt);
    }


    @Test
    public void checkWrongAttemptTest() {
        // given
        ChallengeAttemptDTO attemptDTO =
                new ChallengeAttemptDTO(50, 60, "NÇ", 5000);

        // when
        ChallengeAttempt resultAttempt =
                challengeService.verifyAttempt(attemptDTO);

        // then
        then(resultAttempt.isCorrect()).isFalse();
        verify(usersRepository).save(new Users("NÇ"));
        verify(attemptRepository).save(resultAttempt);
    }
}
