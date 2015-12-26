package srai.model.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import srai.model.Thought;

import java.util.List;

/** Repository manager for Thoughts. */
@RepositoryRestResource(collectionResourceRel = "thoughts", path = "thoughts")
public interface ThoughtRepository extends PagingAndSortingRepository<Thought, Long> {

  /** Get collections of Thoughts by person. */
  List<Thought> findByPersonId(@Param("person_id") Integer personId);

}