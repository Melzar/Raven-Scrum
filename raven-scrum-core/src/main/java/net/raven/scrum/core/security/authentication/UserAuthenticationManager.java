package net.raven.scrum.core.security.authentication;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.raven.scrum.core.annotations.logger.Log;
import net.raven.scrum.core.entity.ScrumUser;
import net.raven.scrum.core.repository.ScrumUserRepository;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

@Service("UserAuthenticationManager")
public class UserAuthenticationManager implements AuthenticationProvider
{
	@Log
	private Logger logger;

	@Autowired
	private Md5PasswordEncoder passwordEncoder;

	@Autowired
	private ScrumUserRepository userRepository;

	public UserAuthenticationManager()
	{
	}

	@Override
	public boolean supports(Class<?> authentication)
	{
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException
	{
		ScrumUser user = userRepository
				.getUserByLogin(authentication.getName());
		if (passwordEncoder.isPasswordValid(user.getPassword(), authentication
				.getCredentials().toString(), null))
		{
			return new UsernamePasswordAuthenticationToken(
					authentication.getPrincipal(),
					authentication.getCredentials(), getUserRoles("ROLE_ADMIN"));
		} else
		{
			throw new BadCredentialsException("Invalid credentials");
		}
	}

	private Collection<GrantedAuthority> getUserRoles(String role)
	{
		// TODO USER ROLES implementation
		List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
		roleList.add(new SimpleGrantedAuthority(role));
		return roleList;
	}
}
