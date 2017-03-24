package in.nks.nit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nks.nit.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
	Role findByName(String name);
}
