package br.com.maiteb.ws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.maiteb.ws.service.RouteService;
import br.com.maiteb.ws.web.vo.RouteVO;

/**
 * {@link RestController} that receives a route solicitation
 * @author Maitê Balhester
 *
 */
@RestController
public class RoutesController {

	@Autowired
	private RouteService service;

	/**
	 * Get a route
	 * @param network name of logistics network
	 * @param source initial node
	 * @param destination end node
	 * @param autonomy vehicle autonomy
	 * @param costPerLiter cost per gas liter
	 * @return {@link RouteVO} with path and cost
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/route")
	public @ResponseBody RouteVO getARoute(@RequestParam("net") String network,
			@RequestParam("source") String source,
			@RequestParam("destination") String destination,
			@RequestParam("autonomy") int autonomy,
			@RequestParam("costPerLiter") double costPerLiter) {
				return service.calculateRoute(network, source, destination, autonomy, costPerLiter);

	}

}
