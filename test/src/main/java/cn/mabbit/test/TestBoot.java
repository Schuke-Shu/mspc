package cn.mabbit.test;

import cn.jruyi.core.util.ObjectUtil;
import cn.mabbit.mspc.core.CommonProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @Date 2023/11/9 10:12
 */
@Slf4j
@SpringBootApplication
public class TestBoot
{
    public static void main(String... args)
    {
        post(SpringApplication.run(TestBoot.class, args));
    }

    private static void post(ConfigurableApplicationContext context)
    {
        ConfigurableEnvironment env = context.getEnvironment();

        String name = env.getProperty("spring.application.name");
        CommonProperties common = context.getBean(CommonProperties.class);

        log.info(
                """

                        ----------------------------------------------------------------------
                        \t(♥◠‿◠)ﾉﾞ Application: [{}] runs successfully  ლ(´ڡ`ლ)ﾞ
                        \tDocument: {}://{}:{}/doc.html
                        ----------------------------------------------------------------------
                        """,
                ObjectUtil.DON(name, "-"),
                common.getProtocol(),
                common.getHost(),
                common.getPort()
        );
    }
}