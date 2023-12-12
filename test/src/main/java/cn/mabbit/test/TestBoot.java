package cn.mabbit.test;

import cn.mabbit.mdk4j.core.util.ObjectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

import java.net.InetAddress;

/**
 * <h2></h2>
 *
 * @author 一只枫兔
 * @Date 2023/11/9 10:12
 */
@Slf4j
@SpringBootApplication
public class TestBoot
{
    public static void main(String... args)
            throws Throwable
    {
        ConfigurableEnvironment env =
                SpringApplication
                        .run(TestBoot.class, args)
                        .getEnvironment();

        String hostAddress = InetAddress.getLocalHost().getHostAddress();

        String port = env.getProperty("server.port");
        String appName = env.getProperty("spring.application.name");

        log.info(
                """

                        ----------------------------------------------------------
                        \t(♥◠‿◠)ﾉﾞ  {} runs successfully  ლ(´ڡ`ლ)ﾞ
                        \tDocument: http://{}:{}/doc.html
                        ----------------------------------------------------------
                        """,
                ObjectUtil.DON(appName, "project"),
                hostAddress,
                ObjectUtil.DON(port, "8080")
        );
    }
}