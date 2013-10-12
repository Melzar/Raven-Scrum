package net.raven.scrum.core.repository;

import net.raven.scrum.core.entity.ScrumProject;

import org.springframework.data.repository.CrudRepository;

public interface ScrumProjectRepository extends
		CrudRepository<ScrumProject, Long>
{

	public ScrumProject getProjectData();
}
