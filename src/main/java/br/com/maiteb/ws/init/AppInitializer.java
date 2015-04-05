package br.com.maiteb.ws.init;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import br.com.maiteb.ws.config.WsConfiguration;
import br.com.maiteb.ws.mvc.MvcConfiguration;

import com.thetransactioncompany.cors.CORSFilter;

/**
 * ws-logistics Initializer
 * @author Maitê Balhester
 *
 */
@Order(1)
public class AppInitializer  extends
AbstractAnnotationConfigDispatcherServletInitializer  {
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		FilterRegistration.Dynamic corsFilter = servletContext.addFilter(
				"corsFilter", CORSFilter.class);
		corsFilter.addMappingForUrlPatterns(null, false, "/*");

		super.onStartup(servletContext);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { WsConfiguration.class };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcConfiguration.class };
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
