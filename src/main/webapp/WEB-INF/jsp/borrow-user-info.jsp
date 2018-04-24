<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="borrowUserList" title="用户列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/borrow/user/info/page.do',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
        	<th data-options="field:'ck',checkbox:true"></th>
        	<th data-options="field:'id',width:80,align:'center'">用户ID</th>
            <th data-options="field:'channel',width:100,align:'center'">渠道</th>
            <th data-options="field:'realName',width:100,align:'center'">真实姓名</th>
            <th data-options="field:'phone',width:100,align:'center'">手机号</th>
            <th data-options="field:'sex',width:70,align:'center'">性别</th>
            <th data-options="field:'age',width:70,align:'center'">年龄</th>
            <th data-options="field:'idNo',width:150,align:'center'">身份证号</th>
            <th data-options="field:'urgeNameOne',width:130,align:'center'">紧急联系人1姓名</th>
            <th data-options="field:'urgeRelationOne',width:130,align:'center'">紧急联系人1关系</th>
            <th data-options="field:'urgePhoneOne',width:130,align:'center'">紧急联系人1电话</th>
            <th data-options="field:'urgeNameTow',width:130,align:'center'">紧急联系人2姓名</th>
            <th data-options="field:'urgeRelationTow',width:130,align:'center'">紧急联系人2关系</th>
            <th data-options="field:'urgePhoneTow',width:130,align:'center'">紧急联系人2电话</th>
            <th data-options="field:'createTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">创建时间</th>
        </tr>
    </thead>
    <div id="toolbar" style="padding:3px">
        <span>手机号:</span><input id="phone" style="line-height:26px;border:1px solid #ccc">
        <span>渠道:</span><input id="channel" style="line-height:26px;border:1px solid #ccc">
        <a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()">Search</a>
    </div>
</table>
<script>
    function doSearch(){
        $('#borrowUserList').datagrid('load',{

            phone: $.trim($('#phone').val()),
            channel: $.trim($('#channel').val())
        });
    }
    function getSelectionsIds(){
        var itemList = $("#borrowUserList");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for(var i in sels){
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

    var toolbar = [{
    }];

</script>