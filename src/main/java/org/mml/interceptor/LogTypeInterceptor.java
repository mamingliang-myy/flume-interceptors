package org.mml.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Package: org.mml.interceptor
 * @ClassName: LogTypeInterceptor
 * @Author: maml
 * @CreateTime: 2020/12/21 12:16
 * @Description:
 */
public class LogTypeInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        //获取body
        String body = new String(event.getBody(), Charset.forName("UTF-8"));
        //获取header
        Map<String, String> headers = event.getHeaders();
        if(body.contains("start")){
            headers.put("topic","topic-start");
        }else {
            headers.put("topic","topic-end");
        }
        return event;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        ArrayList<Event> interceptors = new ArrayList<>();
        for (Event event : list) {
            if(intercept(event)!=null){
                interceptors.add(intercept(event));
            }
        }
        return interceptors;
    }

    @Override
    public void close() {

    }
    public static class Builder implements Interceptor.Builder{

        @Override
        public Interceptor build() {
            return new LogTypeInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
