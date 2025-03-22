package com.att.tdp.popcorn_palace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EntityScan(basePackages = "com.att.tdp.popcorn_palace.model")
@EnableJpaRepositories(basePackages = "com.att.tdp.popcorn_palace.repository")
public class PopcornPalaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopcornPalaceApplication.class, args);
	}

	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(ApplicationContext applicationContext) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPackagesToScan("com.att.tdp.popcorn_palace.model");
        return factoryBean;
    }

}
