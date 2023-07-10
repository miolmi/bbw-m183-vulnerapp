package ch.bbw.m183.vulnerapp;

import ch.bbw.m183.vulnerapp.controller.AdminController;
import ch.bbw.m183.vulnerapp.datamodel.UserEntity;
import ch.bbw.m183.vulnerapp.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest
class VulnerApplicationTests {
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


	public class AdminControllerTest {

		private AdminController adminController;

		@Mock
		private AdminService adminService;

		@BeforeEach
		public void setup() {
			MockitoAnnotations.openMocks(this);
			adminController = new AdminController(adminService);
		}

		@Test
		public void testCreateUser() {
			UserEntity newUser = new UserEntity("john", "password");
			UserEntity createdUser = new UserEntity("john", "password");

			when(adminService.createUser(newUser)).thenReturn(createdUser);

			ResponseEntity<UserEntity> response = adminController.createUser(newUser);

			assertEquals(HttpStatus.OK, response.getStatusCode());
			assertEquals(createdUser, response.getBody());
			verify(adminService, times(1)).createUser(newUser);
		}

		@Test
		public void testGetUsers() {
			List<UserEntity> userList = new ArrayList<>();
			userList.add(new UserEntity("john", "password"));
			userList.add(new UserEntity("jane", "password"));
			Page<UserEntity> usersPage = new PageImpl<>(userList);

			Pageable pageable = Pageable.ofSize(10).withPage(0);

			when(adminService.getUsers(pageable)).thenReturn(usersPage);

			ResponseEntity<Page<UserEntity>> response = adminController.getUsers(pageable);

			assertEquals(HttpStatus.OK, response.getStatusCode());
			assertEquals(usersPage, response.getBody());
			verify(adminService, times(1)).getUsers(pageable);
		}

		@Test
		public void testDeleteUser() {
			String username = "john";

			adminController.deleteUser(username);

			verify(adminService, times(1)).deleteUser(username);
		}
	}

}
