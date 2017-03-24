package in.nks.nit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nks.nit.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>
{

	User findByName(String name);

}
