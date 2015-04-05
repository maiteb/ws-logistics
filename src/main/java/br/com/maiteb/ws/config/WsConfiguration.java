package br.com.maiteb.ws.config;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * ws-logistics Spring Configuration
 * @author Maitê Balhester
 *
 */
@Configuration
@ComponentScan(basePackages = {"br.com.maiteb.ws.service", "br.com.maiteb.ws.repository"})
@PropertySource(value = "classpath:configuration.properties")
public class WsConfiguration {
	
	@Value("${db.folder}")
	private String dbFolder;

	/**
	 * Generate a Bean of {@link PropertySourcesPlaceholderConfigurer}
	 * @return {@link PropertySourcesPlaceholderConfigurer}
	 */
	@Bean
	public static PropertySourcesPlaceholderConfigurer placeHolderConfigurer() {
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
		return propertySourcesPlaceholderConfigurer;

	}

	/**
	 * Generate a Bean that represents the database folder
	 * @return {@link File}
	 */
	@Bean
	public File dbFolder() {
		File folder = new File(System.getProperty("user.dir") + File.separator + dbFolder);
		if (!folder.exists()) {
			folder.mkdir();
		}
		return folder;
	}
	
}
