package net.raven.scrum.core.repository;

import java.util.Collection;

import net.raven.scrum.core.entity.ScrumProject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScrumProjectRepository extends
		JpaRepository<ScrumProject, Long>
{
	@Query("from ScrumProject p join fetch p.userprojectrole pu where pu.pk.user.login = :login ")
	public Collection<ScrumProject> getProjectListForUser(
			@Param("login") String login);

	@Query("select case when (count (p) > 0) then false else true end from ScrumProject p where p.title = :title")
	public boolean isProjectTitleUnique(@Param("title") String title);

	@Query("from ScrumProject p left outer join fetch p.userprojectrole where p.idProject = :idProject")
	public ScrumProject getProjectWithUsers(@Param("idProject") long idProject);

}
