package br.com.maiteb.ws.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.maiteb.ws.repository.LogisticsNetworkRepository;
import br.com.maiteb.ws.structure.Link;
import br.com.maiteb.ws.structure.network.LogisticsNetwork;

@Service
public class LogisticsNetworkService {

	@Autowired
	private LogisticsNetworkRepository logisticsNetworkRepository;
	
	public LogisticsNetwork createNewLogisticNetwork(String name, List<Link> links) {
		try {
			return logisticsNetworkRepository.create(name, links);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
				
	}
	
	public LogisticsNetwork findByName(String name) {
		try {
			return logisticsNetworkRepository.findByName(name);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
		
}
