package ch.bbw.m183.vulnerapp.service;

import ch.bbw.m183.vulnerapp.datamodel.UserEntity;
import ch.bbw.m183.vulnerapp.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class LoginService {

	private final UserRepository userRepository;
	private final EntityManager entityManager;

	public ResponseEntity<UserEntity> whoami(String username) throws Exception {
		if (userRepository.findById(username).isPresent()) {
			UserEntity user = userRepository.findById(username).get();
			return ResponseEntity.status(200).body(user);
		}
/*		return userRepository.findById(username)
				.orElse(new UserEntity().setUsername("idk"));*/
		throw new Exception("Login Error");
	}
}
