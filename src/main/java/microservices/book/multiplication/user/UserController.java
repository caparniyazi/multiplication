package microservices.book.multiplication.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UsersRepository usersRepository;

    /**
     * Retrieve a collection of user aliases based on their identifiers.
     * LeaderBoardController class returns the score, badges, and position based on user IDs.
     * The API call can include one or more numbers separated by commas, such as /users/1,2,3.
     *
     * @param idList
     * @return
     */
    @GetMapping("/{idList}")
    public List<Users> getUsersByIdList(@PathVariable final List<Long> idList) {
        return usersRepository.findAllByIdIn(idList);
    }
}
