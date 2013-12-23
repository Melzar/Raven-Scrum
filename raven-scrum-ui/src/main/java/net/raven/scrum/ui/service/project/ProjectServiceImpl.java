package net.raven.scrum.ui.service.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.raven.scrum.core.entity.ScrumProject;
import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.repository.ScrumProjectRepository;
import net.raven.scrum.core.repository.ScrumUserRepository;
import net.raven.scrum.core.rest.dto.scrum.ProjectDTO;
import net.raven.scrum.core.rest.dto.user.ScrumUserDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService
{

	@Autowired
	private ScrumProjectRepository projectRepository;

	@Autowired
	private ScrumUserRepository userRepository;

	public ProjectServiceImpl()
	{

	}

	@Override
	public Collection<ScrumUserDTO> getProjectUsers(Long idProject)
			throws ScrumException
	{
		Collection<ScrumUser> userlist = userRepository
				.getUsersFromProject(idProject);
		List<ScrumUserDTO> dtos = new ArrayList<ScrumUserDTO>();
		for (ScrumUser user : userlist)
		{
			ScrumUserDTO dto = new ScrumUserDTO();
			dto.setId(user.getIdUser());
			dto.setName(user.getName());
			dto.setSurname(user.getSurname());
			dto.setLogin(user.getLogin());
			dto.setTag(user.getName() + user.getSurname());
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	public List<ProjectDTO> getProjectList() throws ScrumException
	{
		return createProjectListDTO(projectRepository.findAll());
	}

	@Override
	public List<ProjectDTO> getProjectListForUser(String login)
			throws ScrumException
	{
		return createProjectListDTO(projectRepository
				.getProjectListForUser(login));
	}

	private List<ProjectDTO> createProjectListDTO(
			Iterable<ScrumProject> iterable)
	{
		List<ProjectDTO> list = new ArrayList<ProjectDTO>();
		for (ScrumProject sp : iterable)
		{
			ProjectDTO dto = new ProjectDTO();
			dto.setIdProject(sp.getIdProject());
			dto.setIdManager(sp.getManager().getIdUser());
			dto.setTitle(sp.getTitle());
			dto.setDescription(sp.getDescription());
			list.add(dto);
		}
		return list;
	}

}
