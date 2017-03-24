package in.nks.nit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nks.nit.entity.Blog;
import in.nks.nit.entity.User;

public interface BlogRepository extends JpaRepository<Blog, Integer>
{
	List<Blog> findByUser(User user);
}
