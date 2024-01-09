//package cn.mabbit.test.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.License;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * <h2>Swagger在线文档配置</h2>
// *
// * @Date 2023-09-04 0:24
// */
//@Configuration
//public class SwaggerConfig
//{
//    @Bean
//    public OpenAPI customOpenAPI()
//    {
//        return
//                new OpenAPI()
//                        .info(
//                                new Info()
//                                        .title("测试项目")
//                                        .contact(
//                                                new Contact()
//                                                        .name("测试人")
//                                                        .email("测试人的邮箱")
//                                                        .url("测试人的联系地址")
//                                        )
//                                        .version("项目版本号")
//                                        .description("spring-common 测试")
//                                        .termsOfService("xxx")
//                                        .license(
//                                                new License()
//                                                        .name("联系人姓名")
//                                                        .url("联系地址")
//                                        )
//                        );
//    }
//}