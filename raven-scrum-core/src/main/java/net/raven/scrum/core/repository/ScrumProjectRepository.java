package net.raven.scrum.core.repository;

import java.util.Collection;

import net.raven.scrum.core.entity.ScrumProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScrumProjectRepository extends
		JpaRepository<ScrumProject, Long>
{
	@Query("from ScrumProject p left join fetch p.userprojectrole pu where pu.pk.user.login = :login ")
	public Collection<ScrumProject> getProjectListForUser(
			@Param("login") String login);

}
