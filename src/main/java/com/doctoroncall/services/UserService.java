package com.doctoroncall.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.doctoroncall.entities.Role;
import com.doctoroncall.entities.User;
import com.doctoroncall.repository.RoleRepository;
import com.doctoroncall.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;

	private BCryptPasswordEncoder encoder;

	@Autowired
	AuthenticationManager authenticationManager;

	public void createUser(User user) {
		Role role = roleRepository.findByName("PATIENT");
		createUser(user, role);
	}

	public void createUser(User user, Role userRole) {
		if (encoder == null)
			encoder = new BCryptPasswordEncoder();

		if (user.getPassword() == null)
			user.setPassword("admin@123");

		user.setPassword(encoder.encode(user.getPassword()));
		user.setStateId(User.STATE_ACTIVE);
		user.setRole(userRole);
		userRepository.save(user);
	}

	public void createAdmin(User user) {
		if (encoder == null)
			encoder = new BCryptPasswordEncoder();
		user.setStateId(User.STATE_ACTIVE);
		user.setPassword(encoder.encode(user.getPassword()));
		Role userRole = new Role("ADMIN");
		roleRepository.save(userRole);
		user.setRole(userRole);
		userRepository.save(user);
	}

	public void savePassword(User user, String password) {
		user.setPassword(encoder.encode(password));
		userRepository.save(user);
	}

	public User findOne(Long id) {
		return userRepository.findOne(id);
	}

	public boolean isUserPresent(String email) {
		User u = userRepository.findByEmail(email);
		if (u != null)
			return true;

		return false;
	}

	public Page<User> findAll(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}


	public Page<User> findByName(String name,Pageable pageable) {
		
		return userRepository.findByNameLike("%" + name + "%",pageable);
	}

	public long count() {
		
		return userRepository.count();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = findByEmail(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getName());
		UserDetails userDetails = (UserDetails) new org.springframework.security.core.userdetails.User(user.getEmail(),
				user.getPassword(), user.isActive(), true, true, true, Arrays.asList(authority));
		return userDetails;
	}

	public User findByEmail(String email) {

		return userRepository.findByEmail(email);
	}

	public User findByConfirmationToken(String confirmationToken) {
		return userRepository.findByConfirmationToken(confirmationToken);
	}

	public User save(User user) {
		return userRepository.save(user);

	}

	public User findAdmin() {
		return userRepository.findByRole(new Role("ADMIN"));
	}

	public Iterable<User> findByNameAndRole(String name, Role role) {
		return userRepository.findAllByNameLikeAndRole("%" + name + "%", role);
	}
	public Page<User> findByNameAndRoleAndStateId(String name, Role role, Integer stateId, Pageable pageable) {
		return userRepository.findAllByNameLikeAndRoleAndStateId("%" + name + "%", role, stateId,pageable);
	}
	public User findByNumber(String number) {
		return userRepository.findByContactNumber(number);
	}

	public boolean authenticate(String username, String password) {

		UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(username,
				password);
		/*Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
		if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
			//throw new InternalAuthenticationServiceException("Unable to authenticate  User for provided credentials");
			return false;
		}
*/
		//SecurityContextHolder.getContext().setAuthentication(responseAuthentication);
		return true;

	}

	public Boolean sendOTP(User user, String otp) {
		/*
		 * HttpResponse<String> response = Unirest.post(
		 * "http://control.msg91.com/api/sendotp.php?otp_length=6&authkey=192631AQfnt3G2fQ5a5719f2&message="
		 * + otp + "&sender=OTPSMS&mobile=+91" + user.getContactNumber() + "&otp=" +
		 * otp) .asString();
		 */
		return true;
	}

	public void deleteuser(long id) {
		// TODO Auto-generated method stub
		userRepository.delete(id);
	}
	
}
