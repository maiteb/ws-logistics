package br.com.maiteb.ws.structure.network;

import br.com.maiteb.ws.structure.Graph;

/**
 * Represents a logistics network
 * @author Maitê Balhester
 *
 */
public class LogisticsNetwork {
	
	private String name;
	
	private Graph network;

	/**
	 * Constructor
	 * @param name name
	 * @param network network
	 */
	public LogisticsNetwork(String name, Graph network) {
		super();
		this.name = name;
		this.network = network;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the network
	 */
	public Graph getNetwork() {
		return network;
	}

	
	
	

}
