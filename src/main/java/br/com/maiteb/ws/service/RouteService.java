package br.com.maiteb.ws.service;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.maiteb.ws.structure.Graph;
import br.com.maiteb.ws.structure.network.LogisticsNetwork;
import br.com.maiteb.ws.web.vo.RouteVO;

/**
 * Service responsible for calculate routes
 * 
 * @author Maitê Balhester
 *
 */
@Component
public class RouteService {

	@Autowired
	private LogisticsNetworkService logisticsNetworkService;

	
	/**
	 * Calculate the shortest route based on minimum cost of gas, using given parameters
	 * @param network {@link LogisticsNetwork} 
	 * @param source initial node
	 * @param destination end node
	 * @param autonomy autonomy of the vehicle
	 * @param costPerLiter cost per gas liter
	 * @return 
	 */
	public RouteVO calculateRoute(String network, String source,
			String destination, int autonomy, double costPerLiter) {
		LogisticsNetwork logisticsNetwork = logisticsNetworkService.findByName(network);
		
		MinimumRoute minRoute = new MinimumRoute();
		
		recursivelyCalculateShortestRoute(logisticsNetwork.getNetwork(), source, destination, autonomy, 0.0, minRoute, source);
		
		RouteVO vo = new RouteVO(minRoute.getPath(), minRoute.getValue() * costPerLiter);
		
		return vo;
	}
	
	

	/**
	 * Calculate, recursively, the shortest route between two nodes.
	 * 
	 * @param network
	 *            {@link Graph}
	 * @param startNode
	 *            start node
	 * @param endNode
	 *            end node
	 * @param actualLength
	 *            actual length of the path.
	 * @param minCost
	 *            {@link MinimumRoute} that stores the minimum length all
	 *            recursion long.
	 * @return shortest route
	 */
	private double recursivelyCalculateShortestRoute(Graph network,
			String startNode, String endNode, int autonomy, double actualLength,
			MinimumRoute minCost, String path) {
		
		if (actualLength >= minCost.getValue()) {
			return minCost.getValue();
		}
		if (startNode.equals(endNode) && actualLength > 0) {
			minCost.setPath(path);
			minCost.setValue(actualLength);
			return minCost.getValue();
		}
		Map<String, Double> mapDestinationDistance = network
				.getPossiblesDestinationsWithCostFrom(startNode, autonomy);

		double minRoute = minCost.getValue();
		Set<Entry<String, Double>> orderedSet = orderDestination(mapDestinationDistance);
		for (Entry<String, Double> newStop : orderedSet) {
			String node = newStop.getKey();
			minRoute = recursivelyCalculateShortestRoute(network, node,
					endNode, autonomy, actualLength + newStop.getValue(), minCost, concatPath(path, node));
		}
		return minRoute;

	}



	/**
	 * Concatenate the new node to path
	 * @param path path
	 * @param node new node
	 * @return {@link String} that represents all path
	 */
	private String concatPath(String path, String node) {
		return path + ", " + node;
	}

	/**
	 * Order a set for help the shortest route algorithm.
	 * 
	 * @param mapDestinationDistance
	 *            map with the destinations and the distance from the source
	 *            node.
	 * @return an ordered set.
	 */
	private Set<Entry<String, Double>> orderDestination(
			Map<String, Double> mapDestinationDistance) {
		Set<Entry<String, Double>> orderSet = new TreeSet<Entry<String, Double>>(
				new Comparator<Entry<String, Double>>() {

					@Override
					public int compare(Entry<String, Double> o1,
							Entry<String, Double> o2) {

						return Double.compare(o1.getValue(), o2.getValue());
					}
				});
		orderSet.addAll(mapDestinationDistance.entrySet());
		return orderSet;
	}

	/**
	 * Class responsible for store the minimum route for the shortest route
	 * algorithm.
	 * 
	 * @author Maitê Balhester
	 *
	 */
	private class MinimumRoute {

		private double value = Double.MAX_VALUE;
		
		private String path = "";

		

		/**
		 * @return the value
		 */
		public double getValue() {
			return value;
		}


		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(double value) {
			this.value = value;
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



		
		
	}

}
