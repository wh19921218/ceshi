<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
    .input{
        line-height:26px;
        border:1px solid #ccc;
        margin-bottom:10px;
        border-radius:10px;
    }
</style>

<table class="easyui-datagrid" id="sysConfigList" title="系统配置列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/job/info/list.do',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:80,align:'center'">ID</th>
            <th data-options="field:'name',width:100,align:'center'">任务名称</th>
            <th data-options="field:'code',width:100,align:'center'">任务标识</th>
            <th data-options="field:'cycle',width:120,align:'center'">定时任务执行周期</th>
            <th data-options="field:'state',width:70,align:'center',formatter:TAOTAO.formatConfigState">状态</th>
            <th data-options="field:'createTime',width:150,align:'center',formatter:TAOTAO.formatDateTime">创建时间</th>
            <th data-options="field:'updateTime',width:150,align:'center',formatter:TAOTAO.formatDateTime">修改时间</th>
            <th data-options="field:'_operate1',width:80,align:'center',formatter:TAOTAO.formatUpdateConfig">修改</th>
            <th data-options="field:'_operate2',width:80,align:'center',formatter:TAOTAO.formatJobStart">启用</th>
            <th data-options="field:'_operate3',width:80,align:'center',formatter:TAOTAO.formatJobBlocks">停用</th>
        </tr>
    </thead>
    <div id="toolbar" style="padding:3px">
        <span>任务名称：</span><input id="name" class="input" >
        <span>任务标识：</span><input id="code" class="input" >
        <span>状态：</span>
        <select class="easyui-combobox" id="state" name="state"  style="width:100px;" data-options="required:true">
            <option selected="selected" value="-1">请选择</option>
            <option value="0">启用</option>
            <option value="1">停用</option>
        </select>
        <a href="#" class="easyui-linkbutton" style="margin-left: 20px" onclick="doSearch()"> 查询 </a>
        <a href="#" style="margin-left: 10px" class="easyui-linkbutton" onclick="clearForm('#toolbar input')">重置</a>
    </div>
</table>

<div id="addConfig" class="easyui-dialog" title="添加配置信息" style="width:15%;height:30%;"
     data-options="iconCls:'pag-list',modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,closed:true">
    <span>任务名称：</span><input id="name1" class="input" ></br>
    <span>任务标识：</span><input id="code1" class="input" ></br>
    <span>任务服务类：</span><input id="jobService" class="input" ></br>
    <span>执行周期：</span><input id="cycle1" class="input" ></br>
    <span>状态：</span>
    <select class="easyui-combobox" id="state1" name="state"  style="width:100px;" data-options="required:true">
        <option selected="selected" value="-1">请选择</option>
        <option value="0">启用</option>
        <option value="1">禁用</option>
    </select></br></br>

    <div style="margin-top: 10px; margin-left: 120px;">
        <a href="#" class="easyui-linkbutton" style="margin-right: 20px" onclick="submitAdd()"> 提交 </a>
        <a href="#" class="easyui-linkbutton" onclick="clearForm('#addConfig')">重置</a>
    </div>
</div>

<div id="updateConfig" class="easyui-dialog" title="修改配置信息" style="width:15%;height:25%"
     data-options="iconCls:'pag-list',modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,closed:true">
    <input id="id2" type="hidden"/>
    <span>任务名称：</span><input id="name2" class="input" ></br>
    <span>任务标识：</span><input id="code2" class="input" ></br>
    <span>执行周期：</span><input id="cycle2" class="input" ></br>
    <div style="margin-top: 10px; margin-left: 120px;">
        <a href="#" class="easyui-linkbutton" style="margin-right: 20px" onclick="updatesubmit()"> 提交 </a>
        <a href="#" class="easyui-linkbutton" onclick="closeUpdateConfig()">关闭</a>
    </div>
</div>



<script>
    /*查询*/
    function doSearch(){
        $('#sysConfigList').datagrid('load',{
            name : $.trim($('#name').val()),
            code : $.trim($('#code').val()),
            state : $('#state').combobox('getValue')
        });
    }

    /*自动加载*/
    function getSelectionsIds(){
    	var itemList = $("#sysConfigList");
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
            $('#addConfig').dialog("open");

        }
    }];

    //新增 提交
    function submitAdd(){
        var name = $("#name1").val();
        var code = $("#code1").val();
        var jobService = $("#jobService").val();
        var cycle = $('#cycle1').val();
        var state = $('#state1').combobox('getValue');

        if (state == -1){
            $.messager.alert('提示','请选择可用状态！');
            return;
        }
        $.ajax({
            url: "/save/job/info.do",
            data: {"name":name,"code":code,"jobService":jobService,"cycle":cycle,"state":state},
            async: true,
            success: function (data) {//返回数据根据结果进行相应的处理

                $.messager.alert('提示',data.msg,undefined,function(){

                    $('#addConfig').dialog('close');
                    $("#sysConfigList").datagrid("reload");
                });
            }
        })
    };

    /*点击修改按钮*/
    function updateConfig(index) {
        $('#sysConfigList').datagrid('selectRow',index);// 关键在这里
        var row = $('#sysConfigList').datagrid('getSelected');

        if (row){
            var id = row.id;
            var name = row.name;
            var code = row.code;
            var cycle = row.cycle;

            $("#updateConfig").dialog({
                onOpen:function(){
                    document.getElementById('id2').value=id;
                    document.getElementById('name2').value=name;
                    document.getElementById('code2').value=code;
                    document.getElementById('cycle2').value=cycle;
                }
            });
            $('#updateConfig').dialog("open");
        }
    }

    /*点击修改提交按钮*/
    function updatesubmit() {
        var id = $("#id2").val();
        var name = $("#name2").val();
        var code = $("#code2").val();
        var cycle = $('#cycle2').val();

        $.ajax({
            url: "/update/job/info.do",
            data: {"id":id,"name":name,"code":code,"cycle":cycle},
            async: true,
            success: function (data) {//返回数据根据结果进行相应的处理

                $.messager.alert('提示',data.msg,undefined,function(){

                    $('#updateConfig').dialog('close');
                    $("#sysConfigList").datagrid("reload");
                });
            }
        })
    }


    /*点击启动按钮*/
    function JobStart(index){
        $('#sysConfigList').datagrid('selectRow',index);// 关键在这里
        var row = $('#sysConfigList').datagrid('getSelected');
        if (row){
            var id = row.id;
            var code = row.code;
            $.ajax({
                url:"/update/job/state.do",
                data: {"id":id,"state":"0","code":code},
                async : true,
                success : function(data) {//返回数据根据结果进行相应的处理
                     if (data.code==200){
                         $.messager.alert('提示',data.msg,undefined,function(){
                             $("#sysConfigList").datagrid("reload");
                         });
                     }else{
                         $.messager.alert('提示',data.msg,undefined,function(){
                             $("#sysConfigList").datagrid("reload");
                         });
                     }
                }
            });
        }
    };

    /*点击停用按钮*/
    function JobBlock(index){
        $('#sysConfigList').datagrid('selectRow',index);// 关键在这里
        var row = $('#sysConfigList').datagrid('getSelected');
        if (row){
            var id = row.id;
            var code = row.code;
            $.ajax({
                url:"/update/job/state.do",
                data: {"id":id,"state":"1","code":code},
                async : true,
                success : function(data) {//返回数据根据结果进行相应的处理
                    if (data.code==200){
                        $.messager.alert('提示',data.msg,undefined,function(){
                            $("#sysConfigList").datagrid("reload");
                        });
                    }else{
                        $.messager.alert('提示',data.msg,undefined,function(){
                            $("#sysConfigList").datagrid("reload");
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
    /*关闭按钮*/
    function closeUpdateConfig(){
        $('#updateConfig').dialog('close');
    }
</script>