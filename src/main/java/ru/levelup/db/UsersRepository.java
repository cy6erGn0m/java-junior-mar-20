package ru.levelup.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.levelup.model.User;
import ru.levelup.model.UserStatus;

import java.util.List;

@Repository
public interface UsersRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);

    List<User> findByStatusOrderByLogin(UserStatus status);

    List<User> findByBonusScoreGreaterThan(int bonusScore);

    @Query("select u from User u where u.bonusScore > :bonusScore")
    List<User> findUsersScoreTooBig(int bonusScore);
}
