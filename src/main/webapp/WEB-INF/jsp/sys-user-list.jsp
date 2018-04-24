<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="sysUserList" title="用户列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/sys/user/list.do',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:80,align:'center'">用户ID</th>
            <th data-options="field:'userName',width:100,align:'center'">用户名</th>
            <th data-options="field:'realName',width:100,align:'center'">真实姓名</th>
            <th data-options="field:'phone',width:100,align:'center'">手机号</th>
            <th data-options="field:'userType',width:70,align:'center',formatter:TAOTAO.formatUserType">用户类型</th>
            <th data-options="field:'state',width:70,align:'center',formatter:TAOTAO.formatUserState">用户状态</th>
            <th data-options="field:'createTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建时间</th>
            <th data-options="field:'updateTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">修改时间</th>
            <th data-options="field:'_operate1',width:80,align:'center',formatter:TAOTAO.formatStart">启用</th>
            <th data-options="field:'_operate2',width:80,align:'center',formatter:TAOTAO.formatBlocks">停用</th>
        </tr>
    </thead>
    <div id="toolbar" style="padding:3px">
        <span>手机号：</span><input id="phone" class="input">
        <span>用户类型：</span>
        <select class="easyui-combobox" id="userType" name="userType"  style="width:100px;" data-options="required:true">
            <option selected="selected" value="">请选择</option>
            <option value="0">管理员</option>
            <option value="1">催收员</option>
        </select>
        <span>用户状态：</span>
        <select class="easyui-combobox" id="state" name="state"  style="width:100px;" data-options="required:true">
            <option selected="selected" value="">请选择</option>
            <option value="0">启用</option>
            <option value="1">禁用</option>
        </select>
        <a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()"> 查询 </a>
        <a href="#" class="easyui-linkbutton" onclick="clearForm('#toolbar input')"> 重置 </a>
    </div>
</table>

<div id="addUser" class="easyui-dialog" title="添加用户" style="width:15%;height:31%;"
     data-options="iconCls:'pag-list',modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,closed:true">
    <span>用户名：</span><input id="userName" class="input" ></br>
    <span>真实姓名：</span><input id="realName" class="input" ></br>
    <span>登录密码：</span><input id="password" class="input" ></br>
    <span>手机号：</span><input id="phone1" class="input" onblur="check(this)" ></br>
    <span>用户类型：</span>
    <select class="easyui-combobox" id="userType1" name="userType"  style="width:100px;" data-options="required:true">
        <option selected="selected" value="-1">请选择</option>
        <option value="0">管理员</option>
        <option value="1">催收员</option>
    </select></br></br>

    <div style="margin-top: 10px; margin-left: 120px;">
        <a href="#" class="easyui-linkbutton" style="margin-right: 20px" id="subAdd"> 提交 </a>
        <a href="#" class="easyui-linkbutton" onclick="clearForm('#addUser input')">重置</a>
    </div>
</div>

<script>

    /*查询*/
    function doSearch(){
        $('#sysUserList').datagrid('load',{
            phone : $.trim($('#phone').val()),
            userType : $('#userType').combobox('getValue'),
            state : $('#state').combobox('getValue')
        });
    }

    /*自动加载*/
    function getSelectionsIds(){
    	var itemList = $("#sysUserList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        text:'新增',
        iconCls:'icon-add',
        handler:function(){
            $('#addUser').dialog("open");
        }
    }];
    function check(temp){
        //var re = ^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$
        if(!(/^1[3|4|5|8|7][0-9]\d{4,8}$/.test(temp.value)) || temp.value.length < 11){
            $.messager.alert('提示','手机号码格式不正确，请重新输入!');
            return;
        }
    }
    //提交新增表单
    $("#subAdd").click(function() {
    //function submitForm(){
        //有效性验证
        if(!$('#itemAddForm').form('validate')){
            $.messager.alert('提示','提交信息有误，请检查后再提交!');
            return ;
        }
        var userName = $("#userName").val();
        var realName = $("#realName").val();
        var password = $("#password").val();
        var phone = $("#phone1").val();
        var userType = $('#userType1').combobox('getValue');

        if (userType == -1){
            $.messager.alert('提示','请选择用户类型!');
            return ;
        }
        $.ajax({

            url:"/save/user.do",
            data: {"userName":userName,"realName":realName,"password":password,"phone":phone,"userType":userType},
            async : true,
            success : function(data) {//返回数据根据结果进行相应的处理

                $.messager.alert('提示',data.msg);
            }
        });
    });


    /*点击启动用户按钮*/
    function startUser(index){
        $('#sysUserList').datagrid('selectRow',index);// 关键在这里
        var row = $('#sysUserList').datagrid('getSelected');
        if (row){
            var id = row.id;
            $.ajax({
                url:"/update/user/state.do",
                data: {"id":id,"state":"0"},
                async : true,
                success : function(data) {//返回数据根据结果进行相应的处理
                     if (data.code==200){
                         $.messager.alert('提示',data.msg,undefined,function(){
                             $("#sysUserList").datagrid("reload");
                         });
                     }else{
                         $.messager.alert('提示',data.msg,undefined,function(){
                             $("#sysUserList").datagrid("reload");
                         });
                     }
                }
            });
        }
    }

    /*点击停用用户按钮*/
    function blockUser(index){
        $('#sysUserList').datagrid('selectRow',index);// 关键在这里
        var row = $('#sysUserList').datagrid('getSelected');
        if (row){
            var id = row.id;
            $.ajax({
                url:"/update/user/state.do",
                data: {"id":id,"state":"1"},
                async : true,
                success : function(data) {//返回数据根据结果进行相应的处理
                    if (data.code==200){
                        $.messager.alert('提示',data.msg,undefined,function(){
                            $("#sysUserList").datagrid("reload");
                        });
                    }else{
                        $.messager.alert('提示',data.msg,undefined,function(){
                            $("#sysUserList").datagrid("reload");
                        });
                    }
                }
            });
        }
    };
    /*关闭重置*/
    function clearForm(str){
        $(str).val("");
    }
</script>