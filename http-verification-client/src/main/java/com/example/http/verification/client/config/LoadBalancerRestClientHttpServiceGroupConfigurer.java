package com.example.http.verification.client.config;

import java.net.URI;

import org.springframework.cloud.client.loadbalancer.DeferringLoadBalancerInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientHttpServiceGroupConfigurer;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Olga Maciaszek-Sharma
 */
public class LoadBalancerRestClientHttpServiceGroupConfigurer implements RestClientHttpServiceGroupConfigurer {

	private static final String DEFAULT_SCHEME = "http";

	private final DeferringLoadBalancerInterceptor deferringLoadBalancerInterceptor;

	public LoadBalancerRestClientHttpServiceGroupConfigurer(DeferringLoadBalancerInterceptor deferringLoadBalancerInterceptor) {
		this.deferringLoadBalancerInterceptor = deferringLoadBalancerInterceptor;
	}

	@Override
	public void configureGroups(Groups<RestClient.Builder> groups) {
		groups.configureClient((group, builder) -> {
			URI baseUrl = UriComponentsBuilder.newInstance()
					.scheme(DEFAULT_SCHEME)
					.host(group.name())
					.build().encode().toUri();
			builder.baseUrl(baseUrl);
			builder.requestInterceptor(deferringLoadBalancerInterceptor);
		});

	}
}
