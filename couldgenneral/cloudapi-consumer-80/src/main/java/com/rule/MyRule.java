package com.rule;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

public class MyRule extends AbstractLoadBalancerRule {

	private AtomicInteger nextServerCyclicCounter;
	
	private AtomicInteger insistCnt;
	private static Logger log = LoggerFactory.getLogger(MyRule.class);

	public MyRule() {
	    nextServerCyclicCounter = new AtomicInteger(0);
	    insistCnt = new AtomicInteger(0);
	}

	public MyRule(ILoadBalancer lb) {
		this();
		setLoadBalancer(lb);
	}

	@Override
	public Server choose(Object key) {
		return choose(getLoadBalancer(), key);
	}

	public Server choose(ILoadBalancer lb, Object key) {
		if (lb == null) {
			log.warn("no load balancer");
			return null;
		}

		Server server = null;
		int count = 0;
		while (server == null && count++ < 10) {
			List<Server> reachableServers = lb.getReachableServers();
			List<Server> allServers = lb.getAllServers();
			int upCount = reachableServers.size();
			int serverCount = allServers.size();

			if ((upCount == 0) || (serverCount == 0)) {
				log.warn("No up servers available from load balancer: " + lb);
				return null;
			}

			int nextServerIndex = incrementAndGetModulo(serverCount);
			server = allServers.get(nextServerIndex);

			if (server == null) {
				/* Transient. */
				Thread.yield();
				continue;
			}

			if (server.isAlive() && (server.isReadyToServe())) {
				return (server);
			}

			// Next.
			server = null;
		}

		if (count >= 10) {
			log.warn("No available alive servers after 10 tries from load balancer: " + lb);
		}
		return server;
	}

	private int incrementAndGetModulo(int modulo) {
		synchronized(insistCnt) {
			insistCnt.incrementAndGet();
			if (!insistCnt.compareAndSet(3, 0)) {
				return nextServerCyclicCounter.get();
			}
		}
		for (;;) {
			int current = nextServerCyclicCounter.get();
			int next = (current + 1) % modulo;
			if (nextServerCyclicCounter.compareAndSet(current, next))
				return next;
		}
	}

	@Override
	public void initWithNiwsConfig(IClientConfig clientConfig) {

	}

}
