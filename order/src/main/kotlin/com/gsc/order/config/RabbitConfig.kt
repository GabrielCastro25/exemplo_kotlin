package com.gsc.order.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitAdmin
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitConfig {

    @Value("\${ordem.criada.queue}")
    lateinit var queueOrdemCriadaValue: String

    @Value("\${reserva.status.queue}")
    lateinit var queueStatusReservaValue: String

    @Value("\${reserva.status.exchange}")
    lateinit var exchangeStatusReservaValue: String

    @Value("\${ordem.criada.exchange}")
    lateinit var exchangeOrdemCriadaValue: String

    @Bean
    fun connectionFactory(
            @Value("\${spring.rabbitmq.host}") host: String?,
            @Value("\${spring.rabbitmq.port}") port: Int,
            @Value("\${spring.rabbitmq.username}") username: String?,
            @Value("\${spring.rabbitmq.password}") password: String?): CachingConnectionFactory? {
        val connectionFactory = CachingConnectionFactory()
        connectionFactory.host = host!!
        connectionFactory.port = port
        connectionFactory.username = username!!
        connectionFactory.setPassword(password!!)
        return connectionFactory
    }

    @Bean
    fun orderRabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = producerJackson2MessageConverter()
        return rabbitTemplate
    }

    @Bean
    fun producerJackson2MessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    @ConditionalOnProperty(prefix = "spring.rabbitmq.listener", name = ["type"], havingValue = "symple", matchIfMissing = true)
    fun rabbitListenerConainerFactory(): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setMessageConverter(producerJackson2MessageConverter())
        factory.setAutoStartup(true)
        factory.setMissingQueuesFatal(false)
        factory.setStartConsumerMinInterval(3000L)
        factory.setRecoveryInterval(15000L)
        return factory
    }

    @Bean
    fun rabbitAdmin(connectionFactory: ConnectionFactory): RabbitAdmin? {
        val rabbitAdmin = RabbitAdmin(connectionFactory!!)
        rabbitAdmin.isAutoStartup = true
        rabbitAdmin.declareQueue(this.declareQueueOrdem())
        rabbitAdmin.declareQueue(this.declareQueueReserva())
        rabbitAdmin.declareExchange(this.declareExchange())
        rabbitAdmin.declareExchange(this.declareExchangeReserva())
        rabbitAdmin.declareBinding(this.declareBindingOrdem())
        rabbitAdmin.declareBinding(this.declareBindingReserva())
        return rabbitAdmin
    }

    fun declareExchange(): Exchange {
        return ExchangeBuilder.topicExchange(this.exchangeOrdemCriadaValue)
                .durable(true)
                .build()
    }

    fun declareExchangeReserva(): Exchange {
        return ExchangeBuilder.topicExchange(this.exchangeStatusReservaValue)
                .durable(true)
                .build()
    }

    fun declareQueueOrdem(): Queue {
        return QueueBuilder.durable(this.queueOrdemCriadaValue)
                .build()
    }

    fun declareQueueReserva(): Queue {
        return QueueBuilder.durable(this.queueStatusReservaValue)
                .build()
    }

    fun declareBindingOrdem(): Binding {
        return BindingBuilder.bind(this.declareQueueOrdem())
                .to(this.declareExchange())
                .with("")
                .noargs()
    }

    fun declareBindingReserva(): Binding {
        return BindingBuilder.bind(this.declareQueueReserva())
                .to(this.declareExchangeReserva())
                .with("")
                .noargs()
    }
}