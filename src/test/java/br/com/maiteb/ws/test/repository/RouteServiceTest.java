package br.com.maiteb.ws.test.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.maiteb.ws.config.WsConfiguration;
import br.com.maiteb.ws.service.LogisticsNetworkService;
import br.com.maiteb.ws.service.RouteService;
import br.com.maiteb.ws.structure.Link;
import br.com.maiteb.ws.structure.builder.LinkBuilder;
import br.com.maiteb.ws.web.vo.LogisticsNetworkVO;
import br.com.maiteb.ws.web.vo.RouteVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WsConfiguration.class })
public class RouteServiceTest {
	
	@Autowired
	public RouteService service;
	
	@Autowired
	public LogisticsNetworkService logisticsNetworkService;
	
	@Test
	public void testARoute() {
		createATestNetworkWithRouteAtoD("Test");
		
		RouteVO route = service.calculateRoute("Test", "A", "D", 10, 2.50D);
		
		assertNotNull(route);
		assertEquals("A, B, D", route.getPath());
		assertEquals(6.25D, route.getCost(), 0);
	}
	
	@Test
	public void testWhenThereIsNoRoute() {
		
		RouteVO route = service.calculateRoute("Test", "B", "C", 10, 2.50D);
		
		assertNotNull(route);
		assertEquals("", route.getPath());
		assertEquals(-1, route.getCost(), 0);
	}


	private void createATestNetworkWithRouteAtoD(String name) {
		LogisticsNetworkVO vo = new LogisticsNetworkVO();
		vo.setName(name);
		List<Link> links = new ArrayList<Link>();
		links.add(LinkBuilder.aLink().from("A").to("B").distance(10).build());
		links.add(LinkBuilder.aLink().from("B").to("D").distance(15).build());
		links.add(LinkBuilder.aLink().from("A").to("C").distance(20).build());
		links.add(LinkBuilder.aLink().from("C").to("D").distance(30).build());
		links.add(LinkBuilder.aLink().from("B").to("E").distance(50).build());
		links.add(LinkBuilder.aLink().from("D").to("E").distance(30).build());
		vo.setLinks(links);
		
		logisticsNetworkService.createNewLogisticsNetwork(vo.getName(), vo.getLinks());
	}
}
