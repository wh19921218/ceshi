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
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/sys/config/list.do',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:80,align:'center'">ID</th>
            <th data-options="field:'channel',width:100,align:'center'">渠道名称</th>
            <th data-options="field:'timeLimit',width:100,align:'center'">借款期限</th>
            <th data-options="field:'unit',width:100,align:'center'">期限单位</th>
            <th data-options="field:'penaltyFee',width:100,align:'center'">逾期利率</th>
            <th data-options="field:'penaltyAmountMax',width:90,align:'center'">逾期罚金上限</th>
            <th data-options="field:'state',width:70,align:'center',formatter:TAOTAO.formatConfigState">状态</th>
            <th data-options="field:'createTime',width:150,align:'center',formatter:TAOTAO.formatDateTime">创建时间</th>
            <th data-options="field:'updateTime',width:150,align:'center',formatter:TAOTAO.formatDateTime">修改时间</th>
            <th data-options="field:'_operate1',width:80,align:'center',formatter:TAOTAO.formatUpdateConfig">修改</th>
            <th data-options="field:'_operate2',width:80,align:'center',formatter:TAOTAO.formatConfigStart">启用</th>
            <th data-options="field:'_operate3',width:80,align:'center',formatter:TAOTAO.formatConfigBlocks">停用</th>
        </tr>
    </thead>
    <div id="toolbar" style="padding:3px">
        <span>渠道名称：</span><input id="channel" class="input" >
        <span>借款期限：</span><input id="timeLimit" class="input" >
        <span>期限单位：</span><input id="unit" class="input" >
        <span>逾期利率：</span><input id="penaltyFee" class="input" >
        <span>逾期罚金上限：</span><input id="penaltyAmountMax" class="input" >
        <span>可用状态：</span>
        <select class="easyui-combobox" id="state" name="state"  style="width:100px;" data-options="required:true">
            <option selected="selected" value="-1">请选择</option>
            <option value="0">启用</option>
            <option value="1">禁用</option>
        </select>
        <a href="#" class="easyui-linkbutton" style="margin-left: 20px" onclick="doSearch()"> 查询 </a>
        <a href="#" style="margin-left: 10px" class="easyui-linkbutton" onclick="clearForm('#toolbar input')">重置</a>
    </div>
</table>

<div id="addConfig" class="easyui-dialog" title="添加配置信息" style="width:20%;height:30%;padding:10px;"
     data-options="iconCls:'pag-list',modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,closed:true">
    <span>渠道名称：</span><input id="channel1" class="input" ></br>
    <span>借款期限：</span><input id="timeLimit1" class="input" ></br>
    <span>期限单位：</span>
    <select class="easyui-combobox" id="unit1" name="state"  style="width:100px;" data-options="required:true">
        <option selected="selected" value="-1">请选择</option>
        <option value="0">天</option>
        <option value="1">月</option>
    </select></br></br>
    <span>逾期利率：</span><input id="penaltyFee1" class="input" ></br>
    <span>逾期罚金上限：</span><input id="penaltyAmountMax1" class="input" ></br>
    <span>可用状态：</span>
    <select class="easyui-combobox" id="state1" name="state"  style="width:100px;" data-options="required:true">
        <option selected="selected" value="-1">请选择</option>
        <option value="0">启用</option>
        <option value="1">禁用</option>
    </select></br>

    <div style="margin-top: 10px; margin-left: 120px;">
        <a href="#" class="easyui-linkbutton" style="margin-right: 20px" onclick="submitAdd()"> 提交 </a>
        <a href="#" class="easyui-linkbutton" onclick="clearForm('#addConfig')">重置</a>
    </div>
</div>

<div id="updateConfig" class="easyui-dialog" title="修改配置信息" style="width:20%;height:30%;padding:10px;"
     data-options="iconCls:'pag-list',modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,closed:true">
    <input id="id2" type="hidden"/>
    <span>渠道名称：</span><input id="channel2" class="input" ></br>
    <span>借款期限：</span><input id="timeLimit2" class="input" ></br>
    <span>期限单位：</span><input id="unit2" class="input" ></br>
    <span>逾期利率：</span><input id="penaltyFee2" class="input" ></br>
    <span>逾期罚金上限：</span><input id="penaltyAmountMax2" class="input" ></br>
    </br>

    <div style="margin-top: 10px; margin-left: 120px;">
        <a href="#" class="easyui-linkbutton" style="margin-right: 20px" onclick="doSearch()"> 提交 </a>
        <a href="#" class="easyui-linkbutton" onclick="closeUpdateConfig()">关闭</a>
    </div>
</div>



<script>
    /*查询*/
    function doSearch(){
        $('#sysConfigList').datagrid('load',{
            channel : $.trim($('#channel').val()),
            timeLimit : $.trim($('#timeLimit').val()),
            unit : $.trim($('#unit').val()),
            penaltyFee : $.trim($('#penaltyFee').val()),
            penaltyAmountMax : $.trim($('#penaltyAmountMax').val()),
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

            $("#addConfig").dialog({

                onOpen:function(){
                }
            });
            $('#addConfig').dialog("open");


        	/*$(".tree-title:contains('新增配置信息')").parent().click();*/
        }
    }];

    //新增 提交
    function submitAdd(){
        var channel = $("#channel1").val();
        var timeLimit = $("#timeLimit1").val();
        var unit = $('#unit1').combobox('getValue');
        var penaltyFee = $("#penaltyFee1").val();
        var penaltyAmountMax = $("#penaltyAmountMax1").val();
        var state = $('#state1').combobox('getValue');

        if (unit == -1){
            $.messager.alert('提示','请选择期限单位！');
            return;
        }

        if (state == -1){
            $.messager.alert('提示','请选择可用状态！');
            return;
        }
        $.ajax({
            url: "/save/sys/config.do",
            data: {"channel":channel,"timeLimit":timeLimit,"unit":unit,"penaltyFee":penaltyFee,"penaltyAmountMax": penaltyAmountMax, "state": state },
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
            var channel = row.channel;
            var timeLimit = row.timeLimit;
            var unit = row.unit;
            var penaltyFee = row.penaltyFee;
            var penaltyAmountMax = row.penaltyAmountMax;
            var state = row.state;

            if (unit == 0){
                unit = "天";
            } else if (unit == 1){
                unit = "月";
            } else {
                unit = "未知";
            }

            $("#updateConfig").dialog({
                onOpen:function(){
                    document.getElementById('id2').value=id;
                    document.getElementById('channel2').value=channel;
                    document.getElementById('timeLimit2').value=timeLimit;
                    document.getElementById('unit2').value=unit;
                    document.getElementById('penaltyFee2').value=penaltyFee;
                    document.getElementById('penaltyAmountMax2').value=penaltyAmountMax;
                    document.getElementById('state2').value=state;
                }
            });
            $('#updateConfig').dialog("open");
        }
    }

    /*点击启动按钮*/
    function startConfig(index){
        $('#sysConfigList').datagrid('selectRow',index);// 关键在这里
        var row = $('#sysConfigList').datagrid('getSelected');
        if (row){
            var id = row.id;
            $.ajax({
                url:"/update/sys/config.do",
                data: {"id":id,"state":"0"},
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
    }

    /*点击停用按钮*/
    function blockConfig(index){
        $('#sysConfigList').datagrid('selectRow',index);// 关键在这里
        var row = $('#sysConfigList').datagrid('getSelected');
        if (row){
            var id = row.id;
            $.ajax({
                url:"/update/sys/config.do",
                data: {"id":id,"state":"1"},
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
    }
    /*关闭重置*/
    function clearForm(str){
        $(str).val("");
    }
    /*关闭按钮*/
    function closeUpdateConfig(){
        $('#updateConfig').dialog('close');
    }
</script>