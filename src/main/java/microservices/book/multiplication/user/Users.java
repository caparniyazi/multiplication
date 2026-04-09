package microservices.book.multiplication.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

/**
 * Stores information to identify the user.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    // Data fields
    @Id
    @GeneratedValue
    private Long id;
    private String alias;

    public Users(final String userAlias) {
        this(null, userAlias);
    }
}
