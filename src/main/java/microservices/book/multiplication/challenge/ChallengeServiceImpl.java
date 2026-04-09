package microservices.book.multiplication.challenge;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microservices.book.multiplication.user.Users;
import microservices.book.multiplication.user.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChallengeServiceImpl implements ChallengeService {
    // Data fields
    private final UsersRepository userRepository;
    private final ChallengeAttemptRepository attemptRepository;

    @Override
    public ChallengeAttempt verifyAttempt(ChallengeAttemptDTO attemptDTO) {
        // Check if the user already exists for that alias, otherwise create it
        Users user = userRepository.findByAlias(attemptDTO.getUserAlias()).orElseGet(() -> {
            log.info("Creating new user with alias {}", attemptDTO.getUserAlias());
            return userRepository.save(new Users(attemptDTO.getUserAlias()));
        });


        // Check if the attempt is correct.
        boolean isCorrect = attemptDTO.getGuess() == attemptDTO.getFactorA() * attemptDTO.getFactorB();

        // Builds the domain object. Null Id for now.
        ChallengeAttempt checkedAttempt =
                new ChallengeAttempt(null,
                        user,
                        attemptDTO.getFactorA(),
                        attemptDTO.getFactorB(),
                        attemptDTO.getGuess(),
                        isCorrect);

        // Stores the attempt
        ChallengeAttempt storedAttempt = attemptRepository.save(checkedAttempt);

        return storedAttempt;
    }


    @Override
    public List<ChallengeAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findTop10ByUsersAliasOrderByIdDesc(userAlias);
    }
}
