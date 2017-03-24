package in.nks.nit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.nks.nit.entity.Blog;
import in.nks.nit.entity.Item;
import in.nks.nit.entity.Role;
import in.nks.nit.entity.User;
import in.nks.nit.repository.BlogRepository;
import in.nks.nit.repository.ItemRepository;
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
	
	@Autowired
	private ItemRepository itemRepository;
	
	@PostConstruct
	public void init()
	{
		//if(roleRepository.findByName("ROLE_ADMIN")==null){
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
		
		/*
		Item item1=new Item();
		item1.setBlog(blogJavavids);
		item1.setTitle("first");
		item1.setLink("http://www.javavids.com/");
		item1.setPublishedDate(new Date());
		itemRepository.save(item1);
		
		Item item2=new Item();
		item2.setBlog(blogJavavids);
		item2.setTitle("second");
		item2.setLink("http://www.javavids.com/");
		item2.setPublishedDate(new Date());
		itemRepository.save(item2);
		*/
		//}
	}
}
