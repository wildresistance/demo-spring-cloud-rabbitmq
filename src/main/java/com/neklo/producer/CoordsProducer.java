package com.neklo.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.IntegrationMessageHeaderAccessor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

@Component
@EnableBinding(Source.class)
@Slf4j
public class CoordsProducer {
	@Resource
	private Source source;

	@Scheduled(fixedDelay = 10000L)
	public void addUserRecord() {
		Random random = new Random();
		IntStream.range(0, random.nextInt(10)).forEach(i-> {
			Message<ObjectPosition> randomMessage = MessageBuilder.withPayload(ObjectPosition.random()).
					setHeaderIfAbsent(IntegrationMessageHeaderAccessor.CORRELATION_ID,
							UUID.randomUUID()).build();
			source.output().send(randomMessage);
			log.debug("Message {} has been sent", randomMessage.getPayload());
		});
	}

}
