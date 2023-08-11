package com.block15kafkaconsumer.block15kafkaconsumer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


/**
 * Servicio para consumir mensajes desde un tópico de Kafka y responder a ellos.
 *
 * <p>
 * Este servicio se encarga de escuchar mensajes desde un tópico específico en Kafka.
 * Una vez que se recibe un mensaje, el servicio envía una respuesta al tópico definido
 * como {@code TOPIC}.
 * </p>
 */
@Service
public class KafkaConsumerService {

    /**
     * Plantilla para interactuar con Kafka, permitiendo enviar y recibir mensajes.
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * Tópico al cual se enviarán las respuestas tras consumir un mensaje.
     */

    private static final String TOPIC = "response_topic";

    /**
     * Método para consumir mensajes desde el tópico "test_topic".
     *
     * <p>
     * Al recibir un mensaje desde "test_topic", este método imprime el mensaje
     * consumido y envía una respuesta al tópico {@code TOPIC}.
     * </p>
     *
     * @param message El mensaje consumido desde el tópico "test_topic".
     */

    @KafkaListener(topics = "test_topic", groupId = "group_id")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        kafkaTemplate.send(TOPIC, "Response to: " + message);
    }
}
