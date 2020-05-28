package top.undefinded.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.org.apache.regexp.internal.REUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.undefinded.domain.Account;
import top.undefinded.domain.User;
import top.undefinded.exception.SysException;

import javax.crypto.interfaces.PBEKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * @author will
 * @date 2020/5/22
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(path = "/start")
    public String start(){
        System.out.println("初次启动成功");
        return "success";
    }
/*
一、如果是基本类型或者 String 类型：
    要求我们的参数名称必须和控制器中方法的形参名称保持一致。(严格区分大小写)
二、如果是 POJO 类型，或者它的关联对象：
    要求表单中参数名称和 POJO 类的属性名称保持一致。并且控制器方法的参数类型是 POJO 类型。
三、如果是集合类型,有两种方式：
    第一种：
        要求集合类型的请求参数必须在 POJO 中。在表单中请求参数名称要和 POJO 中集合属性名称相同。
        给 List 集合中的元素赋值，使用下标。
        给 Map 集合中的元素赋值，使用键值对。
    第二种：
        接收的请求参数是 json 格式数据。需要借助一个注解实现。
*/
    @RequestMapping(path = "/BasicParameter" ,method = RequestMethod.POST)
    public String parameter(String name,Integer age){
        System.out.println("基本类型和String做参数启动..."+"name:"+name+"   age:"+age+"   ");
        return "success";
    }
    @RequestMapping("/POJOParameter")
    public String parameter(User user){
        System.out.println("POJO做参数启动(带数组、自定义类)..."+user);
        return "success";
    }
/*
自定义类型转换器
    1. 表单提交的任何数据类型全部都是字符串类型，但是后台定义Integer类型，
        数据也可以封装上，说明Spring框架内部会默认进行数据类型转换。
    2. 如果想自定义数据类型转换，可以实现Converter的接口
*/
    @RequestMapping(value = "/customize", method = RequestMethod.GET)
    public String customize(Date date){
        System.out.println("日期转换启动..."+date);
        return "success";
    }

/*
SpringMVC 还支持使用原始 ServletAPI 对象作为控制器方法的参数。支持原始 ServletAPI 对象有：
    HttpServletRequest
    HttpServletResponse
    HttpSession
    java.security.Principal
    Locale
    InputStream
    OutputStream
    Reader
    Writer
我们可以把上述对象，直接写在控制的方法参数中使用。
*/
    @RequestMapping("testServletAPI")
    public String testServletAPI(HttpServletRequest request,
                                 HttpServletResponse response,
                                 HttpSession session){
        System.out.println(request);
        System.out.println(response);
        System.out.println(session);
        System.out.println("ServletAPI做参数启动...");
        return "success";
    }

//常用注解:
    //@RequestParam
        //value：请求参数中的名称。
        //required：请求参数中是否必须提供此参数。默认值：true。表示必须提供，如果不提供将报错。
    @RequestMapping("RequestParam")
    public String RequestParam(@RequestParam("name") String uname,@RequestParam(value = "id" ,required = false) Integer ID){
        System.out.println("RequestParam注解启动...      ID:"+ID+"   name:"+uname);
        return "success";
    }
    //@RequestBody
        //用于获取请求体内容。直接使用得到是 key=value&key=value...结构的数据。
        //get 请求方式不适用。
    //required：是否必须有请求体。默认值是:true。当取值为 true 时,get 请求方式会报错。如果取值为 false，get 请求得到是 null.
    @RequestMapping("RequestBody")
    public String RequestBody(@RequestBody(required = false) String body){
        System.out.println("RequestBody注解启动...   请求体内容："+body);
        return "success";
    }
    //@PathVaribale
        //用于绑定 url 中的占位符。例如：请求 url 中 /delete/{id}，这个{id}就是 url 占位符。
        //url 支持占位符是 spring3.0 之后加入的。是 springmvc 支持 rest 风格 URL 的一个重要标志。
    //value：用于指定 url 中占位符名称。
    //required：是否必须提供占位符。
    @RequestMapping("PathVaribale/{id}")
    public String PathVaribale(@PathVariable("id") String ID){
        System.out.println("PathVariable注解启动...   ID:"+ID);
        return "success";
    }
    //基于HiddentHttpMethodFilter的示例
    //作用：
    //    由于浏览器 form 表单只支持 GET 与 POST 请求，而 DELETE、PUT 等 method 并不支持，Spring3.0 添
    //    加了一个过滤器，可以将浏览器请求改为指定的请求方式，发送给我们的控制器方法，使得支持 GET、POST、PUT
    //    与 DELETE 请求。
    //使用方法：
    //    第一步：在 web.xml 中配置该过滤器。
    //    第二步：请求方式必须使用 post 请求。
    //    第三步：按照要求提供_method 请求参数，该参数的取值就是我们需要的请求方式。
    @RequestMapping(value = "HiddentHttpMethodFilter/{id}" ,method = RequestMethod.POST)
    public String HiddentHttpMethodPost(@PathVariable("id") String ID){
        System.out.println(ID+"资源执行创建操作...");
        return "success";
    }
    @RequestMapping(value = "HiddentHttpMethodFilter/{id}" ,method = RequestMethod.GET)
    public String HiddentHttpMethodGet(@PathVariable("id") String ID){
        System.out.println(ID+"资源执行查询操作...");
        return "success";
    }
    @RequestMapping(value = "HiddentHttpMethodFilter/{id}" ,method = RequestMethod.DELETE)
    public String HiddentHttpMethodDelete(@PathVariable("id") String ID){
        System.out.println(ID+"资源执行删除操作...");
        return "success";
    }
    @RequestMapping(value = "HiddentHttpMethodFilter/{id}" ,method = RequestMethod.PUT)
    public String HiddentHttpMethodPut(@PathVariable("id") String ID){
        System.out.println(ID+"资源执行更新操作...");
        return "success";
    }
    //RequestHeader
    //  用于获取请求消息头。
    //value：提供消息头名称
    //required：是否必须有此消息头
    @RequestMapping("RequestHeader")
    public String RequestHeader(@RequestHeader(value = "Accept-Language",required = false) String requestHeader){
        System.out.println("RequestHeader注解启动...   Accept-Language："+requestHeader);
        return "success";
    }
    //CookieValue
    //  用于把指定 cookie 名称的值传入控制器方法参数。
    //value：指定 cookie 的名称。
    //required：是否必须有此 cookie。
    @RequestMapping("CookieValue")
    public String CookieValue(@CookieValue(value = "JSESSIONID",required = false) String cookieValue){
        System.out.println("CookieValue注解启动...   JSESSIONID："+cookieValue);
        return "success";
    }
    //ModelAttribute
    //  该注解是 SpringMVC4.3 版本以后新加入的。它可以用于修饰方法和参数。
    //  出现在方法上，表示当前方法会在控制器的方法执行之前，先执行。它可以修饰没有返回值的方法，也可以修饰有具体返回值的方法。
    //  出现在参数上，获取指定的数据给参数赋值。
    //value：用于获取数据的 key。key 可以是 POJO 的属性名称，也可以是 map 结构的 key。
    /*
     *  应用场景：
     *     当表单提交数据不是完整的实体类数据时，保证没有提交数据的字段使用数据库对象原来的数据。
     *  例如:
     *     我们在编辑一个用户时，用户有一个创建信息字段，该字段的值是不允许被修改的。在提交表单数
     *     据是肯定没有此字段的内容，一旦更新会把该字段内容置为 null，此时就可以使用此注解解决问题。
    */


    //SessionAttribute
    //  用于多次执行控制器方法间的参数共享。
    //value：用于指定存入的属性名称
    //type：用于指定存入的数据类型。






//ResponseBody响应json数据
    @RequestMapping("testJson")
    public @ResponseBody Account testJson(@RequestBody Account account){
        System.out.println("ResponseBody响应json数据...    "+account);
        return account;
    }

//SpringMVC实现文件上传
    @RequestMapping("upload")
    public String upload(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("SpringMVC方式的文件上传...");
    // 先获取到要上传的文件目录
        String path = request.getSession().getServletContext().getRealPath("/file");
    // 创建File对象，一会向该路径下上传文件
        File file = new File(path);
    // 判断路径是否存在，如果不存在，创建该路径
        if(!file.exists()) {
            file.mkdirs();
        }
    // 获取到上传文件的名称
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    // 把文件的名称唯一化
        filename = "["+filename+"]"+uuid+filename;
    // 上传文件(自动删除缓存文件）
        upload.transferTo(new File(file,filename));
        return "success";
    }
/*  传统方法实现文件上传
    @RequestMapping(value="/fileupload")
    public String fileupload(HttpServletRequest request) throws Exception {
    // 先获取到要上传的文件目录
    String path = request.getSession().getServletContext().getRealPath("/uploads");
    // 创建File对象，一会向该路径下上传文件
    File file = new File(path);
    // 判断路径是否存在，如果不存在，创建该路径
    if(!file.exists()) {
    file.mkdirs();
    }
    // 创建磁盘文件项工厂
    DiskFileItemFactory factory = new DiskFileItemFactory();
    ServletFileUpload fileUpload = new ServletFileUpload(factory);
    // 解析request对象
    List<FileItem> list = fileUpload.parseRequest(request);
    // 遍历
    for (FileItem fileItem : list) {
    // 判断文件项是普通字段，还是上传的文件
    if(fileItem.isFormField()) {
    }else {
    // 上传文件项
    2. SpringMVC传统方式文件上传
    1. SpringMVC框架提供了MultipartFile对象，该对象表示上传的文件，要求变量名称必须和表单file标签的
    name属性名称相同。
    2. 代码如下
    3. 配置文件解析器对象
    // 获取到上传文件的名称
    String filename = fileItem.getName();
    // 上传文件
    fileItem.write(new File(file, filename));
    // 删除临时文件
    fileItem.delete();
    }
    }
    return "success";
    }
*/
// SpringMVC跨服务器方式文件上传
    @RequestMapping(value="uploadToFileServer")
    public String fileupload3(MultipartFile upload) throws Exception {
        System.out.println("SpringMVC跨服务器方式的文件上传...");
    // 定义图片服务器的请求路径
        String path = "http://localhost:9090/fileServer/uploads/";
    // 获取到上传文件的名称
        String filename = upload.getOriginalFilename();
        String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    // 把文件的名称唯一化
        filename = uuid+filename;
    // 向图片服务器上传文件
    // 创建客户端对象
        Client client = Client.create();
    // 连接图片服务器
        WebResource webResource = client.resource(path+filename);
    // 上传文件
        webResource.put(upload.getBytes());
        return "success";
    }

//SpringMVC的异常处理
    @RequestMapping("error")
    public String error() throws Exception {
        System.out.println("异常处理启动...");

        try {
            int killSelf = 1/0;
        } catch (Exception e) {
            //打印异常信息
            e.printStackTrace();
            //抛出自定义异常信息
            throw new SysException("肥肠抱歉，访问出错啦...");
        }
        return "success";
    }
//SpringMVC框架中的拦截器
/*  1. SpringMVC框架中的拦截器用于对处理器进行预处理和后处理的技术。
    2. 可以定义拦截器链，连接器链就是将拦截器按着一定的顺序结成一条链，在访问被拦截的方法时，拦截器链中的拦截器会按着定义的顺序执行。
    3. 拦截器和过滤器的功能比较类似，有区别:
        (1). 过滤器是Servlet规范的一部分，任何框架都可以使用过滤器技术。
        (2). 拦截器是SpringMVC框架独有的。
        (3). 过滤器配置了/*，可以拦截任何资源。
        (4). 拦截器只会对控制器(controller)中的方法进行拦截。
    4. 拦截器也是AOP思想的一种实现方式
    5. 想要自定义拦截器，需要实现HandlerInterceptor接口。*/
    @RequestMapping("interceptor")
    public String interceptor(){
        System.out.println("拦截器启动...");
        return "success";
    }
}
