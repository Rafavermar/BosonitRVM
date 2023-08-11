package com.block15kafka.block15kafka.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;


/**
 * Configuración del productor Kafka que proporciona las configuraciones necesarias y
 * la plantilla de Kafka para enviar mensajes a un servidor Kafka.
 * <p>
 * Esta clase utiliza valores de configuración de Spring (por ejemplo, desde el archivo application.properties)
 * para configurar las propiedades específicas de Kafka.
 * </p>
 */
@Configuration
public class KafkaProducerConfig {

    /**
     * La dirección del servidor Kafka al que se debe conectar el productor.
     * Esta dirección suele estar en el formato: {@code host1:port1,host2:port2,...}.
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Crea y devuelve una configuración para el productor Kafka.
     *
     * @return Un mapa de propiedades con configuraciones para el productor Kafka.
     */

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
    /**
     * Crea y devuelve una plantilla de Kafka que utiliza la configuración del productor.
     * La plantilla de Kafka se utiliza para enviar mensajes a los tópicos de Kafka.
     *
     * @return Una instancia de {@link KafkaTemplate} configurada.
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs()));
    }
}
