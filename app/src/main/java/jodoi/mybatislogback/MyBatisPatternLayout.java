package jodoi.mybatislogback;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyBatisPatternLayout extends PatternLayout {
    @Override
    public String doLayout(ILoggingEvent event) {
        if (!isPersistenceExceptionIncluded(event)) {
            String message = super.doLayout(event);

            // org.apache.ibatis.exceptions.PersistenceException:
            //### Error updating database.  Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect datetime value: 'thJCQDR4kFqu2' for column 'created_at' at row 1
            //### The error may exist in jodoi/mybatislogback/mapper/UserMapper.java (best guess)
            //### The error may involve jodoi.mybatislogback.mapper.UserMapper.failedInsert-Inline
            //### The error occurred while setting parameters
            //### SQL: insert into user (username, email, password, created_at) values ('username-1', 'email-1', 'password-1', encrypt('hogehoge', ?))
            //### Cause: com.mysql.cj.jdbc.exceptions.MysqlDataTruncation: Data truncation: Incorrect datetime value: 'thJCQDR4kFqu2' for column 'created_at' at row 1
            Pattern pattern = Pattern.compile("SQL:.*", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(message);
            // Replace all groups with asterisks
            message = matcher.replaceAll("******\n");
            return message;
        };

        return super.doLayout(event);
    }

    private boolean isPersistenceExceptionIncluded(ILoggingEvent event) {
        // Check whether the event has PersistenceException as a cause by checking recursively
        boolean result = false;
        IThrowableProxy throwableProxy = event.getThrowableProxy();
        while (throwableProxy != null) {
            if (throwableProxy.getClassName().equals("org.apache.ibatis.exceptions.PersistenceException")) {
                result = true;
                break;
            }
            throwableProxy = throwableProxy.getCause();
        }
        return result;
    }
}
