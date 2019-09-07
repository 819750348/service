<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html"; charset="UTF-8">
    <title>评估系统-评估管理添加</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
    <script type="text/javascript">
        function get() {
            var params={
                name:"123"
            }
            $.post({
                url:"./v1/service",
                contentType: "application/json",
                data:Json.stringify(params),
                success: function (result) {

                }
            })

        }
    </script>

    <style></style>
</head>
<body><h1>测试restful请求</h1>
<button onclick="get()">
    <p>点击测试</p>
</button>
</body>
</html>