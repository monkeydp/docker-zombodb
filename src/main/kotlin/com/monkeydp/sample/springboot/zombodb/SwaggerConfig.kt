package com.monkeydp.sample.springboot.zombodb

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType.SWAGGER_2
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

/**
 * @author iPotato-Work
 * @date 2021/3/18
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
class SwaggerConfig {
    @Bean
    fun docket() =
            Docket(SWAGGER_2)
                    .apiInfo(
                            ApiInfoBuilder()
                                    .title("接口")
                                    .build()
                    )
                    .select()
                    .apis(RequestHandlerSelectors.basePackage(this::class.java.`package`.name))
                    .build()
}
