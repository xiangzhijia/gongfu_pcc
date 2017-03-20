package com.gongfu.config.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2017年1月8日
 *
 * @向治家
 **/
@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

    /**
     * 发起请求,进入拦截器链，运行所有拦截器的preHandle方法，
     * 当preHandle方法返回false时，从当前拦截器往回执行所有拦截器的afterCompletion方法，再退出拦截器链
     */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        log.info("AuthInterceptor→{在请求处理之前进行调用（Controller方法调用之前）}" + request.getRequestURI());

        /**
         * 这是类对象进行检查,此方法返回布尔值，它指示是否该类型cls对象可以被分配到这个类的对象。
         */
        if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {

        }
        return true;
    }

    /**
     * 当preHandle方法全为true时，执行下一个拦截器,直到所有拦截器执行完。再运行被拦截的Controller。
     * 然后进入拦截器链，运行所有拦截器的postHandle方法,完后从最后一个拦截器往回执行所有拦截器的afterCompletion方法.
     * 当有拦截器抛出异常时,会从当前拦截器往回执行所有拦截器的afterCompletion方法
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("AuthInterceptor→{请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）}");
    }

    /**
     * preHandle方法：返回true，映射处理器执行链将继续执行；当返回false时，
     * DispatcherServlet处理器认为拦截器已经处理完了请求，而不继续执行执行链中的其它拦截器和处理器
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("AuthInterceptor→{在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）}");
    }
}
