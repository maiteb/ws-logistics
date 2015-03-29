package br.com.maiteb.ws.web.vo;

import java.util.List;

import br.com.maiteb.ws.structure.Link;

public class LogisticsNetworkVO {
	
	private String name;
	
	private List<Link> links;
	


	public String getName() {
		return name;
	}

	public List<Link> getLinks() {
		return links;
	}
	
	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
