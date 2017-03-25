package com.gongfu.config.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gongfu.base.RestModel;
import com.gongfu.config.code.StringCode;
import com.gongfu.config.interceptor.constant.HttpConstant;
import com.gongfu.config.interceptor.support.AuthPassport;
import com.gongfu.config.interceptor.support.ClientInfo;
import com.gongfu.config.interceptor.support.enums.Role;
import com.gongfu.model.user.User;
import com.gongfu.util.StringUtils;
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
            AuthPassport authPassport = ((HandlerMethod) handler).getMethodAnnotation(AuthPassport.class);

            // 没有声明需要权限,或者声明不验证权限
            if (authPassport == null || authPassport.validate() == false) {
                return true;
            }

            /**
             * 请求头:
             * x-gongfu-client: {"role":"ADVISER","version":"v1.1.1","deviceId":"pc","token":"dobkvu7ukcmd3mjv9e53d86mio"}
             */
            // 缺少必须的请求头信息
            JSONObject jsonObject = null;
            try {
                jsonObject = JSON.parseObject(request.getHeader(HttpConstant.HTTP_HEADER_CLIENT));
                if (jsonObject == null)
                    throw new Exception();
            } catch (Exception e) {
                RestModel model = RestModel.create().errorMsg("非法请求头或请求头为空[x-gongfu-client]");
                model.responseJson(response);
                return false;
            }

            ClientInfo clientInfo = JSON.parseObject(jsonObject.toString(), ClientInfo.class);

            if (clientInfo.getDeviceId().equals(HttpConstant.DEVICE_PC)) {  //PC过来的请求
                User user = (User) request.getSession().getAttribute(StringCode.USER);
                if (user == null) {
                    RestModel model = RestModel.create().errorMsg(1001, "会话失效或未登录，请重新登录");
                    model.responseJson(response);
                    return false;
                }
            } else if (clientInfo.getDeviceId().equals(HttpConstant.DEVICE_APP)) {//APP过来的请求
                String token = clientInfo.getToken();
                if (StringUtils.isEmpty(token)) {
                    String warning = String.format("token[%s] required!", token);
                    log.warn(warning);
                    RestModel model = RestModel.create().errorMsg(warning);
                    model.responseJson(response);
                    return false;
                }
            } else {
                RestModel model = RestModel.create().errorMsg("非法入侵我可是要报警的");
                model.responseJson(response);
                return false;
            }

            Role role = clientInfo.getRole();
            Role expectedRole = authPassport.role();
            if (expectedRole == Role.ALL) {
                return true;
            }

            //PC请求的时候，要设置用户权限
            if (expectedRole != role) {
                RestModel model = RestModel.create().errorMsg("权限不足");
                model.responseJson(response);
                return false;
            }
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
