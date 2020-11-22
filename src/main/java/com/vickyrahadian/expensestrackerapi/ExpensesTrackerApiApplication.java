package com.vickyrahadian.expensestrackerapi;

import com.vickyrahadian.expensestrackerapi.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.cors.CorsConfiguration;

@SpringBootApplication
public class ExpensesTrackerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpensesTrackerApiApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        source.registerCorsConfiguration("/**", config);
        registrationBean.setFilter(new CorsFilter(source));
        registrationBean.setOrder(0);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean() {
        FilterRegistrationBean<AuthFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        AuthFilter authFilter = new AuthFilter();
        filterRegistrationBean.setFilter(authFilter);
        filterRegistrationBean.addUrlPatterns("/api/categories/*");

        return filterRegistrationBean;
    }
}
