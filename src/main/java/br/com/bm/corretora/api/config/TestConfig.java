package br.com.bm.corretora.api.config;

import br.com.bm.corretora.api.service.impl.DBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class TestConfig {

	private final DBServiceImpl dbService;

	@Autowired
	public TestConfig(DBServiceImpl dbService) {
		this.dbService = dbService;
	}

	@Bean
	public void instanciaDB() {
		this.dbService.instanciaDB();
	}
}
