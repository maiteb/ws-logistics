package br.com.maiteb.ws.mvc;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan("br.com.maiteb.ws.web")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	
	public @Bean MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setSupportedMediaTypes(Collections
				.singletonList(MediaType.APPLICATION_JSON));
		return jsonConverter;
	}

	public @Bean StringHttpMessageConverter stringHttpMessageConverter() {
		final StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setSupportedMediaTypes(Arrays.asList(
				MediaType.TEXT_HTML, MediaType.TEXT_PLAIN));
		return stringConverter;
	}

	public @Bean RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		final RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		requestMappingHandlerAdapter.setMessageConverters(Arrays.asList(
				stringHttpMessageConverter(),
				mappingJackson2HttpMessageConverter()));
		return requestMappingHandlerAdapter;
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);

		registry.addInterceptor(new LoggingHandlerInterceptorAdapter())
		.addPathPatterns("/*");
	}
}
