package com.pein.wechat.controller;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import com.pein.common.request.wechat.WechatScanPayRequest;
import com.pein.wechat.ApplicationContext;
import com.pein.wechat.utils.ClientTemplateLoader;
import com.pein.wechat.utils.MyCustomProcessor;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Created by qiuwei on 2016/9/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ActiveProfiles("unit")
@SpringApplicationConfiguration(classes = {ApplicationContext.class})
@WebIntegrationTest(randomPort = true)
public class ScanPayControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    /**
     * 验证输出包括system.out和log日志
     * 必须使用@Rule注解
     */
    @Rule
    public OutputCapture outputCapture = new OutputCapture();

    private RestTemplate restTemplate;

    private String url = "localhost:8084/pay-wechat/unified_pay";

    @Before
    public void setUp() {
        FixtureFactoryLoader.loadTemplates("com.pein.wechat.utils");
        restTemplate = new RestTemplate();
    }

    @Test
    public void testUnifiedPay() throws Exception {
        WechatScanPayRequest wechatScanPayRequest = Fixture.from(WechatScanPayRequest.class)
                .uses(new MyCustomProcessor())
                .gimme(ClientTemplateLoader.TEMPLATE);

        Assertions.assertThat(wechatScanPayRequest.getAppid()).isEqualTo("wx678ad9de0bf9d684");

        String response = restTemplate.postForObject(url, wechatScanPayRequest, String.class);

        Assertions.assertThat(response).isNotEmpty();

    }
}