package com.vr.oauth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vr.oauth.model.User;
import com.vr.oauth.service.UserService;
import com.vr.oauth.util.APIEndpoints;
import com.vr.oauth.util.AppConstants;
import com.vr.oauth.util.SecurityUtil;

@RestController
@Api(value = "users", description = "User APIs")
@RequestMapping(value = APIEndpoints.USER_API_URL)
public class UserController extends BaseController {

	private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@ApiOperation(value = "Create an user.", notes = "API to create new user.",
			response = User.class)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> create(@RequestBody User user) {
		LOG.info("Saving new user with email {}.", user.getEmail());
		userService.create(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@ApiOperation(value = "Fetch user by id.", notes = "API to fetch user by id.",
			response = User.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getById(@PathVariable String id,
			@RequestParam(value = AppConstants.FIELDS, required = false) String fields) {
		LOG.info("Fetch user by id {}", id);
		String email = SecurityUtil.loggedInUserEmail();
		LOG.info("email : "+email);
		return new ResponseEntity<>(limitDataFields(userService.getById(id), User.class, fields),
				HttpStatus.MULTI_STATUS.OK);
	}
}
