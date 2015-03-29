package br.com.maiteb.ws.structure.network;

import br.com.maiteb.ws.structure.Graph;

public class LogisticsNetwork {
	
	private String name;
	
	private Graph network;

	public LogisticsNetwork(String name, Graph network) {
		super();
		this.name = name;
		this.network = network;
	}

	public String getName() {
		return name;
	}

	public Graph getNetwork() {
		return network;
	}
	
	

}
