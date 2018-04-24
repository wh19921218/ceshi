<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="urgeSuccessList" title="催收成功订单列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/get/urge/order/list.do?state=50',method:'get',pageSize:30,toolbar:toolbar">
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
</table>
<div id="itemEditWindow" class="easyui-window" title="编辑商品" data-options="modal:true,closed:true,iconCls:'icon-save',href:'/rest/page/item-edit'" style="width:80%;height:80%;padding:10px;">
</div>
<script>

    function getSelectionsIds(){
    	var itemList = $("#urgeSuccessList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }
    
    var toolbar = [{
        /*text:'分配全部订单',
        iconCls:'icon-reload',
        handler:function(){

            $.ajax({
                url:"/borrow/repay/urge/allotUser.do",
                data: {},
                async : true,
                success : function(data) {//返回数据根据结果进行相应的处理

                    if (data.code==200){
                        $.messager.alert('提示',data.msg,undefined,function(){
                            $("#urgeSuccessList").datagrid("reload");
                        });
                    }else{
                        $.messager.alert('提示',data.msg,undefined,function(){
                            $("#urgeSuccessList").datagrid("reload");
                        });
                    }

                }
            });
        }*/
    }];

</script>