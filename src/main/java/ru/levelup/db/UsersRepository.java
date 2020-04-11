package ru.levelup.db;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.levelup.model.User;
import ru.levelup.model.UserStatus;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "users-api",
        itemResourceRel = "users-api",
        path = "users-api")
public interface UsersRepository extends CrudRepository<User, Integer> {
    User findByLogin(@Param("loginName") String login);
    User findByLoginAndPassword(String login, String password);

    List<User> findByStatusOrderByLogin(UserStatus status);

    List<User> findByBonusScoreGreaterThan(int bonusScore);

    @Query("select u from User u where u.bonusScore > :bonusScore")
    List<User> findUsersScoreTooBig(int bonusScore);
}
