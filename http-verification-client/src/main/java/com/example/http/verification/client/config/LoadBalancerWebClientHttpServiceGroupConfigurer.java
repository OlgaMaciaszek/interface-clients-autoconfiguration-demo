package com.example.http.verification.client.config;

import org.springframework.boot.autoconfigure.interfaceclients.http.HttpInterfaceGroupProperties;
import org.springframework.boot.autoconfigure.interfaceclients.http.HttpInterfaceGroupsProperties;
import org.springframework.cloud.client.loadbalancer.reactive.DeferringLoadBalancerExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientHttpServiceGroupConfigurer;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author Olga Maciaszek-Sharma
 */
@SuppressWarnings("rawtypes")
public class LoadBalancerWebClientHttpServiceGroupConfigurer implements WebClientHttpServiceGroupConfigurer {

	private static final String DEFAULT_SCHEME = "http";

	private final DeferringLoadBalancerExchangeFilterFunction deferringLoadBalancerExchangeFilterFunction;
	private final HttpInterfaceGroupsProperties properties;

	public LoadBalancerWebClientHttpServiceGroupConfigurer(DeferringLoadBalancerExchangeFilterFunction deferringLoadBalancerExchangeFilterFunction,
			HttpInterfaceGroupsProperties properties) {
		this.deferringLoadBalancerExchangeFilterFunction = deferringLoadBalancerExchangeFilterFunction;
		this.properties = properties;
	}

	@Override
	public void configureGroups(Groups<WebClient.Builder> groups) {
		groups.configureClient((group, builder) -> {
			HttpInterfaceGroupProperties groupProperties = properties.getProperties(group.name());
			String baseUrl = baseUriComponentsBuilder(groupProperties)
					.host(group.name())
					.encode()
					.build().toUriString();
			builder.baseUrl(baseUrl);
			builder.filter(deferringLoadBalancerExchangeFilterFunction);
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
