/**
 * 
 */
package org.interestTeam.model.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author dongshengzhang
 * @category 文档生成类
 */
@Configuration
@EnableSwagger2
@ConfigurationProperties(prefix="project")
@Data
public class Swagger2 {

	String name;
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("org.interestTeam.model.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(this.name+"项目使用Swagger2构建RESTful APIs")
                .description("")
                .termsOfServiceUrl("")
                .contact("@author dongshengzhang")
                .version("1.0")
                .build();
    }

}
