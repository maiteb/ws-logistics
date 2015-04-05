package br.com.maiteb.ws.web.vo;

public class RouteVO {
	
	private String path;
	
	private double cost = -1;

	public RouteVO(String path, double cost) {
		super();
		this.path = path;
		if (!path.isEmpty()) {
		this.cost = cost;
		} 
	}

	

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}



	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}



	/**
	 * @return the cost
	 */
	public double getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(double cost) {
		this.cost = cost;
	}

	

}
