package com.testplusquerysql.querysqlintest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QuerysqlintestApplicationTests {

	@Autowired
	private TextService textService;

	@Test
	void contextLoads() {
		textService.someMethod();
	}

}
