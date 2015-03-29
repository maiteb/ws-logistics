package br.com.maiteb.ws.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan("br.com.maiteb.ws")
@PropertySource(value = "classpath:configuration.properties")
public class WsConfiguration {
	
	@Value("${db.folder}")
	private String dbFolder;

	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		return propertySourcesPlaceholderConfigurer;

	}

	@Bean
	public File dbFolder() {
		File folder = new File(System.getProperty("user.dir") + File.separator + dbFolder);
		if (!folder.exists()) {
			folder.mkdir();
		}
		return folder;
	}
	
}
