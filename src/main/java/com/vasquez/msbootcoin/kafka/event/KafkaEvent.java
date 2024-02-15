package com.vasquez.msbootcoin.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This class defines the event model
 *
 * @param <T>
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaEvent<T> {

  private String id;
  private Date date;
  private String eventType;
  private T data;

}
