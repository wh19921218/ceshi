<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<script type="text/javascript" src="../../static/js/jquery-3.2.1.min.js"></script>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>登录界面</title>
</head>

<style>
    .input{
        line-height:22px;
        border:1px solid #ccc;
        margin-bottom:10px;
        border-radius:10px;
    }
    .input2{
        margin-bottom:10px;
        border-radius:10px;
        font-size: 1.2em;
        color: black;
    }
</style>


<body style="background:url(../../static/images/bg-login.jpg) no-repeat; margin-top: 150px;width:100%;height:auto">
<center>
    <div style="margin-left: 40%; position: relative; ">
        <br><br><br><br><br><br>
        <h1 style="color:mediumspringgreen">Login</h1>
        <br>
        <form id="login_from" name="loginForm">
            <table Border="0" style="font-size: 1.3em;color: mediumspringgreen">
                <tr>
                    <td>账号：</td>
                    <td><input id="user_name" class="input" type="text" name="userName"></td>
                </tr>
                <tr >
                    <td></td>
                    <td></td>
                </tr>
                <tr >
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>密码：</td>
                    <td><input id="password" class="input" type="password" name="password">
                    </td>
                </tr>
            </table>
            <input type="submit" id="sub_login" class="input2" value="登录">
        </form>
    </div>
</center>

</body>


<script >

    $(function () {

        $('#sub_login').click(function () {
            var userName = $("#user_name").val();
            var password = $("#password").val();
            $.ajax({

                url:"/user/login.do",
                data: {"userName":userName,"password":password},
                async : true,
                success : function(data) {//返回数据根据结果进行相应的处理
                    var userInfo = data.data;
                    if (data.code==200){
                        window.location.href = data.URL;
                    }else{
                        alert(data.msg);
                    }
                }
            });

        })
    })

</script>




</html>