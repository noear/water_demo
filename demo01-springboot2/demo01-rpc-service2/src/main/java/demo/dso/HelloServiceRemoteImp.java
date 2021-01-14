package demo.dso;

import demo.protocol.HelloService;
import org.springframework.stereotype.Component;

/**
 * 这个Spinrgboot组件，可以原封不动
 *
 * @author noear 2021/1/8 created
 */
@Component
public class HelloServiceRemoteImp implements HelloService {
    @Override
    public String hello() {
        return "remote: hello";
    }
}
