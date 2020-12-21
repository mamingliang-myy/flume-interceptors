package org.mml.interceptor;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @Package: org.mml
 * @ClassName: EtlInterceptor
 * @Author: maml
 * @CreateTime: 2020/12/21 10:30
 * @Description:
 */
public class EtlInterceptor implements Interceptor {
    @Override
    public void initialize() {

    }

    @Override
    public Event intercept(Event event) {
        String body = new String(event.getBody(), Charset.forName("UTF-8"));
        if(body.contains("start")){
            if (LogUtils.validateStart(body)) {
                return event;
            }
        }else{
            if(LogUtils.validateEvent(body)){
                return event;
            }
        }
        return null;
    }

    @Override
    public List<Event> intercept(List<Event> list) {
        ArrayList<Event> interceptors = new ArrayList<>();
        for (Event event : list) {
            if(intercept(event)!=null){
                interceptors.add(event);
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
            return new EtlInterceptor();
        }

        @Override
        public void configure(Context context) {

        }
    }
}
