package br.com.bm.corretora.api.config;

import br.com.bm.corretora.api.service.impl.DBServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("dev")
@Configuration
public class DevConfig {

	private final DBServiceImpl dbService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String value;

	@Autowired
	public DevConfig(DBServiceImpl dbService) {
		this.dbService = dbService;
	}

	@Bean
	public boolean instanciaDB() {
		if (value.equals("create")) {
			this.dbService.instanciaDB();
		}

		return false;
	}
}
