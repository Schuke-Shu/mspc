package cn.mabbit.test;

import cn.jruyi.core.util.ObjectUtil;
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
        String url = env.getProperty("common.module.url");
        if (url == null) url = env.getProperty("common.project.url");

        log.info(
                """

                        ----------------------------------------------------------------------
                        \t(♥◠‿◠)ﾉﾞ Application: [{}] runs successfully  ლ(´ڡ`ლ)ﾞ
                        \tDocument: {}/doc.html
                        ----------------------------------------------------------------------
                        """,
                ObjectUtil.DON(name, "-"),
                url
        );
    }
}