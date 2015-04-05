package br.com.maiteb.ws.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.print.attribute.standard.Destination;

import br.com.maiteb.ws.structure.builder.LinkBuilder;
import br.com.maiteb.ws.structure.network.LogisticsNetwork;

/**
 * Class that represents a graph.
 * 
 * @author Maitê Balhester
 *
 */
public class Graph {

	private final Map<String, List<Link>> sourceNodeLinksMap = new HashMap<String, List<Link>>();

	/**
	 * Constructor. In this method, we fill the sourceNodeLinksMap with the
	 * graph configuration
	 * 
	 * @param links
	 *            {@link List} of {@link Link}
	 */
	public Graph(List<Link> links) {
		super();

		for (Link link : links) {
			String sourceNode = link.getSourceNode();
			if (sourceNodeLinksMap.containsKey(sourceNode)) {
				sourceNodeLinksMap.get(sourceNode).add(link);
			} else {
				ArrayList<Link> sourceNodeLinks = new ArrayList<Link>(
						Collections.singletonList(link));
				sourceNodeLinksMap.put(sourceNode, sourceNodeLinks);
			}
		
		}

	}

	/**
	 * Get all the links given a source node
	 * 
	 * @param sourceNode
	 *            source node
	 * @return {@link List} of {@link Link}
	 */
	private List<Link> getLinksFromSource(String sourceNode) {
		return sourceNodeLinksMap.getOrDefault(sourceNode,
				new ArrayList<Link>());
	}




	/**
	 * Get all possible destinations and liters between them from a source
	 * 
	 * @param sourceNode
	 *            source node
	 * @return {@link Map<String,Integer> that represents all the destinations
	 *         and the liters between them.
	 */
	public Map<String, Double> getPossiblesDestinationsWithCostFrom(
			String sourceNode, int autonomy) {
		List<Link> linksFromSource = getLinksFromSource(sourceNode);

		return linksFromSource.stream().collect(
				Collectors.toMap(Link::getDestinationNode,
						(c) -> c.getLiter(autonomy)));
	}

}
