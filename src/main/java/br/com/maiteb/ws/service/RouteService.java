package br.com.maiteb.ws.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.maiteb.ws.web.vo.RouteVO;

@Component
public class RouteService {

	@Autowired
	private LogisticsNetworkService logisticsNetworkService;

	public RouteVO calculateRoute(String network, String source,
			String destination, String autonomy) {
				return null;

	}

}
