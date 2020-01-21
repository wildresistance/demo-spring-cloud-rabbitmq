package com.neklo.consumer;

import com.neklo.dao.CoordsRepository;
import com.neklo.producer.ObjectPosition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableBinding(Sink.class)
@Slf4j
public class CoordsConsumer {

	@Resource
	CoordsRepository coordsRepository;

	@StreamListener(Sink.INPUT)
	public void consume(@Payload ObjectPosition objectPosition) {
		log.debug("got message {}", objectPosition);
		coordsRepository.addCords(objectPosition);
	}
}
