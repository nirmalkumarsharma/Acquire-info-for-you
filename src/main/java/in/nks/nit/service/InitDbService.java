package in.nks.nit.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.nks.nit.entity.Blog;
import in.nks.nit.entity.Role;
import in.nks.nit.entity.User;
import in.nks.nit.repository.BlogRepository;
import in.nks.nit.repository.RoleRepository;
import in.nks.nit.repository.UserRepository;

@Transactional
@Service
public class InitDbService {
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BlogRepository blogRepository;
	
	@PostConstruct
	public void init()
	{
		if(roleRepository.findByName("ROLE_ADMIN")==null)
		{
			Role roleUser=new Role();
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);
			Role roleAdmin=new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);
		
			User userAdmin=new User();
			userAdmin.setEnabled(true);
			userAdmin.setName("admin");
			BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
			userAdmin.setPassword(encoder.encode("admin"));
			List<Role> roles=new ArrayList<Role>();
			roles.add(roleUser);
			roles.add(roleAdmin);
			userAdmin.setRoles(roles);
			userRepository.save(userAdmin);
		
			Blog blogJavavids=new Blog();
			blogJavavids.setName("Open Source Updates");
			blogJavavids.setUrl("https://opensource.com/feed");
			blogJavavids.setUser(userAdmin);
			blogRepository.save(blogJavavids);
		}
	}
}
