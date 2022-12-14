package com.ssafy.finalPjt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.finalPjt.interceptor.JwtInterceptor;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui/**")
				.addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
		registry.addResourceHandler("/static/images/**")
				.addResourceLocations("classpath:/static/images/");
		registry.addResourceHandler("/images/**")
				.addResourceLocations("file:///C:/finalUpload/images/");
		registry.addResourceHandler("/videos/**")
				.addResourceLocations("file:///C:/finalUpload/videos/");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("GET", "POST", "PUT", "DELETE");
	}

	@Autowired
	private JwtInterceptor jwtInterceptor;

	// @Override
	// public void addInterceptors(InterceptorRegistry registry) {
	// registry.addInterceptor(jwtInterceptor)
	//// .addPathPatterns("/**")
	// .excludePathPatterns("/api/userApi/login",
	// "/api/userApi/regist",
	// "/swagger-resources/**",
	// "/swagger-ui/**",
	// "/v2/api-docs");
	// }

}
