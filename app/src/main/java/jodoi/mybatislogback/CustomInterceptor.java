package jodoi.mybatislogback;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

@Intercepts({
        @Signature(
                type = Executor.class,
                method = "update",
                args = { MappedStatement.class, Object.class }
        )
})
@Slf4j
public class CustomInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (Exception e) {
            log.error("CustomInterceptor intercept", e);
            log.error("CustomInterceptor intercept e.getMessage()" + e.getMessage());
            log.error("CustomInterceptor intercept e.getCause().getMessage()" + e.getCause().getMessage());
            throw new RuntimeException("RuntimeException from CustomInterceptor", new RuntimeException("RuntimeException from CustomInterceptor"));
        }
    }
}
