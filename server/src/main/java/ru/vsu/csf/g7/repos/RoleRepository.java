package ru.vsu.csf.g7.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.csf.g7.entity.Role;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Integer>,
        CrudRepository<Role, Integer> {
}
