package ru.vsu.csf.g7.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.csf.g7.entity.FlexValuesSet;
import ru.vsu.csf.g7.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer>,
        PagingAndSortingRepository<User, Integer> {

    List<User> getUsersBy();

    Optional<User> getUserByLogin(String login);

    boolean existsUserByLogin(String login);

    Optional<User> findUserByLoginOrEmail(String login, String email);

    Optional<User> findUserByLogin(String login);
}
