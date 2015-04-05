package br.com.maiteb.ws.structure.builder;

import static br.com.maiteb.ws.structure.builder.LinkBuilder.aLink;

import java.util.ArrayList;
import java.util.List;

import br.com.maiteb.ws.structure.Graph;
import br.com.maiteb.ws.structure.Link;
import br.com.maiteb.ws.structure.api.Builder;

/**
 * {@link Builder} for {@link Graph}
 * 
 * @author Maitê Balhester
 *
 */
public class GraphBuilder implements Builder<Graph> {


	private static final int SOURCE_NODE_INDEX = 0;

	private static final int DESTINATION_NODE_INDEX = 1;

	private static final int DISTANCE_INDEX = 2;

	private final List<Link> links = new ArrayList<Link>();

	/**
	 * Creates a {@link GraphBuilder}
	 * 
	 * @return {@link GraphBuilder}
	 */
	public static GraphBuilder aGraph() {
		return new GraphBuilder();
	}

	/**
	 * Specify a path
	 * 
	 * @param path
	 *            path formatted like AB5 or CE6
	 * @return {@link GraphBuilder}
	 */
	public GraphBuilder withPath(String path) {
		String[] splittedPath = path.split(" ");
		String sourceNode = splittedPath[SOURCE_NODE_INDEX];
		String destinationNode = splittedPath[DESTINATION_NODE_INDEX];
		int distance = Integer.parseInt((splittedPath[DISTANCE_INDEX]));
		links.add(aLink().from(sourceNode).to(destinationNode)
				.distance(distance).build());
		return this;

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Graph build() {
		return new Graph(links);
	}

}
