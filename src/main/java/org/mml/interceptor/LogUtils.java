package org.mml.interceptor;

import org.apache.commons.lang.math.NumberUtils;

/**
 * @Package: org.mml.interceptor
 * @ClassName: LogUtils
 * @Author: maml
 * @CreateTime: 2020/12/21 10:50
 * @Description:
 */
public class LogUtils {
    public static  boolean validateEvent(String log){
        // 服务器时间 | json
        // 1549696569054 | {"cm":{"ln":"-89.2","sv":"V2.0.4","os":"8.2.0","g":"M67B4QYU@gmail.com","nw":"4G","l":"en","vc":"18","hw":"1080*1920","ar":"MX","uid":"u8678","t":"1549679122062","la":"-27.4","md":"sumsung12","vn":"1.1.3","ba":"Sumsung","sr":"Y"},"ap":"weather","et":[]}
        //按照 | 分割，具体根据业务
        String[] split = log.split(">|");
        //判断是否为时间戳+json格式
        if(split.length!=2){
            return false;
        }else if (split[0].length()!=13|| !NumberUtils.isDigits(split[0])){
            return false;
        }else if(!split[1].trim().startsWith("{") || !split[1].trim().endsWith("}")){
            return false;
        }
        return true;
    }
    public static boolean validateStart(String log){
        //{"action":"1","ar":"MX","ba":"HTC","detail":"542","en":"start","entry":"2","extend1":"","g":"S3HQ7LKM@gmail.com","hw":"640*960","l":"en","la":"-43.4","ln":"-98.3","loading_time":"10","md":"HTC5","mid":"993","nw":"WIFI","open_ad_type":"1","os":"8.2.1","sr":"D","sv":"V2.9.0","t":"1559551922019","uid":"993","vc":"0","vn":"1.1.5"}
        if(log==null){
            return false;
        }else if (!log.trim().startsWith("{")||!log.trim().endsWith("}")){
            return false;
        }
        return true;
    }
}
