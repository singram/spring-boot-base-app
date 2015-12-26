package srai.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import srai.model.Person;

import java.util.List;

/** Repository manager for Person. */
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

  /** Get collections of Person by name. */
  List<Person> findByLastName(@Param("name") String name);

}