<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>XXX后台管理系统</title>
<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.4.1/themes/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="../../js/jquery-easyui-1.4.1/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="../../css/taotao.css" />

	<link rel="stylesheet" href="../../static/css/bootstrap.css">
	<link rel="stylesheet" href="../../static/css/font-awesome.css">
	<link rel="stylesheet" href="../../static/css/index.css">  <!-- 修改自Bootstrap官方Demon，你可以按自己的喜好制定CSS样式 -->
	<link rel="stylesheet" href="../../static/css/font-change.css">  <!-- 将默认字体从宋体换成微软雅黑（个人比较喜欢微软雅黑，移动端和桌面端显示效果比较接近） -->

	<script type="text/javascript" src="../../static/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../../js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript" src="../../js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="../../js/common.js"></script>
<style type="text/css">
	.content {
		padding: 10px 10px 10px 10px;
	}
	.input{
		line-height:26px;
		border:1px solid #ccc;
		margin-bottom:10px;
		border-radius:10px;
	}
</style>

</head>
<body class="easyui-layout">
	<div id="headRegion" data-options="region:'north',title:'North Title',split:false, collapsible:false, border:false, noheader:true, minWidth:1024"
		 style="padding:0 0 0 0; overflow:hidden; height:70px;right:0px;border-width:0px;background-color: #1b6d85;">

		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" >
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<img src="../../static/images/logo.png" />
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav navbar-right">
				<%--<li><a href="###" onclick="" style="color: black;"> 用户列表</a></li>--%>
				<li><a href="javascript:void(0)" class="update" style="color: black;"> 修改密码</a></li>
				<li><a href="/login/out.do"  style="color: black;"> 退出登录</a></li>
			</ul>
		</div>
	</div>

    <div data-options="region:'west',title:'菜单',split:true" style="width:180px;">
    	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 5px;">
         	<li>
         		<span>用户管理</span>
         		<ul>
	         		<%--<li data-options="attributes:{'url':'/show/add/user.do'}">新增用户</li>--%>
	         		<li data-options="attributes:{'url':'/user/index.do'}">查询用户</li>
					<%--<li data-options="attributes:{'url':'/update/login/password/index.do'}">修改登录密码</li>--%>
					<li data-options="attributes:{'url':'/show/upload/file.do'}">导入数据</li>
	         	</ul>
         	</li>
         	<li>
         		<span>催收订单管理</span>
         		<ul>
	         		<li data-options="attributes:{'url':'/show/order/index.do'}">催收总订单</li>
					<li data-options="attributes:{'url':'/show/await/allot/order.do'}">待分配订单</li>
	         		<li data-options="attributes:{'url':'/show/await/urge/order.do'}">待催收订单</li>
					<li data-options="attributes:{'url':'/show/urge/ing/order.do'}">催收中订单</li>
					<li data-options="attributes:{'url':'/show/urge/success/order.do'}">催收成功订单</li>
					<li data-options="attributes:{'url':'/show/borrow/user/info.do'}">借款用户信息</li>
	         	</ul>
         	</li>
			<li>
				<span>定时任务管理</span>
				<ul>
					<li data-options="attributes:{'url':'/job/info/index.do'}">定时任务查看</li>
				</ul>
			</li>
         </ul>
    </div>
    <div data-options="region:'center',title:''">
    	<div id="tabs" class="easyui-tabs">
		    <div title="首页" style="padding:20px;">
				<h1 class="page-header">
					<i class="fa fa-cog fa-spin"></i>&nbsp;控制台
					<small>&nbsp;&nbsp;&nbsp;欢迎使用风控后台管理系统</small></h1>

				<!-- 载入左侧菜单指向的jsp（或html等）页面内容 -->
				<div id="content">

					<h4>
						<strong>使用指南：</strong><br>
						<br><br>默认页面内容……
					</h4>

				</div>
			</div>
		</div>
    </div>
</body>
<div id="updatePasswordOpen" class="easyui-dialog" title="修改登录密码" style="width:15%;height:23%;"
	 data-options="iconCls:'pag-list',modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,closed:true">
	<span>原密码：</span><input id="ypassword" class="input" type="password" ></br>
	<span>新密码：</span><input id="xpassword" class="input" type="password" ></br>
	<span>确认密码：</span><input id="zpassword" class="input" type="password" ></br>
	<div style="margin-top: 10px; margin-left: 120px;">
		<a href="#" class="easyui-linkbutton" style="margin-right: 20px" id="subUpdate"> 提交 </a>
		<a href="#" class="easyui-linkbutton" onclick="clearForm('#updatePasswordOpen')">重置</a>
	</div>
</div>

<script type="text/javascript">
$(function(){
	$('#menu').tree({
		onClick: function(node){
			if($('#menu').tree("isLeaf",node.target)){
				var tabs = $("#tabs");
				var tab = tabs.tabs("getTab",node.text);
				if(tab){
					tabs.tabs("select",node.text);
				}else{
					tabs.tabs('add',{
					    title:node.text,
					    href: node.attributes.url,
					    closable:true,
					    bodyCls:"content"
					});
				}
			}
		}
	});
    $(function() {
        $(".guizeBtn").click(function() {
            $(".guize").show(300);
            $(".mask").show();
        });
        $(".close").click(function() {
            $(".guize").hide(300);
            $(".mask").hide();
        });
    });

    $(".update").click(function() {
        $('#updatePasswordOpen').dialog("open");
    });

    /*修改密码*/
    //提交表单
    $("#subUpdate").click(function() {

        var ypassword = $("#ypassword").val();
        var xpassword = $("#xpassword").val();
        var zpassword = $("#zpassword").val();

        if (null == ypassword || "" == ypassword ){
            $.messager.alert('提示','原密码不能为空!');
            return;
		} else if ("" == xpassword || null == xpassword ){
            $.messager.alert('提示','新密码不能为空!!');
            return;
		}else if (null == zpassword || "" == zpassword ){
            $.messager.alert('提示','确认密码不能为空!');
            return;
        }

        if (xpassword != zpassword){
            $.messager.alert('提示','两次密码输入密码不一致!');
            return;
        }
        $.ajax({
            url:"/update/login/password.do",
            data: {"ypassword":ypassword,"xpassword":xpassword,"zpassword":zpassword},
            async : true,
            success : function(data) {//返回数据根据结果进行相应的处理

                if (data.code==200){

                    $.messager.alert('提示',data.msg,undefined,function(){

                        window.location.href="/update/login/password.do";
                    });
                }else{
                    $.messager.alert('提示',data.msg);
                }
            }
        });
    });
	/*关闭重置*/
    function clearForm(str){
        $(str).val("");
    }
});
</script>

</html>