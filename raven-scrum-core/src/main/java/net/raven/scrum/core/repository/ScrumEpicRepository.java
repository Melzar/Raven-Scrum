package net.raven.scrum.core.repository;

import java.util.Collection;

import net.raven.scrum.core.entity.ScrumEpic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ScrumEpicRepository extends JpaRepository<ScrumEpic, Long>
{

	@Query("from ScrumEpic e left join fetch e.color where e.project.idProject = :idProject")
	public Collection<ScrumEpic> getProjectEpics(
			@Param("idProject") long idProject);

	@Modifying
	@Transactional
	@Query("update ScrumEpic e set e.color.idColor = :idColor where e.idEpic = :idEpic")
	public int updateEpicColor(@Param("idEpic") long idEpic,
			@Param("idColor") long idColor);

	@Modifying
	@Transactional
	@Query("update ScrumEpic e set e.epicName = :name where e.idEpic = :idEpic")
	public int updateEpicName(@Param("name") String name,
			@Param("idEpic") long idEpic);
}
