package srai.model.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import srai.model.Thought;

@RepositoryRestResource(collectionResourceRel = "thoughts", path = "thoughts")
public interface ThoughtRepository extends PagingAndSortingRepository<Thought, Long> {

	List<Thought> findByPersonId(@Param("person_id") Integer person_id);

}