package net.raven.scrum.core.repository;

import java.util.Collection;

import net.raven.scrum.core.entity.ScrumProject;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScrumProjectRepository extends
		CrudRepository<ScrumProject, Long>
{
	@Query("from ScrumProject p left join fetch p.projectUsers pu where pu.login = :login ")
	public Collection<ScrumProject> getProjectListForUser(
			@Param("login") String login);

}
