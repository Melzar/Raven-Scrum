package net.raven.scrum.core.repository;

import net.raven.scrum.core.entity.ScrumBacklog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScrumBacklogRepository extends
		JpaRepository<ScrumBacklog, Long>
{

	@Query("from ScrumBacklog b left join fetch b.tasks t where b.project.idProject = :idProject")
	public ScrumBacklog getBacklogForProject(@Param("idProject") long idProject);
}
