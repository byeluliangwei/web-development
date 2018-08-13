package cn.luliangwei.web.development.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cn.luliangwei.web.development.beans.User;

@RestController
public class TestController {

    public static final Logger LOG = LoggerFactory.getLogger(TestController.class);
    
    @PostMapping("/test/body")
    public String updateRequestBody(@RequestBody User body) {
        LOG.debug("得到修改后的请求体，并返回值");
//        try {
//            ServletInputStream in = request.getInputStream();
//            StringBuilder sb = new StringBuilder();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//            String line = null;
//            while((line =reader.readLine()) != null) {
//                sb.append(line);
//            }
//            return sb.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        String result = "修改后：" + body.getName() + "：" + body.getAge();
        return result;
    }
}
