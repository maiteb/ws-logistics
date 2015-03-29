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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import br.com.maiteb.ws.structure.Graph;
import br.com.maiteb.ws.structure.Link;
import br.com.maiteb.ws.structure.builder.GraphBuilder;
import br.com.maiteb.ws.structure.network.LogisticsNetwork;

@Repository
public class LogisticsNetworkRepository {

	@Autowired
	private File dbFolder;
	
	public List<LogisticsNetwork> getAll() {
		List<LogisticsNetwork> allNetworks = new ArrayList<LogisticsNetwork>();
		try {
			allNetworks = readNetworkFiles();
		} catch (IOException e) {
			// print an error please
		}

		return allNetworks;
	}

	private List<LogisticsNetwork> readNetworkFiles() throws IOException {
		List<LogisticsNetwork> allNetworks = new ArrayList<LogisticsNetwork>();
		Path dbPath = FileSystems.getDefault().getPath(dbFolder.getPath());
		DirectoryStream<Path> dirStream = Files.newDirectoryStream(dbPath);
		dirStream.forEach(p -> {
			try {
				read(p.toFile(), allNetworks);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		});
		return allNetworks;
	}

	private void read(File file, List<LogisticsNetwork> allNetworks)
			throws IOException {
		String fileName = file.getName();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			GraphBuilder graphBuilder = GraphBuilder.aGraph();
			
			reader.lines().forEach(line -> graphBuilder.withPath(line));
			
			allNetworks.add(new LogisticsNetwork(fileName, graphBuilder.build()));
		}
		
	}

	public LogisticsNetwork create(String name, List<Link> links) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dbFolder, name)))) {
			for (Link link : links) {
				bw.write(link.toString());
				bw.newLine();
			}
			bw.flush();
		}
		LogisticsNetwork logisticsNetwork = new LogisticsNetwork(name, new Graph(links));
		return logisticsNetwork;
		
	}

}
