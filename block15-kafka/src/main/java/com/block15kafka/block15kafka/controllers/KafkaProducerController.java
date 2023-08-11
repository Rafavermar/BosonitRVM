package com.block15kafka.block15kafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controlador REST que proporciona endpoints para interactuar con un servidor Kafka.
 * <p>
 * Este controlador permite enviar mensajes al servidor Kafka en un tópico específico mediante
 * una solicitud HTTP GET.
 * </p>
 */


@RestController
public class KafkaProducerController {

    /**
     * Plantilla de Kafka que facilita la producción de mensajes a los tópicos de Kafka.
     */
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * El nombre del tópico al que se enviarán los mensajes.
     */

    private static final String TOPIC = "test_topic";


    /**
     * Endpoint para enviar un mensaje al tópico Kafka.
     *
     * @param message El mensaje que se desea enviar al tópico Kafka.
     * @return Una cadena que indica que el mensaje ha sido enviado.
     */

    @GetMapping("/send/{message}")
    public String sendMessage(@PathVariable String message) {
        kafkaTemplate.send(TOPIC, message);
        return "Sent message: " + message;
    }
}
