package com.ssafy.happyhouse.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.happyhouse.interceptor.JwtInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

	private static final String[] SWAGGER_PATHS = { "/swagger-ui/**", "/swagger-resources/**", "/v2/api-docs" };
	private static final String[] RESOURCE_PATHS = { "/img/happyhouse.png" };
	private static final String[] URL_PATHS = { "/userinfo/login", "/map/**", "/notice/**", "/error/**" };
	private static final List<String> EXCLUDE_PATHS = new ArrayList<String>();

	private JwtInterceptor jwtInterceptor;

	public WebMvcConfiguration(JwtInterceptor jwtInterceptor) {
		this.jwtInterceptor = jwtInterceptor;

		EXCLUDE_PATHS.addAll(Arrays.asList(SWAGGER_PATHS));
		EXCLUDE_PATHS.addAll(Arrays.asList(RESOURCE_PATHS));
		EXCLUDE_PATHS.addAll(Arrays.asList(URL_PATHS));
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// jwt 인터셉터 등록
//		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**").excludePathPatterns(EXCLUDE_PATHS);
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*")
//		.allowedOrigins("http://localhost:8080")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS").maxAge(6000);
	}

//	Swagger UI 실행시 404처리
//	@Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/swagger-ui/index.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui/index.html");
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }
}
