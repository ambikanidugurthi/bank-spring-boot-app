package com.demo.bankspringbootapp.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import com.demo.bankspringbootapp.model.AccountRequest;
import com.demo.bankspringbootapp.model.AccountTransactionRequest;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ser.std.StringSerializer;

//@EnableKafka
@Configuration
public class KafkaService {

	@Value("$spring.kafka.producer.bootstrap-servers")
	private String kafkaBootstrapservers;
	 
	@Bean
    public ProducerFactory<String, AccountRequest> accountRequestProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapservers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }
	
	@Bean
	public KafkaTemplate<String, AccountRequest> kakfaTemplate(){
		return new KafkaTemplate<>(accountRequestProducerFactory());
	}

	@Bean
    public ProducerFactory<String, AccountTransactionRequest> accountTransactionRequestProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapservers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(configProps);
    }
	
	@Bean
	public KafkaTemplate<String, AccountTransactionRequest> accountTransactionRequestkakfaTemplate(){
		return new KafkaTemplate<>(accountTransactionRequestProducerFactory());
	}

	
	/**
	 * 
	 * @return
	 */
	@Bean
    public ConsumerFactory<String, AccountRequest> accountRequestConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapservers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaConsumerFactory<>(configProps);
    }
	
	/**
	 * 
	 * @return
	 */
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, AccountRequest> accountRequestKafkaFactory(){
		ConcurrentKafkaListenerContainerFactory<String, AccountRequest> kafkaFactory = new ConcurrentKafkaListenerContainerFactory<String, AccountRequest>();
		kafkaFactory.setConsumerFactory(accountRequestConsumerFactory());
		return kafkaFactory;
	}
	
	/**
	 * 
	 * @return
	 */
	@Bean
    public ConsumerFactory<String, AccountTransactionRequest> accountTransactionRequestConsumerFactory() {
        Map<String, Object> configProps = new HashMap<>();

        configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapservers);
        configProps.put(ConsumerConfig.GROUP_ID_CONFIG, "group");
        configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaConsumerFactory<>(configProps);
    }
	
	/**
	 * 
	 * @return
	 */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, AccountTransactionRequest> accountTransactionRequestKafkaFactory(){
		ConcurrentKafkaListenerContainerFactory<String, AccountTransactionRequest> kafkaFactory = new ConcurrentKafkaListenerContainerFactory<String, AccountTransactionRequest>();
		kafkaFactory.setConsumerFactory(accountTransactionRequestConsumerFactory());
		return kafkaFactory;
	}
}
