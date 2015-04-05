package br.com.maiteb.ws.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.maiteb.ws.service.LogisticsNetworkService;
import br.com.maiteb.ws.structure.network.LogisticsNetwork;
import br.com.maiteb.ws.web.vo.LogisticsNetworkVO;

/**
 * {@link RestController} for register a {@link LogisticsNetwork}
 * @author Maitê Balhester
 *
 */
@RestController
public class LogisticsNetworkController {
	
	@Autowired
	private LogisticsNetworkService service;

	/**
	 * Create a new {@link LogisticsNetwork}
	 * @param vo {@link LogisticsNetworkVO} received
	 * @return {@link ResponseEntity} with respective {@link HttpStatus} 
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json", value = "/logisticsNetwork/new")
	public ResponseEntity<String> newLogisticsNetwork(@RequestBody LogisticsNetworkVO vo) {
		Optional<LogisticsNetwork> aLogisticsNetwork = Optional.ofNullable(service.createNewLogisticsNetwork(vo.getName(), vo.getLinks()));
		if (aLogisticsNetwork.isPresent()) {
				return new ResponseEntity<String>("Logistics network created with success", HttpStatus.CREATED);
			} else {
				return new ResponseEntity<String>("Error while creating a logistics network",
						HttpStatus.BAD_REQUEST);
			}
				
	}
	
}
