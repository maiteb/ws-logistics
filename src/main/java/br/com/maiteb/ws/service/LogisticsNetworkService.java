package br.com.maiteb.ws.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maiteb.ws.repository.LogisticsNetworkRepository;
import br.com.maiteb.ws.structure.Link;
import br.com.maiteb.ws.structure.network.LogisticsNetwork;


/**
 * Service responsible for manipulate {@link LogisticsNetwork}
 * @author Maitê Balhester
 *
 */
@Service
public class LogisticsNetworkService {

	@Autowired
	private LogisticsNetworkRepository logisticsNetworkRepository;
	
	
	/**
	 * Create a new {@link LogisticsNetwork}
	 * @param name name 
	 * @param links links of {@link LogisticsNetwork}
	 * @return a new {@link LogisticsNetwork}
	 */
	public LogisticsNetwork createNewLogisticsNetwork(String name, List<Link> links) {
		try {
			return logisticsNetworkRepository.create(name, links);
		} catch (IOException e) {
			throw new RuntimeException("Could not create a logistics network",e);
		}
				
	}
	
	/**
	 * Find a {@link LogisticsNetwork} by name
	 * @param name name for search
	 * @return {@link LogisticsNetwork} founded
	 */
	public LogisticsNetwork findByName(String name) {
		try {
			return logisticsNetworkRepository.findByName(name);
		} catch (IOException e) {
			throw new RuntimeException("Could not find a logistics network with given name", e);
		}
	}
		
}
