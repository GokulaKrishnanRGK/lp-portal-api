package com.sw.lp.config;

import java.util.Arrays;
import java.util.Collections;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  DataSource datasource;

  public SecurityConfig(DataSource datasource) {
    this.datasource = datasource;
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors(c -> c.configurationSource(request -> {
      CorsConfiguration cors = new CorsConfiguration();
      cors.setAllowedOriginPatterns(Collections.singletonList("*"));
      cors.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
      cors.setAllowedHeaders(Arrays.asList("Origin", "X-Requested-With", "Content-Type", "Accept", "Access-Control-Allow-Origin",
          "Access-Control-Allow-Methods", "Access-Control-Allow-Headers", "X-XSRF-Token"));
      cors.setAllowCredentials(true);
      return cors;
    }));
    http.authorizeHttpRequests(req -> req.requestMatchers("*").permitAll());
    http.csrf(AbstractHttpConfigurer::disable);
    return http.build();
  }

  @Bean
  public PlatformTransactionManager transactionManager() {
    DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
    transactionManager.setDataSource(datasource);
    return transactionManager;
  }

}
