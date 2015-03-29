package br.com.maiteb.ws.structure.builder;

import br.com.maiteb.ws.structure.Link;
import br.com.maiteb.ws.structure.api.Builder;

/**
 * {@link Builder} for {@link Link}
 * 
 * @author Maitê Balhester
 *
 */
public class LinkBuilder implements Builder<Link> {

	private String sourceNode;

	private String destinationNode;

	private int distance;

	/**
	 * Creates a {@link LinkBuilder}
	 * 
	 * @return {@link LinkBuilder}
	 */
	public static LinkBuilder aLink() {
		return new LinkBuilder();
	}

	/**
	 * Specify the source node
	 * 
	 * @param sourceNode
	 *            source node
	 * @return {@link LinkBuilder}
	 */
	public LinkBuilder from(String sourceNode) {
		this.sourceNode = sourceNode;
		return this;
	}

	/**
	 * Specify the destination node
	 * 
	 * @param destinationNode
	 *            destination node
	 * @return {@link LinkBuilder}
	 */
	public LinkBuilder to(String destinationNode) {
		this.destinationNode = destinationNode;
		return this;
	}

	/**
	 * Specify the distance of the link
	 * 
	 * @param distance
	 *            distance
	 * @return {@link LinkBuilder}
	 */
	public LinkBuilder distance(int distance) {
		this.distance = distance;
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Link build() {
		return new Link(sourceNode, destinationNode, distance);
	}

}
