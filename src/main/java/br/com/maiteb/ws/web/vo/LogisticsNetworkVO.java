package br.com.maiteb.ws.web.vo;

import java.util.List;

import br.com.maiteb.ws.structure.Link;

/**
 * Value Object that represents a Logistics Network for register
 * @author Maitê Balhester
 *
 */
public class LogisticsNetworkVO {
	
	private String name;
	
	private List<Link> links;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the links
	 */
	public List<Link> getLinks() {
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(List<Link> links) {
		this.links = links;
	}
	


	
	

}
