package cn.com.hyun.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableDiscoveryClient
@SpringBootApplication
@Component
public class ApplicationServerGateway {

	@RequestMapping("/circuitbreakerfallback")
	public String circuitbreakerfallback() {
		System.out.println("tes");
		return "This is a fallback";
	}

	@Value("${metaapi.apitoken}")
	String metaApiToken;

	@Value("${metaapi.client.uri}")
	String metaapiClientUri;
	@Value("${metaapi.metastats.uri}")
	String metaapiStatsUri;
	@Value("${metaapi.copyfactory.uri}")
	String metaapiCopyfactoryUri;
	@Value("${metaapi.provisioning.uri}")
	String metaapiProvisioningUri;
	@Value("${metaapi.market.uri}")
	String metaapiMarketUri;
	@Value("${metaapi.management.uri}")
	String metaapiManagementUri;
	@Value("${metaapi.clientvint.uri}")
	String metaapiClientvintUri;
	@Value("${api.server.demo.uri}")
	String apiServerDemoUri;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {

		return builder.routes()
				.route("/api/mt/client", r -> r.path("/api/mt/client/**")
						.filters(f -> f
								.setRequestHeader("auth-token", metaApiToken)
								.stripPrefix(3))
						.uri(metaapiClientUri))
				.route("/api/mt/metastats", r -> r.path("/api/mt/metastats/**")
						.filters(f -> f
								.setRequestHeader("auth-token", metaApiToken)
								.stripPrefix(3))
						.uri(metaapiStatsUri))
				.route("/api/mt/copyfactory", r -> r.path("/api/mt/copyfactory/**")
						.filters(f -> f
								.setRequestHeader("auth-token", metaApiToken)
								.setRequestHeader("Host","copyfactory-api-v1.singapore.agiliumtrade.ai")
								.stripPrefix(3))
						.uri(metaapiCopyfactoryUri))
				.route("/api/mt/provisioning", r -> r.path("/api/mt/provisioning/**")
						.filters(f -> f
								.setRequestHeader("auth-token", metaApiToken)
								.stripPrefix(3))
						.uri(metaapiProvisioningUri))
				.route("/api/mt/market", r -> r.path("/api/mt/market/**")
						.filters(f -> f
								.setRequestHeader("auth-token", metaApiToken)
								.stripPrefix(3))
						.uri("https://mt-market-data-client-api-v1.singapore.agiliumtrade.ai"))
				.route("/api/mt/management", r -> r.path("/api/mt/management/*")
						.filters(f -> f
								.setRequestHeader("auth-token", metaApiToken)
								.stripPrefix(3))
						.uri(metaapiMarketUri))
				.route("/api/mt/clientvint", r -> r.path("/api/mt/clientvint/**")
						.filters(f -> f
								.setRequestHeader("auth-token", metaApiToken)
								.setRequestHeader("HOST","mt-client-api-v1.vint-hill.agiliumtrade.ai")
								.stripPrefix(3))
						.uri(metaapiClientvintUri))

				.route("/api/testroute", r -> r.path("/api/testroute/**")
						.filters(f -> f
								.addRequestParameter("auth-token", metaApiToken)
								.stripPrefix(2))
						.uri("http://localhost:8301"))


				.route("demo", p -> p.path("/api/d/**")
						.filters(f -> f
								.addRequestParameter("api_key", "demo")
								.stripPrefix(1)
						)
							//	.filter(testGatewayFilter.testFilter())
						.uri(apiServerDemoUri))
				.build();



		//@formatter:on
	}

	@Bean
	RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 2);
	}

	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) throws Exception {
		return http.httpBasic().and()
				.csrf().disable()
				.authorizeExchange()
				.pathMatchers("/anything/**").authenticated()
				.anyExchange().permitAll()
				.and()
				.build();
	}

	@Bean
	public MapReactiveUserDetailsService reactiveUserDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER").build();
		return new MapReactiveUserDetailsService(user);
	}

	public static void main(String[] args) {

		SpringApplication.run(ApplicationServerGateway.class, args);
	}
}
