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


/**
 * {@link WebMvcConfigurerAdapter} for ws-logistics
 * @author Maitê Balhester
 *
 */
@Configuration
@EnableWebMvc
@ComponentScan("br.com.maiteb.ws.web")
public class MvcConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * Generate a Bean of {@link MappingJackson2HttpMessageConverter}
	 * @return {@link MappingJackson2HttpMessageConverter}
	 */
	public @Bean MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setSupportedMediaTypes(Collections
				.singletonList(MediaType.APPLICATION_JSON));
		return jsonConverter;
	}

	/**
	 * Generate a Bean of {@link StringHttpMessageConverter}
	 * @return {@link StringHttpMessageConverter}
	 */
	public @Bean StringHttpMessageConverter stringHttpMessageConverter() {
		final StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
		stringConverter.setSupportedMediaTypes(Arrays.asList(
				MediaType.TEXT_HTML, MediaType.TEXT_PLAIN));
		return stringConverter;
	}

	/**
	 * Generate a Bean of {@link RequestMappingHandlerAdapter}, mapping all converters
	 * @return {@link RequestMappingHandlerAdapter}
	 */
	public @Bean RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		final RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		requestMappingHandlerAdapter.setMessageConverters(Arrays.asList(
				stringHttpMessageConverter(),
				mappingJackson2HttpMessageConverter()));
		return requestMappingHandlerAdapter;
	}


}
