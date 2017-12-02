package org.interestTeam.model;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableAutoConfiguration
/**
 * 时间调度，配对@Scheduled等
 */
@EnableScheduling
/**
 * 异步调用，配对@Async等
 */
@EnableAsync
@EnableTransactionManagement
@EnableSwagger2
@SpringBootApplication(scanBasePackages={"org.interestTeam.model.*"})
@Slf4j
public class Application {
	//private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		log.info("程序开始启动!...");
		SpringApplication.run(Application.class, args);
		log.info("程序启动完成!...");
	}

	@Bean
	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			// @Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.setSessionTimeout(30, TimeUnit.MINUTES);// 单位为分钟
				container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
			}
		};
	}
	
}