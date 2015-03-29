package br.com.maiteb.ws.structure;

/**
 * Class that represents a link between two nodes
 * 
 * @author Maitê Balhester
 *
 */
public class Link {

	private String sourceNode;

	private String destinationNode;

	private int distance;

	/**
	 * Constructor.
	 * 
	 * @param sourceNode
	 *            source node
	 * @param destinationNode
	 *            destination node
	 * @param distance
	 *            distance between them
	 */
	public Link(String sourceNode, String destinationNode, int distance) {
		super();
		this.sourceNode = sourceNode;
		this.destinationNode = destinationNode;
		this.distance = distance;
	}
	
	

	public Link() {
		super();
	}



	/**
	 * @return the sourceNode
	 */
	public String getSourceNode() {
		return sourceNode;
	}

	/**
	 * @return the destinationNode
	 */
	public String getDestinationNode() {
		return destinationNode;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Checks if this link goes to some given destination
	 * 
	 * @param destinationNode
	 *            destination node
	 * @return <code>true</code> if this link goes to the destination,
	 *         <code>false</code> otherwise
	 */
	public boolean isToDestination(String destinationNode) {
		return destinationNode.equals(this.getDestinationNode());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%s %s %d", sourceNode, destinationNode, distance);
	}

}
