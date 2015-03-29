package br.com.maiteb.ws.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoggingHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

	private static final Logger LOGGER = Logger
			.getLogger(LoggingHandlerInterceptorAdapter.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		super.preHandle(request, response, handler);

		LOGGER.debug("Requisição recebida: " + request);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);

		LOGGER.debug("Resposta a ser enviada: " + response);
	}

}
