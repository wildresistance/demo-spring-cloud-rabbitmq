package com.neklo.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectPosition implements Serializable {
	private static final List<String> objects = List.of("object1", "object2", "object3", "object4", "object5");
	private String object; // will be a partition key
	private BigDecimal latitude;
	private BigDecimal longitude;
	private long timestamp;

	public static ObjectPosition random() {
		Random random = new Random();
		BigDecimal latitude = BigDecimal.valueOf(-65.5);
		BigDecimal longitude = BigDecimal.valueOf(-43.5);
		return new ObjectPosition(objects.get(random.nextInt(5)),
				latitude.add(BigDecimal.valueOf(random.nextDouble())),
				longitude.add(BigDecimal.valueOf(random.nextDouble())),
				LocalDateTime.now().plusMinutes(random.nextInt(40)).toEpochSecond(ZoneOffset.UTC));
	}
}
