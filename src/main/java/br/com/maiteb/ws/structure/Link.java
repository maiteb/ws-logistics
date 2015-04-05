package br.com.maiteb.ws.structure;

import java.math.BigDecimal;

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
	 * Calculate liters to use
	 * 
	 * @param autonomy vehicle autonomy
	 * @return liters to use
	 */
	public double getLiter(int autonomy) {
		return (double) distance / (double) autonomy;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return String.format("%s %s %d", sourceNode, destinationNode, distance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((destinationNode == null) ? 0 : destinationNode.hashCode());
		result = prime * result + distance;
		result = prime * result
				+ ((sourceNode == null) ? 0 : sourceNode.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Link other = (Link) obj;
		if (destinationNode == null) {
			if (other.destinationNode != null)
				return false;
		} else if (!destinationNode.equals(other.destinationNode))
			return false;
		if (distance != other.distance)
			return false;
		if (sourceNode == null) {
			if (other.sourceNode != null)
				return false;
		} else if (!sourceNode.equals(other.sourceNode))
			return false;
		return true;
	}

}
