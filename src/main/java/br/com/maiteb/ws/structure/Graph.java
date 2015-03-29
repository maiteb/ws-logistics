package br.com.maiteb.ws.structure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.attribute.standard.Destination;

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
	 * Checks if this graph contains some node
	 * 
	 * @param node
	 *            node
	 * @return <code>true</code> if this graph contains the given node,
	 *         <code>false</code> otherwise.
	 */
	public boolean containsNode(String node) {
		return sourceNodeLinksMap.containsKey(node);
	}

	/**
	 * Get the distance of a {@link Link} connecting two nodes
	 * 
	 * @param sourceNode
	 *            source node
	 * @param destinationNode
	 *            {@link Destination}
	 * @return distance between the two nodes or -1 if didn't exist a link
	 *         connecting the nodes
	 */
	public int getDistanceOfLink(String sourceNode, String destinationNode) {
		List<Link> linksFromSource = getLinksFromSource(sourceNode);
		if (!linksFromSource.isEmpty()) {
			for (Link link : linksFromSource) {
				if (link.isToDestination(destinationNode)) {
					return link.getDistance();
				}
			}
		}
		return -1;
	}

	/**
	 * Get all possible destinations from a source
	 * 
	 * @param sourceNode
	 *            source node
	 * @return {@link List} of {@link String} that represents all the
	 *         destinations
	 */
	public List<String> getPossiblesDestinationsFrom(String sourceNode) {
		List<Link> linksFromSource = getLinksFromSource(sourceNode);
		ArrayList<String> possiblesDestinations = new ArrayList<String>();
		if (!linksFromSource.isEmpty()) {
			for (Link link : linksFromSource) {
				possiblesDestinations.add(link.getDestinationNode());
			}
		}
		return possiblesDestinations;
	}

	/**
	 * Get all possible destinations and distance between them from a source
	 * 
	 * @param sourceNode
	 *            source node
	 * @return {@link Map<String,Integer> that represents all the destinations
	 *         and the distance between them.
	 */
	public Map<String, Integer> getPossiblesDestinationsWithDistanceFrom(
			String sourceNode) {
		List<Link> linksFromSource = getLinksFromSource(sourceNode);
		Map<String, Integer> mapDestinationDistance = new HashMap<String, Integer>();
		if (!linksFromSource.isEmpty()) {
			for (Link link : linksFromSource) {
				mapDestinationDistance.put(link.getDestinationNode(),
						link.getDistance());
			}
		}
		return mapDestinationDistance;
	}

}
