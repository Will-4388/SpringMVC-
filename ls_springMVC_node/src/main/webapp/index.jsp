<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020/5/22
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/js/jquery.min.js"></script>
    <script>
        $(function () {
            //绑定点击事件
            $("#btn").click(function () {
                $.ajax({
                    url: "user/testJson",
                    contentType: "application/json;charset=UTF-8",
                    data: '{"accountName":"ls","number":100}',
                    dataType: "json",
                    type: "post",
                    success: function (data) {
                        alert(data);
                        alert(data.accountName + "      " + data.number);
                    }
                });
            });
        });

    </script>
    <style>
        * {
            margin: 0 auto;
        }

        .nine {
            float: left;

        }

        .left {
            float: left;
        }

        .right {
            float: right;
        }

        h1 {
            text-align: center;
        }
    </style>
</head>

<body>
<h1>SpringMVC学习汇总</h1><span>(By:kulipa)</span>
<div class="left">
    <a href="user/start">1.初次启动MVC</a>
    <br><br>
    2.
    <form action="user/BasicParameter" method="post">
        name<input type="text" name="name" value="test" />
        age<input type="text" name="age" value="20" />
        <input type="submit" />
    </form>

    3.
    <form action="user/POJOParameter" method="post">
        id<input type="text" name="id" value="034194214" />
        密码<input type="text" name="password" value="test" />
        <br>
        list<input type="text" name="list[0]" value="test" /><input type="text" name="list[1]" value="test" />
        <br>
        map<input type="text" name="map['one']" value="test" /><input type="text" name="map['two']" value="test" />
        <br>
        list_account<input type="text" name="list_account[0].number" value="1000" /><input type="text"
                                                                                           name="list_account[1].number" value="1000" />
        <br>
        map_account<input type="text" name="map_account[0].number" value="1000" /><input type="text"
                                                                                         name="map_account[1].number" value="1000" />
        <br>
        金额<input type="text" name="money.number" value="1000" />
        <input type="submit" />
    </form>

    4.<a href="user/customize?date=2020-5-20">日期转换(2020-5-20)</a>
    <br><br>
    5.<a href="user/testServletAPI">ServletAPI做参数启动(HttpServletRequest,HttpServletResponse,HttpSession)</a>
    <br><br>
    6.<a href="user/RequestParam?name=will&id=131200">RequestParam注解启动(起别名)</a>
    <br><br>
    7.<a href="user/RequestBody?name=will&id=131200">RequestBody注解启动(get：null)</a>

    <form action="user/RequestBody" method="post">
        <input type="text" name="name" value="value..." />
        post：name=value<input type="submit">
    </form>
    <br>
    <a href="user/PathVaribale/4388">8.PathVaribale注解({id})</a>
    <br><br>
    <p class="nine">9.基于HiddentHttpMethodFilter实现</p>
    <form action="user/HiddentHttpMethodFilter/123" method="post" class="nine">
        <input type="hidden" name="_method" value="POST">
        <input type="submit" value="保存" />
    </form>
    <form action="user/HiddentHttpMethodFilter/123" method="post" class="nine">
        <input type="hidden" name="_method" value="GET">
        <input type="submit" value="查询" />
    </form>
    <form action="user/HiddentHttpMethodFilter/123" method="post" class="nine">
        <input type="hidden" name="_method" value="DELETE">
        <input type="submit" value="删除" />
    </form>
    <form action="user/HiddentHttpMethodFilter/123" method="post" class="nine">
        <input type="hidden" name="_method" value="PUT">
        <input type="submit" value="更新" />
    </form>
</div>
<div class="right">
    <a href="user/RequestHeader">10.RequestHeader注解(请求头)</a>
    <br><br>
    <a href="user/CookieValue">11.CookieValue注解(Cookies)</a>
    <br><br>
    12.<input id="btn" type="button" value="ResponseBody响应json数据">
    <br><br>
    13.文件上传
    <form action="user/upload" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload">
        <input type="submit" value="文件上传">
    </form>
    14.跨服务器方式文件上传
    <form action="user/uploadToFileServer" method="post" enctype="multipart/form-data">
        选择文件：<input type="file" name="upload">
        <input type="submit" value="文件上传">
    </form>
    15.<a href="user/error">SpringMVC异常处理</a>
    <br><br>
    16.<a href="user/interceptor">SpringMVC拦截器</a>
</div>
</body>

</html>