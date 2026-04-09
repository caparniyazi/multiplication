package microservices.book.multiplication.challenge;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChallengeAttemptRepository extends CrudRepository<ChallengeAttempt, Long> {
    /**
     *
     * @param usersAlias
     * @return The last 10 attempts for a given user, identified by their alias.
     */
    List<ChallengeAttempt> findTop10ByUsersAliasOrderByIdDesc(String usersAlias);

    @Query("SELECT a FROM ChallengeAttempt a WHERE a.users.alias= :usersAlias ORDER BY a.id DESC")
    List<ChallengeAttempt> lastAttempts(@Param("usersAlias") String usersAlias);
}
