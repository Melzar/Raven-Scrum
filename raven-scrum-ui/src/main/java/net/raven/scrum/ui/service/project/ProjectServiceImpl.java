package net.raven.scrum.ui.service.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.raven.scrum.core.entity.ScrumProject;
import net.raven.scrum.core.entity.ScrumRole;
import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.entity.ScrumUserProjectRole;
import net.raven.scrum.core.enumeration.scrum.ProjectRole;
import net.raven.scrum.core.exception.ScrumException;
import net.raven.scrum.core.repository.ScrumProjectRepository;
import net.raven.scrum.core.repository.ScrumRoleRepository;
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

	@Autowired
	private ScrumRoleRepository roleRepository;

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
			dto.setRole(ProjectRole.valueOf(user.getUserprojectrole()
					.iterator().next().getPk().getRole().getRoleName()));
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
			dto.setTitle(sp.getTitle());
			dto.setDescription(sp.getDescription());
			list.add(dto);
		}
		return list;
	}

	@Override
	public ProjectDTO crateProject(ProjectDTO dto) throws ScrumException
	{
		ScrumProject project = new ScrumProject();
		project.setTitle(dto.getTitle());
		project.setDescription(dto.getDescription());
		project.setStatus(dto.getStatus());
		project = projectRepository.save(project);
		if (!dto.getProjectUsers().isEmpty())
		{
			Set<ScrumUserProjectRole> uprl = new HashSet<>();
			for (ScrumUserDTO u : dto.getProjectUsers())
			{
				ScrumUserProjectRole upr = new ScrumUserProjectRole();
				upr.getPk().setProject(project);
				ScrumUser user = new ScrumUser();
				user.setIdUser(u.getId());
				ScrumRole role = roleRepository.getRoleByName(u.getRole()
						.name());
				upr.getPk().setUser(user);
				upr.getPk().setRole(role);
				uprl.add(upr);
			}
			project.setUserProjectRole(uprl);
			projectRepository.save(project);
		}
		dto.setIdProject(project.getIdProject());
		return dto;
	}
}
