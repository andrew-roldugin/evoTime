package ru.vsu.csf.g7.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.vsu.csf.g7.entity.FlexValue;

@Repository
public interface FlexValueRepository extends CrudRepository<FlexValue, Long>,
        PagingAndSortingRepository<FlexValue, Long> {

}
