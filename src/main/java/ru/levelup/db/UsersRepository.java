package ru.levelup.db;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
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
public interface UsersRepository extends PagingAndSortingRepository<User, Integer> {
    User findByLogin(@Param("loginName") String login);

    Page<User> findByStatusOrderByLogin(UserStatus status, Pageable page);

    Page<User> findByBonusScoreGreaterThan(int bonusScore, Pageable page);

    @Query("select u from User u where u.bonusScore > :bonusScore")
    List<User> findUsersScoreTooBig(@Param("bonusScore") int bonusScore);
}
