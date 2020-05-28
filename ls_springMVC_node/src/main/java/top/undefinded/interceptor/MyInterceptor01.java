package top.undefinded.interceptor;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author will
 * @date 2020/5/24
 */
/*
* HandlerInterceptor接口中的方法
    1. preHandle方法是controller方法执行前拦截的方法
        (1). 可以使用request或者response跳转到指定的页面
        (2). return true放行，执行下一个拦截器，如果没有拦截器，执行controller中的方法。
        (3). return false不放行，不会执行controller中的方法。
    2. postHandle是controller方法执行后执行的方法，在JSP视图执行前。
        (1). 可以使用request或者response跳转到指定的页面
        (2). 如果指定了跳转的页面，那么controller方法跳转的页面将不会显示。
    3. postHandle方法是在JSP执行后执行
        (1). request或者response不能再跳转页面了
*/
public class MyInterceptor01 implements HandlerInterceptor {
    //preHandle方法是controller方法执行前拦截的方法
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        System.out.println("01前拦截方法执行...");
        return true;
    }


    //postHandle是controller方法执行后执行的方法，在JSP视图执行前。
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("01后拦截方法执行...");
    }


    //postHandle方法是在JSP执行后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("01最终方法执行...");
    }
}
