package br.com.maiteb.ws.test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.maiteb.ws.config.WsConfiguration;
import br.com.maiteb.ws.repository.LogisticsNetworkRepository;
import br.com.maiteb.ws.structure.Link;
import br.com.maiteb.ws.structure.builder.LinkBuilder;
import br.com.maiteb.ws.structure.network.LogisticsNetwork;
import br.com.maiteb.ws.web.vo.LogisticsNetworkVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WsConfiguration.class })
public class LogisticsNetworkRepositoryTest {
	
	@Autowired
	LogisticsNetworkRepository repository;
	
	@Autowired
	private File dbFolder;
	
	@Test
	public void testInsertWithSucess() throws IOException {
		LogisticsNetworkVO vo = createTestVO();
		
		LogisticsNetwork create = repository.create(vo.getName(), vo.getLinks());
		
		assertTrue((new File(dbFolder, vo.getName())).exists());
		assertEquals(vo.getName(), create.getName());
		assertEquals(create.getNetwork().getPossiblesDestinationsWithCostFrom("A", 1).keySet().size(), 2);
		assertEquals(create.getNetwork().getPossiblesDestinationsWithCostFrom("B", 1).keySet().size(), 1);
	}

	private LogisticsNetworkVO createTestVO() {
		LogisticsNetworkVO vo = new LogisticsNetworkVO();
		vo.setName("TEST");
		List<Link> links = new ArrayList<Link>();
		links.add(LinkBuilder.aLink().from("A").to("B").distance(10).build());
		links.add(LinkBuilder.aLink().from("B").to("C").distance(7).build());
		links.add(LinkBuilder.aLink().from("A").to("C").distance(25).build());
		vo.setLinks(links);
		return vo;
	}
	
	
}
