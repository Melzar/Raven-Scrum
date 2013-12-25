package net.raven.scrum.core.repository;

import java.util.Collection;

import net.raven.scrum.core.entity.ScrumUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScrumUserRepository extends JpaRepository<ScrumUser, Long>
{
	@Query("from ScrumUser u where u.login = :login")
	public ScrumUser getUserByLogin(@Param("login") String login);

	@Query("from ScrumUser u where u.email = :email")
	public ScrumUser getUserByEmail(@Param("email") String email);

	@Query("select case when (count(u) > 0)  then false else true end from ScrumUser u where u.email = :email")
	public boolean isEmailUnique(@Param("email") String email);

	@Query("select case when (count(u) > 0) then false else true end from ScrumUser u where u.login = :login")
	public boolean isLoginUnique(@Param("login") String login);

	@Query("from ScrumUser u left join fetch u.userprojectrole p where p.pk.project.id = :id and u.shadowFlag = 0")
	public Collection<ScrumUser> getUsersFromProject(@Param("id") Long id);

	@Query("from ScrumUser u where u.shadowFlag = 0")
	public Collection<ScrumUser> getActiveUsers();

	// @Query("update ScrumUser u set u.shadowFlag = :shadowFlag where u.login = :login")
	// public void setUserShadowflag(@Param("login") String login,
	// @Param("shadowFlag") ShadowFlag flag);
}
