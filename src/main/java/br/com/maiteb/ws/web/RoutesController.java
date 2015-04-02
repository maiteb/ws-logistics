package br.com.maiteb.ws.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.maiteb.ws.service.RouteService;
import br.com.maiteb.ws.web.vo.RouteVO;

@RestController
public class RoutesController {

	@Autowired
	private RouteService service;

	@RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/route")
	public @ResponseBody RouteVO getARoute(@RequestParam("net") String network,
			@RequestParam("source") String source,
			@RequestParam("destination") String destination,
			@RequestParam("autonomy") String autonomy) {
				return null;

	}

}
