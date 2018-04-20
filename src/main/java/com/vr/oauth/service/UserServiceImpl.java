package com.vr.oauth.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vr.oauth.annotation.LogExecutionTime;
import com.vr.oauth.model.User;
import com.vr.oauth.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public void create(User user) {
		Set<String> roles = new HashSet();
		roles.add("ROLE_USER");
		user.setRoles(roles);
		user.setCreatedAt(new Date());
		user.setActive(true);
		userRepository.save(user);
	}

	@Override
	@LogExecutionTime
	public User getById(String id) {
		return userRepository.findOneById(id);
	}
}
