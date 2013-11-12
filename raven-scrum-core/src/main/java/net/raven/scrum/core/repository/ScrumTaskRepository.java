package net.raven.scrum.core.repository;

import net.raven.scrum.core.entity.ScrumSprint;
import net.raven.scrum.core.entity.ScrumTask;
import net.raven.scrum.core.enumeration.scrum.TaskState;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ScrumTaskRepository extends JpaRepository<ScrumTask, Long>
{
	@Query("from ScrumTask t left join fetch t.subtasks ss where t.id = :idParent")
	public ScrumTask getTaskWithSubtasks(@Param("idParent") Long idParent);

	@Modifying
	@Transactional
	@Query("update ScrumTask t set t.parent.idTask = :idParent where t.idTask = :idSubtask")
	public int setParentTaskForSubtask(@Param("idParent") Long idParent,
			@Param("idSubtask") Long idSubtask);

	@Modifying
	@Transactional
	@Query("update ScrumTask t set t.state = :state where t.idTask = :idSubtask")
	public int setTaskState(@Param("state") TaskState state,
			@Param("idSubtask") Long idSubtask);

	@Modifying
	@Transactional
	@Query("update ScrumTask t set t.parent.idTask = null, t.title = :title, t.description = :description, t.assigned.idUser = :idUser where t.idTask = :idSubtask")
	public int makeSubtaskParentTask(@Param("idSubtask") Long idSubtask,
			@Param("title") String title,
			@Param("description") String description,
			@Param("idUser") Long idUser);

	@Query("Select distinct ss from ScrumSprint ss left join ss.tasks st left join fetch ss.project p where ss.status = 0 and st.idTask = :idTask")
	public ScrumSprint getTaskActiveSprint(@Param("idTask") Long idTask);

}
