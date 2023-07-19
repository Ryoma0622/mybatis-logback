package jodoi.mybatislogback;


import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.extern.slf4j.Slf4j;
import ch.qos.logback.classic.Level;


@Slf4j
public class MyBatisExceptionFilter extends Filter<ILoggingEvent> {
    @Override
    public FilterReply decide(ILoggingEvent event) {
//        if (event.getLevel() == Level.ERROR && event.getThrowableProxy() != null && event.getThrowableProxy().getClassName() != null && event.getThrowableProxy().getClassName().equals("org.apache.ibatis.exceptions.PersistenceException")) {
//            System.out.println(event.getThrowableProxy().getClassName());
//            return FilterReply.DENY;
//        };
        return FilterReply.ACCEPT;
    }
}
