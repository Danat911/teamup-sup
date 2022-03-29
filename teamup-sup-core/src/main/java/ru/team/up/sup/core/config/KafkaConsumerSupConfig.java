package ru.team.up.sup.core.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.team.up.dto.AppModuleNameDto;
import ru.team.up.dto.ListSupParameterDto;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerSupConfig {
    /**
     * Значение groupId, которе определяет группу консьюмеров, в рамках которой доставляется один экземпляр сообщения.
     * Например, при трех консьюмеров в одной группе, слушающих один Topic сообщение достанется, только, одному
     */
    @Value(value = "${kafka.group.id}")
    private String groupId;
    /**
     * Адрес bootstrap сервера kafka
     */
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    public Map<String, Object> jsonConfigProps() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        configProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        configProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        return configProps;
    }

    @Bean
    public ConsumerFactory<String, ListSupParameterDto> parameterConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(jsonConfigProps(), new StringDeserializer(), new JsonDeserializer<>(ListSupParameterDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ListSupParameterDto> kafkaParamContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ListSupParameterDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(parameterConsumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, AppModuleNameDto> moduleConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(jsonConfigProps(), new StringDeserializer(), new JsonDeserializer<>(AppModuleNameDto.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, AppModuleNameDto> kafkaModuleContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, AppModuleNameDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(moduleConsumerFactory());
        return factory;
    }
}