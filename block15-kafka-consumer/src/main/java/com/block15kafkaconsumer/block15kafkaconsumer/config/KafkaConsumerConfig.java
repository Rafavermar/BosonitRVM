package com.block15kafkaconsumer.block15kafkaconsumer.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * Configuración de Kafka Consumer para Spring Boot.
 *
 * <p>
 * Esta clase proporciona configuraciones necesarias para establecer una conexión con
 * un servidor Kafka y consumir mensajes de tópicos específicos. Define las configuraciones
 * básicas del consumidor y la fábrica para crear instancias del oyente del consumidor.
 * </p>
 */
@Configuration
public class KafkaConsumerConfig {

    /**
     * Dirección de los servidores bootstrap de Kafka. Por lo general, es la dirección
     * del broker de Kafka.
     */
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * Configuraciones específicas para el consumidor de Kafka.
     *
     * <p>
     * Define propiedades como la dirección del servidor, el ID del grupo,
     * y las clases de deserialización para las claves y valores de los mensajes.
     * </p>
     *
     * @return Un mapa con las configuraciones del consumidor.
     */

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "group_id");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    /**
     * Define la fábrica para crear instancias del oyente del consumidor.
     *
     * <p>
     * Esta fábrica se encarga de crear los contenedores que se utilizan
     * para consumir mensajes desde el servidor Kafka.
     * </p>
     *
     * @return Una instancia de {@link ConcurrentKafkaListenerContainerFactory} configurada.
     */

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(consumerConfigs()));
        return factory;
    }
}
