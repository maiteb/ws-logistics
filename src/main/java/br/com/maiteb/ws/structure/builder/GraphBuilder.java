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

	private static final int PATH_CONFIGURATION_LENGTH = 3;

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
	 *            path formatted like A B 5 or C E 6
	 * @return {@link GraphBuilder}
	 */
	public GraphBuilder withPath(String path) {
		String sanitizedPath = sanizitize(path);
		if (validatePath(sanitizedPath)) {
			char[] splittedPath = new char[PATH_CONFIGURATION_LENGTH];
			sanitizedPath.getChars(0, PATH_CONFIGURATION_LENGTH, splittedPath, 0);
			String sourceNode = Character
					.toString(splittedPath[SOURCE_NODE_INDEX]);
			String destinationNode = Character
					.toString(splittedPath[DESTINATION_NODE_INDEX]);
			int distance = Integer.parseInt(Character
					.toString(splittedPath[DISTANCE_INDEX]));
			links.add(aLink().from(sourceNode).to(destinationNode)
					.distance(distance).build());
			return this;
		} else {
			throw new RuntimeException("Given path must have 3 characters.");
		}
	}

	/**
	 * Remove all the white spaces
	 * @param path 
	 * @return
	 */
	private String sanizitize(String path) {
		return path.replaceAll("\\s+","");
	}

	/**
	 * Checks if the path is well formed.
	 * 
	 * @param path
	 *            path
	 * @return <code>true</code> if the path is well formed, <code>false</code>
	 *         otherwise
	 */
	private boolean validatePath(String path) {
		return path.length() == PATH_CONFIGURATION_LENGTH;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Graph build() {
		return new Graph(links);
	}

}
