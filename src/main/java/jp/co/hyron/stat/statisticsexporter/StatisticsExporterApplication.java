package jp.co.hyron.stat.statisticsexporter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StatisticsExporterApplication {

	public static void main(String[] args) {
		SpringApplication.run(StatisticsExporterApplication.class, args);
	}

}
