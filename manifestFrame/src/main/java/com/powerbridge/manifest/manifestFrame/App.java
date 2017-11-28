package com.powerbridge.manifest.manifestFrame;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
/**
 * 时间调度，配对@Scheduled等
 */
@EnableScheduling
/**
 * 异步调用，配对@Async等
 */
@EnableAsync
//@EnableSwagger2
@SpringBootApplication(scanBasePackages={"com.powerbridge.manifest.manifestFrame.*"})
public class App {
	private static final Logger logger = LoggerFactory.getLogger(App.class);
	//public final static int PORT = 8011;

	public static void main(String[] args) {
		logger.info("程序开始启动!...");
		SpringApplication.run(App.class, args);
	}

	@Bean
	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer() {
		return new EmbeddedServletContainerCustomizer() {
			// @Override
			public void customize(ConfigurableEmbeddedServletContainer container) {
				container.setSessionTimeout(60, TimeUnit.MINUTES);// 单位为分钟
			}
		};
	}
}