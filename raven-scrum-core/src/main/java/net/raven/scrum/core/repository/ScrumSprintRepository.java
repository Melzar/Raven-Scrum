package net.raven.scrum.core.repository;

import net.raven.scrum.core.entity.ScrumSprint;
import net.raven.scrum.core.enumeration.scrum.SprintStatus;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScrumSprintRepository extends
		CrudRepository<ScrumSprint, Long>
{

	@Query("Select distinct ss from ScrumSprint ss left join fetch ss.tasks t left join fetch ss.project p where p.idProject = :idProject and ss.status = :status")
	public ScrumSprint getSprintData(@Param("idProject") Long idProject,
			@Param("status") SprintStatus status);

}
