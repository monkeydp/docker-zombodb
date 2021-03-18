package com.monkeydp.sample.springboot.zombodb

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

/**
 * @author iPotato-Work
 * @date 2021/3/18
 */
@Configuration
class JacksonConfig(
        private val builder: Jackson2ObjectMapperBuilder,
) {
    @Bean
    fun objectMapper(): ObjectMapper =
            builder.build<ObjectMapper>()
                    .registerKotlinModule()
}
