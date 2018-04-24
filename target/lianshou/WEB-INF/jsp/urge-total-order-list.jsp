<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="urgeOrderList" title="催收订单列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/get/urge/order/list.do',method:'get',pageSize:30,toolbar:toolbar">
    <thead>
        <tr>
            <th data-options="field:'ck',checkbox:true"></th>
            <th data-options="field:'id',width:60">订单id</th>
            <th data-options="field:'urgeName',width:70,align:'center'">催收人姓名</th>
            <th data-options="field:'borrowName',width:70,align:'center'">借款人姓名</th>
            <th data-options="field:'channel',width:70,align:'center'">渠道</th>
            <th data-options="field:'orderNo',width:120,align:'center'">订单编号</th>
            <th data-options="field:'phone',width:100,align:'center'">借款人手机号</th>
            <th data-options="field:'amount',width:70,align:'center'">借款金额</th>
            <th data-options="field:'borrowTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">借款时间</th>
            <th data-options="field:'timeLimit',width:70,align:'center'">借款期限</th>
            <th data-options="field:'unit',width:70,align:'center',formatter:TAOTAO.formatBorrowUnit">期限单位</th>
            <th data-options="field:'repayTime',width:130,align:'center',formatter:TAOTAO.formatDateTime">预计还款时间</th>
            <th data-options="field:'penaltyDay',width:60,align:'center'">逾期天数</th>
            <th data-options="field:'penaltyAmout',width:70,align:'center'">逾期金额</th>
            <th data-options="field:'state',width:60,align:'center',formatter:TAOTAO.formatUrgeOrderStatus">订单状态</th>
            <th data-options="field:'level',width:60,align:'center'">逾期等级</th>
        </tr>
    </thead>
    <div id="toolbar" style="padding:3px">
        <span>订单id：</span><input id="id" style="line-height:15px;border:1px solid #ccc">
        <span>催收人姓名：</span><input id="urgeName" style="line-height:15px;border:1px solid #ccc">
        <span>借款人姓名：</span><input id="borrowName" style="line-height:15px;border:1px solid #ccc">
        <span>渠道：</span><input id="channel" style="line-height:15px;border:1px solid #ccc">
        <span>订单编号：</span><input id="orderNo" style="line-height:15px;border:1px solid #ccc">
        <span>借款人手机号：</span><input id="phone" style="line-height:15px;border:1px solid #ccc">
        <span>借款期限：</span><input id="timeLimit" style="line-height:15px;border:1px solid #ccc">
        <span>逾期天数：</span><input id="penaltyDay" style="line-height:15px;border:1px solid #ccc"> <br>
        <span>订单状态：</span>
        <select class="easyui-combobox" id="state" name="state"  style="width:100px;" data-options="required:true">
            <option selected="selected" value="">请选择</option>
            <option value="10">未分配</option>
            <option value="20">待催收</option>
            <option value="30">催收中</option>
            <option value="40">承诺还款</option>
            <option value="50">催收成功</option>
            <option value="60">坏账</option>
        </select>
        <span>逾期等级：</span>
        <select class="easyui-combobox" id="level" name="level"  style="width:100px;" data-options="required:true">
            <option selected="selected" value="">请选择</option>
            <option value="M1">M1</option>
            <option value="M2">M2</option>
            <option value="M3">M3</option>
        </select>
        <a href="#" class="easyui-linkbutton" plain="true" onclick="doSearch()"> 查询 </a>
        <%--<a href="javascript:void(0)" class="easyui-linkbutton" onclick="clearForm()">重置</a>--%>
        <a href="/export/excel.do" id="export" class="easyui-linkbutton" data-options="iconCls:'icon-download'">导出表格</a>
    </div>
</table>
<script>

    function getSelectionsIds(){
    	var itemList = $("#urgeOrderList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }

    /*查询*/
    function doSearch(){
        $('#urgeOrderList').datagrid('load',{

            id : $.trim($('#id').val()),
            urgeName : $.trim($('#urgeName').val()),
            borrowName : $.trim($('#borrowName').val()),
            channel : $.trim($('#channel').val()),
            orderNo : $.trim($('#orderNo').val()),
            phone : $.trim($('#phone').val()),
            timeLimit : $.trim($('#timeLimit').val()),
            penaltyDay : $.trim($('#penaltyDay').val()),
            state : $('#state').combobox('getValue'),
            level : $('#level').combobox('getValue')
        });
    }

    var toolbar = [{
        /*text:'新增',
        iconCls:'icon-add',
        handler:function(){
        	$(".tree-title:contains('新增用户')").parent().click();
        }
    },{
        text:'删除',
        iconCls:'icon-cancel',
        handler:function(){
        	var ids = getSelectionsIds();
        	if(ids.length == 0){
        		$.messager.alert('提示','未选中商品!');
        		return ;
        	}
        	$.messager.confirm('确认','确定删除ID为 '+ids+' 的商品吗？',function(r){
        	    if (r){
        	    	var params = {"ids":ids};
                	$.post("/rest/item/delete",params, function(data){
            			if(data.status == 200){
            				$.messager.alert('提示','删除商品成功!',undefined,function(){
            					$("#itemList").datagrid("reload");
            				});
            			}
            		});
        	    }
        	});
        }*/
    }];

</script>