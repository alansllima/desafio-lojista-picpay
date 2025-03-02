package com.picpay;

import com.picpay.domain.transaction.TransactionDTO;
import com.picpay.domain.user.UserDTO;
import com.picpay.domain.user.UserType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SpringBootApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	private static List<UserDTO> users;
	private static TransactionDTO transaction;

	@BeforeAll
	public static void setup(){
		users = new ArrayList<>();
		users.add(new UserDTO("Lara","Portinari", "12345657","lara.portinari@gmail.com","abcde1234", new BigDecimal(10000), UserType.COMMON));
		users.add(new UserDTO("Bella","Portinari", "123456578","Bella.portinari@gmail.com","1234abcde", new BigDecimal(10000), UserType.COMMON));

		transaction = new TransactionDTO(new BigDecimal(5000),1L,2L);
	}



	@Test
	@DisplayName("teste geral")
	void testeGeral(){
		postingUsers();
		getUsers();
		createNewTransaction();
		getTransactions();
		getUsers();

	}

	@DisplayName("Check transaction values")
	void postingUsers() {
		webTestClient.post()
				.uri("http://localhost:8080/api/user/register")
				.bodyValue(users.getFirst())
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("$.firstName").isEqualTo(users.getFirst().firstName());



		webTestClient.post()
				.uri("http://localhost:8080/api/user/register")
				.bodyValue(users.getLast())
				.exchange()
				.expectStatus().isOk()
				.expectBody().jsonPath("$.firstName").isEqualTo(users.getLast().firstName());

	}



	@DisplayName("get Users")
	void getUsers() {
		String responseBody = webTestClient.get()
				.uri("http://localhost:8080/api/user/users")
				.exchange()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();


		System.out.println(responseBody);


		//.expectBody().jsonPath("$.firstName").isEqualTo("Lara")

	}



	@DisplayName("create new Transaction")
	void createNewTransaction() {
		webTestClient.post()
				.uri("http://localhost:8080/api/transaction")
				.bodyValue(transaction)
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.sender").isEqualTo(1L)
				.jsonPath("$.receiver").isEqualTo(2L);

	}

	@DisplayName("get transactions")
	void getTransactions() {
		String responseBody =	webTestClient.get()
				.uri("http://localhost:8080/api/transaction/transactions")
				.exchange()
				.expectBody(String.class)
				.returnResult()
				.getResponseBody();


		System.out.println(responseBody);


		webTestClient.get()
				.uri("http://localhost:8080/api/transaction/transactions")
				.exchange()
				.expectBody()
				.jsonPath("$[0].sender").isEqualTo(1L)
				.jsonPath("$[0].receiver").isEqualTo(2L);
	}

}
