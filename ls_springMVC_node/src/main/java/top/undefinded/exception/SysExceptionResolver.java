package top.undefinded.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author will
 * @date 2020/5/24
 */
public class SysExceptionResolver implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,HttpServletResponse response,
                                         Object o,Exception e) {
        System.out.println("SysExceptionResolver执行...");
        //获取异常对象
        SysException se = null;
        if(e instanceof SysException){
            se = (SysException) e;
        }else {
            se = new SysException("服务器正在维护呐...");
        }
        //创建ModelAndView对象
        ModelAndView view = new ModelAndView();
        //设置回传键值对
        view.addObject("errorMsg",se.getMessage());
        //设置跳转页面
        view.setViewName("error");
        return view;
    }
}
