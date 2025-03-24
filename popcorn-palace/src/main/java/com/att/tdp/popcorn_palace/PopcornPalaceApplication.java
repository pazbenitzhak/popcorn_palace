package com.att.tdp.popcorn_palace;

// import java.util.HashMap;
// import java.util.Map;

// import javax.sql.DataSource;

// import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
// import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
// import org.springframework.context.ApplicationContext;

// import jakarta.annotation.PostConstruct;

@SpringBootApplication
// @EnableJpaRepositories(basePackages = "com.att.tdp.popcorn_palace.repository")
@EntityScan(basePackages = {"com.att.tdp.popcorn_palace.model"})
// @ComponentScan(basePackages = { "com.att.tdp.popcorn_palace.*" })
public class PopcornPalaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PopcornPalaceApplication.class, args);
	}

	// @Bean
    // public LocalContainerEntityManagerFactoryBean entityManagerFactoryA(ApplicationContext applicationContext) {
    //     LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
    //     factoryBean.setPackagesToScan("com.att.tdp.popcorn_palace.model");
    //     factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    //     Map<String, Object> jpaProperties = new HashMap<>();
    //     jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
    //     factoryBean.setJpaPropertyMap(jpaProperties);
    //     return factoryBean;
    // }

	// @Bean
    // public LocalContainerEntityManagerFactoryBean entityManagerFactory(
    //         DataSource dataSource) {
    //     LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    //     emf.setDataSource(dataSource);
    //     emf.setPackagesToScan("com.att.tdp.popcorn_palace.model");
    //     // Map<String, Object> jpaProperties = new HashMap<>();
    //     // jpaProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

    //     //** Set the JPA provider explicitly (Hibernate)
    //     emf.setPersistenceProviderClass(HibernatePersistenceProvider.class);
    //     // emf.setJpaPropertyMap(jpaProperties);
    //     return emf;
    // }

}
