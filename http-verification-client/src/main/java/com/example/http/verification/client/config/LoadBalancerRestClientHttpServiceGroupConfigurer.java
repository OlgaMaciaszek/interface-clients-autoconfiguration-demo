package com.example.http.verification.client.config;

import java.net.URI;

import org.springframework.boot.autoconfigure.interfaceclients.http.HttpInterfaceGroupProperties;
import org.springframework.boot.autoconfigure.interfaceclients.http.HttpInterfaceGroupsProperties;
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
	private final HttpInterfaceGroupsProperties properties;

	public LoadBalancerRestClientHttpServiceGroupConfigurer(DeferringLoadBalancerInterceptor deferringLoadBalancerInterceptor,
			HttpInterfaceGroupsProperties properties) {
		this.deferringLoadBalancerInterceptor = deferringLoadBalancerInterceptor;
		this.properties = properties;
	}

	@Override
	public void configureGroups(Groups<RestClient.Builder> groups) {
		groups.configureClient((group, builder) -> {
			HttpInterfaceGroupProperties groupProperties = properties.getProperties(group.name());
			URI baseUrl = baseUriComponentsBuilder(groupProperties)
					.host(group.name())
					.encode()
					.build().toUri();
			builder.baseUrl(baseUrl);
			builder.requestInterceptor(deferringLoadBalancerInterceptor);
		});

	}


	private static UriComponentsBuilder baseUriComponentsBuilder(
			HttpInterfaceGroupProperties groupProperties) {
		if (groupProperties != null && groupProperties.getBaseUrl() != null) {
			return UriComponentsBuilder
					.fromUriString(groupProperties.getBaseUrl());
		}
		return UriComponentsBuilder.newInstance()
				.scheme(DEFAULT_SCHEME);
	}
}
