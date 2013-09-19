package net.raven.scrum.core.repository;

import net.raven.scrum.core.entity.ScrumUser;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScrumUserRepository extends CrudRepository<ScrumUser, Long>
{
	@Query("from ScrumUser u where u.login = :login")
	public ScrumUser getUserByLogin(@Param("login") String login);

	@Query("from ScrumUser u where u.email = :email")
	public ScrumUser getUserByEmail(@Param("email") String email);

	@Query("from ScrumUser u where u.login = :login and u.password = :password")
	public boolean checkCredentials(@Param("login") String login,
			@Param("password") String password);
}
