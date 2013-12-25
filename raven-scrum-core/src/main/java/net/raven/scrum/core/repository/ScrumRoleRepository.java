package net.raven.scrum.core.repository;

import net.raven.scrum.core.entity.ScrumRole;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScrumRoleRepository extends JpaRepository<ScrumRole, Long>
{
	@Query("from ScrumRole r where r.roleName = :name")
	public ScrumRole getRoleByName(@Param("name") String name);
}
