package br.com.maiteb.ws.repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.maiteb.ws.structure.Graph;
import br.com.maiteb.ws.structure.Link;
import br.com.maiteb.ws.structure.builder.GraphBuilder;
import br.com.maiteb.ws.structure.network.LogisticsNetwork;


/**
 * Repository of {@link LogisticsNetwork}, responsible for manipulate the database
 * @author Maitê Balhester
 *
 */
@Repository
public class LogisticsNetworkRepository {

	@Autowired
	private File dbFolder;

	private Map<String, LogisticsNetwork> logisticsNetworkCache = new HashMap<String, LogisticsNetwork>();

	/**
	 * Initializes the  {@link LogisticsNetwork} cache
	 */
	@PostConstruct
	public void initCache() {
		logisticsNetworkCache = getAll().stream().collect(
				Collectors.toMap(LogisticsNetwork::getName, (network) -> network));
	}

	/**
	 * Create a new {@link LogisticsNetwork}
	 * @param name network name
	 * @param links network links
	 * @return {@link LogisticsNetwork}
	 * @throws IOException if it cannot write the respective file
	 */
	public LogisticsNetwork create(String name, List<Link> links)
			throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				dbFolder, name)))) {
			for (Link link : links) {
				bw.write(link.toString());
				bw.newLine();
			}
			bw.flush();
		}
		LogisticsNetwork logisticsNetwork = new LogisticsNetwork(name,
				new Graph(links));
		logisticsNetworkCache.put(name, logisticsNetwork);
		return logisticsNetwork;

	}

	
	/**
	 * Find a {@link LogisticsNetwork} with given name
	 * @param name network name
	 * @return {@link LogisticsNetwork} founded
	 * @throws IOException if it cannot find or read the respective file
	 */
	public LogisticsNetwork findByName(String name) throws IOException {
		Optional<LogisticsNetwork> logisticsNetwork = Optional
				.ofNullable(logisticsNetworkCache.getOrDefault(name,
						read(new File(dbFolder, name))));
		if (!logisticsNetwork.isPresent()) {
			throw new RuntimeException("Could not find a logistics network with given name");
		}
		return logisticsNetwork.get();
	}

	/**
	 * Get all {@link LogisticsNetwork} registered
	 * @return {@link List} of {@link LogisticsNetwork}
	 */
	private List<LogisticsNetwork> getAll() {
		List<LogisticsNetwork> allNetworks = new ArrayList<LogisticsNetwork>();
		try {
			allNetworks = readNetworkFiles();
		} catch (IOException e) {
			throw new RuntimeException("Could not find any logistics network");
		}

		return allNetworks;
	}

	/**
	 * Read all logistics network files
	 * @return {@link List} of {@link LogisticsNetwork}
	 * @throws IOException if it cannot read some file
	 */
	private List<LogisticsNetwork> readNetworkFiles() throws IOException {
		List<LogisticsNetwork> allNetworks = new ArrayList<LogisticsNetwork>();
		Path dbPath = FileSystems.getDefault().getPath(dbFolder.getPath());
		DirectoryStream<Path> dirStream = Files.newDirectoryStream(dbPath);
		dirStream.forEach(p -> {
			try {
				allNetworks.add(read(p.toFile()));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return allNetworks;
	}

	/**
	 * Read one  {@link LogisticsNetwork} file
	 * @param file {@link File} of some {@link LogisticsNetwork}
	 * @return {@link LogisticsNetwork}
	 * @throws IOException if it cannot read the respective file
	 */
	private LogisticsNetwork read(File file) throws IOException {
		String fileName = file.getName();
		GraphBuilder graphBuilder = GraphBuilder.aGraph();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			reader.lines().forEach(line -> graphBuilder.withPath(line));
		}
		return new LogisticsNetwork(fileName, graphBuilder.build());

	}

}
