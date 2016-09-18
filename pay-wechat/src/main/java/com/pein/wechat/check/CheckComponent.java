package com.pein.wechat.check;

import com.pein.wechat.utils.SignUtil;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by qiuwei on 2016/9/18.
 */
public class CheckComponent {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(CheckComponent.class);

    /**
     * 检验API返回的数据里面的签名是否合法，避免数据在传输的过程中被第三方篡改
     * @param map API返回的XML数据字符串
     * @return API签名是否合法
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     * @throws IllegalAccessException
     */
    public static boolean checkIsSignValidFromResponseString(Map<String,Object> map, String apiKey) throws ParserConfigurationException, IOException, SAXException, IllegalAccessException {

        log.info("LOG01170: map.toString()");

        String signFromAPIResponse = map.get("sign").toString();
        if(StringUtils.isEmpty(signFromAPIResponse)){
            log.info("LOG01190:API返回的数据签名数据不存在，有可能被第三方篡改!!!");
            return false;
        }
        //清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
        map.put("sign","");
        //将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
        String signForAPIResponse = SignUtil.getSign(map,apiKey);

        if(!signForAPIResponse.equals(signFromAPIResponse)){
            //签名验不过，表示这个API返回的数据有可能已经被篡改了
            log.info("LOG01200:API返回的数据签名验证不通过，有可能被第三方篡改!!!");
            return false;
        }
        log.info("LOG01210: 恭喜，API返回的数据签名验证通过!!!");
        return true;
    }
}
