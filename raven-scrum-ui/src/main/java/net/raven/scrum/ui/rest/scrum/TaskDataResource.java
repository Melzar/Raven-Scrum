package net.raven.scrum.ui.rest.scrum;

import java.util.LinkedList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import net.raven.scrum.core.annotations.logger.Log;
import net.raven.scrum.core.enumeration.scrum.TaskType;
import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.rest.dto.scrum.TaskDTO;
import net.raven.scrum.core.rest.dto.scrum.TaskTypeDTO;
import net.raven.scrum.ui.service.scrum.ScrumService;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/task")
public class TaskDataResource
{
	@Autowired
	private ScrumService scrumService;

	@Log
	private Logger log;

	public TaskDataResource()
	{

	}

	// TODO refactoring maybe like no idParent param, it is inside dto
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{idParent}/add")
	public Response addTask(@PathParam("idParent") Long idParent, TaskDTO dto)
	{
		try
		{
			dto = scrumService.addSubtaskQuick(idParent, dto);
			return Response.status(Status.OK).entity(dto).build();
		} catch (ScrumException e)
		{
			log.error("Failed to add subtask for parent " + idParent, e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/change/state")
	public Response changeTaskState(TaskDTO dto)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumService.changeTaskState(dto)).build();
		} catch (ScrumException e)
		{
			log.error("Failed to change task state " + dto.getId(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/change/type")
	public Response changeTaskType(TaskDTO dto)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumService.changeTaskType(dto)).build();
		} catch (ScrumException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/change/user")
	public Response changeTaskUser(TaskDTO dto)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumService.changeTaskUser(dto)).build();
		} catch (ScrumException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/change/parent")
	public Response changeParent(TaskDTO dto)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumService.changeTaskParent(dto)).build();
		} catch (ScrumException ex)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/delete")
	public Response deleteTask(TaskDTO dto)
	{
		try
		{
			dto = scrumService.deleteTask(dto);
			return Response.status(Status.OK).entity(dto).build();
		} catch (ScrumException e)
		{
			log.error("Failed to delete task " + dto.getId(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/makeparent")
	public Response makeSubtaskParent(TaskDTO dto)
	{
		try
		{
			dto = scrumService.makeSubtaskParentTask(dto);
			return Response.status(Status.OK).entity(dto).build();
		} catch (ScrumException e)
		{
			log.error("Failed to make subtask as parent task " + dto.getId(), e);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/scopeout")
	public Response scopeOutTask(TaskDTO dto)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumService.scopeOutTask(dto)).build();
		} catch (ScrumException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/type")
	public Response getTaskType()
	{
		List<TaskTypeDTO> types = new LinkedList<TaskTypeDTO>();
		for (TaskType type : TaskType.values())
		{
			TaskTypeDTO node = new TaskTypeDTO();
			node.setId(type.ordinal());
			node.setType(type);
			node.setTag(type.name());
			types.add(node);
		}
		return Response.status(Status.OK).entity(types).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/edit/description")
	public Response editTaskDescription(TaskDTO dto)
	{
		try
		{
			return Response.status(Status.OK)
					.entity(scrumService.editTaskDescription(dto)).build();
		} catch (ScrumException e)
		{
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}
	}
}
