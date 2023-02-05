package cn.com.hyun.configuration;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.AllowableListValues;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.withClassAnnotation;

/**
 * Created by helloworld on 2017/2/20.
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket docket() {

        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name("token").description("密钥").
                modelRef(new ModelRef("string")).
                parameterType("header")
                .required(false)
                .allowMultiple(false)
                .parameterType("query")
                .build();

        pars.add(tokenPar.build());
        //Parameter visitType = new Parameter("visitType", "访问类型(app/pc)", "", false, false, new ModelRef("string"), Optional.absent(), new AllowableListValues(new ArrayList<>(), "string"), "query", "");
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .apiInfo(myApiInfo())
              //  .globalOperationParameters(Lists.newArrayList(token))
                .globalOperationParameters(pars)
                .select()
                .apis(withClassAnnotation(Api.class))
              //  .apis( RequestHandlerSelectors.basePackage( "cn.com.hyun.demo" ) )
                .paths(any())
                .build();
    }


    private ApiInfo myApiInfo() {
        return new ApiInfoBuilder()
                .contact(new Contact("zhg", "", ""))
                .title("demo 测试接口")
                .version("1.0")
                .build();
    }

    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(@NotNull WebEndpointsSupplier webEndpointsSupplier,
                                                                         @NotNull ServletEndpointsSupplier servletEndpointsSupplier,
                                                                         @NotNull ControllerEndpointsSupplier controllerEndpointsSupplier,
                                                                         EndpointMediaTypes endpointMediaTypes,
                                                                         @NotNull CorsEndpointProperties corsProperties,
                                                                         @NotNull WebEndpointProperties webEndpointProperties,
                                                                         Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment,
                basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes,
                corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath),
                shouldRegisterLinksMapping, null);
    }

    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment,
                                               String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath)
                || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}
