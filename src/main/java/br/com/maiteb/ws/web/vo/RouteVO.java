package br.com.maiteb.ws.web.vo;

public class RouteVO {
	
	private String allRoute;
	
	private Double cost;

	public RouteVO(String allRoute, Double cost) {
		super();
		this.allRoute = allRoute;
		this.cost = cost;
	}

	public String getAllRoute() {
		return allRoute;
	}

	public Double getCost() {
		return cost;
	}
	
	

}
