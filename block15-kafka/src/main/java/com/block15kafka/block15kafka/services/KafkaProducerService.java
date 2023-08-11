package com.block15kafka.block15kafka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Servicio que facilita la interacción con un servidor Kafka, tanto para enviar mensajes
 * como para escuchar respuestas de un tópico específico.
 *
 * <p>
 * Este servicio utiliza una plantilla de Kafka para enviar mensajes a un tópico de Kafka
 * y tiene un oyente que escucha las respuestas en un tópico diferente.
 * </p>
 */
@Service
public class KafkaProducerService {


    /**
     * Plantilla de Kafka utilizada para enviar mensajes al tópico de Kafka.
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;


    /**
     * Envía un mensaje al tópico 'test_topic' en el servidor Kafka.
     *
     * @param message El mensaje que se desea enviar.
     */

    public void sendMessage(String message) {
        kafkaTemplate.send("test_topic", message);
    }

    /**
     * Método oyente que escucha y maneja los mensajes del tópico 'response_topic'.
     * <p>
     * Cuando se recibe un mensaje en el tópico 'response_topic', este método lo procesa
     * y muestra el mensaje en la consola.
     * </p>
     *
     * @param message El mensaje recibido del tópico 'response_topic'.
     */

    @KafkaListener(topics = "response_topic", groupId = "producer_group_id")
    public void listenResponse(String message) {
        System.out.println("Received response: " + message);
    }
}
