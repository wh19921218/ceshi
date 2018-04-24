<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table class="easyui-datagrid" id="urgeIngList" title="催收成功订单列表"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/get/urge/order/list.do?state=30',method:'get',pageSize:30,toolbar:toolbar">
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
            <th data-options="field:'_operate1',width:80,align:'center',formatter:TAOTAO.formatCheckUserInfo">查看用户信息</th>
        </tr>
    </thead>
</table>
<div>
    <a href="#" id="export" class="easyui-linkbutton" data-options="iconCls:'icon-download'" onclick="exportExcel()">导出表格</a>
</div>

<div id="borrowUserInfo" class="easyui-dialog" title="借款人用户信息" style="width:50%;height:80%;padding:10px;"
     data-options="iconCls:'pag-list',modal:true,collapsible:false,minimizable:false,maximizable:false,resizable:false,closed:true">
    <div style="margin-left: 5px;margin-right: 5px;margin-top: 5px;">
        <table style="margin-top: 20px;margin-left:40px;margin-right:40px;">
            <tr style="height: 40px">
                <td style="margin-right: 0">渠道：</td>
                <td><input class="easyui-textbox" id="channel" disabled="disabled" style="height:25px"/></td>
                <td>真实姓名：</td>
                <td><input class="easyui-textbox" id="real_name" disabled="disabled" style="height:25px"/></td>
                <td>手机号：</td>
                <td><input class="easyui-textbox" id="phone" disabled="disabled" style="height:25px"/></td>
            </tr>
            <tr style="height: 40px">
                <td>性         别：</td>
                <td><input class="easyui-textbox" id="sex" disabled="disabled" style="height:25px"/></td>
                <td>年         龄：</td>
                <td><input class="easyui-textbox" id="age" disabled="disabled" style="height:25px"/></td>
                <td>身  份  证  号：</td>
                <td><input class="easyui-textbox" id="id_no" disabled="disabled" style="height:25px"/></td>
            </tr>
            <tr style="height: 40px">
                <td>紧急联系人1姓名：</td>
                <td><input class="easyui-textbox" id="urge_name_one" disabled="disabled" style="height:25px"/></td>
                <td>紧急联系人1关系：</td>
                <td><input class="easyui-textbox" id="urge_relation_one" disabled="disabled" style="height:25px"/></td>
                <td>紧急联系人1电话：</td>
                <td><input class="easyui-textbox" id="urge_phone_one" disabled="disabled" style="height:25px"/></td>
            </tr>
            <tr style="height: 40px">
                <td>紧急联系人2姓名：</td>
                <td><input class="easyui-textbox" id="urge_name_tow" disabled="disabled" style="height:25px"/></td>
                <td>紧急联系人2关系：</td>
                <td><input class="easyui-textbox" id="urge_relation_tow" disabled="disabled" style="height:25px"/></td>
                <td>紧急联系人2电话：</td>
                <td><input class="easyui-textbox" id="urge_phone_tow" disabled="disabled" style="height:25px"/></td>
            </tr>
            <%--借款信息--%>
            <tr style="height: 40px">
                <td>借款金额：</td>
                <td><input class="easyui-textbox" id="amount" disabled="disabled" style="height:25px"/></td>
                <td>借款时间：</td>
                <td><input class="easyui-textbox" id="borrow_time" disabled="disabled" style="height:25px"/></td>
                <td>借款期限：</td>
                <td><input class="easyui-textbox" id="time_limit" disabled="disabled" style="height:25px"/></td>
            </tr>
            <tr style="height: 40px">
                <td>期限单位：</td>
                <td><input class="easyui-textbox" id="unit" disabled="disabled" style="height:25px"/></td>
                <td>预计还款时间：</td>
                <td><input class="easyui-textbox" id="repay_time" disabled="disabled" style="height:25px"/></td>
                <td>逾期天数：</td>
                <td><input class="easyui-textbox" id="penalty_day" disabled="disabled" style="height:25px"/></td>
            </tr>
            <tr style="height: 40px">
                <td>逾期金额：</td>
                <td><input class="easyui-textbox" id="penalty_amout" disabled="disabled" style="height:25px"/></td>
                <td>订单状态：</td>
                <td><input class="easyui-textbox" id="state" disabled="disabled" style="height:25px"/></td>
                <td>逾期等级：</td>
                <td><input class="easyui-textbox" id="level" disabled="disabled" style="height:25px"/></td>
            </tr>
        </table>
    </div>
    <div style="margin-left: 350px;margin-right: 5px;margin-top: 20px;">
        <form id="itemAddForm" class="itemForm" method="post">
            <table cellpadding="5">
                <tr style="height: 40px">
                    <td>催收方式：</td>
                    <td>
                        <select class="easyui-combobox" id="urgeWay" name="urgeWay"  style="width:100px;" data-options="required:true">
                            <option selected="selected" value="">请选择</option>
                            <option value="10">电话</option>
                            <option value="20">邮件</option>
                            <option value="30">短信</option>
                            <option value="40">现场沟通</option>
                            <option value="50">其他</option>
                        </select>

                    </td>
                </tr>
                <tr style="height: 40px">
                    <td>催收结果：</td>
                    <td>
                        <select class="easyui-combobox" id="urgeResult" name="urgeResult"  style="width:100px;" data-options="required:true">
                            <option selected="selected" value="">请选择</option>
                            <option value="20">催收中</option>
                            <option value="30">承诺还款</option>
                            <option value="40">催收成功</option>
                        </select>
                    </td>
                </tr>
                <tr style="height: 40px">
                    <td>承诺还款时间：</td>
                    <td>
                        <input id="promiseTime" type="text" class="easyui-datebox" required="required">
                    </td>
                </tr>
                <tr style="height: 40px">
                    <td>催收备注：</td>
                    <td>
                        <input class="easyui-textbox" id="remark" data-options="multiline:true" value="" style="width:300px;height:100px">
                    </td>
                </tr>
            </table>
            <input type="hidden" id="urgeOrderId"/>
            <input type="hidden" id="orderNo" />
            <input type="hidden" id="urgeUserId" />
        </form>
        <div style="margin-left: 130px;margin-right: 5px;margin-top: 20px;">
            <a href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 50px;" onclick="clearForm()" data-options="iconCls:'icon-no'">重置</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" style="margin-left: 5px;" onclick="submitForm()" data-options="iconCls:'icon-ok'">提交</a>
        </div>
    </div>
</div>
<script>

    /*自动加载*/
    function getSelectionsIds(){
    	var itemList = $("#urgeIngList");
    	var sels = itemList.datagrid("getSelections");
    	var ids = [];
    	for(var i in sels){
    		ids.push(sels[i].id);
    	}
    	ids = ids.join(",");
    	return ids;
    }

    /*点击开始催收按钮*/
    function checkUserInfo(index){
        $('#urgeIngList').datagrid('selectRow',index);// 关键在这里
        var row = $('#urgeIngList').datagrid('getSelected');
        if (row){
            var id = row.id;
            $.ajax({
                url:"/get/borrow/user/info/by/id.do",
                data: {"id":id},
                async : true,
                success : function(data) {//返回数据根据结果进行相应的处理
                    if (data.code==200){

                        $("#borrowUserInfo").dialog({

                            onOpen:function(){
                                $("#channel").textbox("setValue",data.borrowUserInfo.channel);
                                $("#real_name").textbox("setValue",data.borrowUserInfo.realName);
                                $("#phone").textbox("setValue",data.borrowUserInfo.phone);
                                $("#sex").textbox("setValue",data.borrowUserInfo.sex);
                                $("#age").textbox("setValue",data.borrowUserInfo.age);
                                $("#id_no").textbox("setValue",data.borrowUserInfo.idNo);
                                $("#urge_name_one").textbox("setValue",data.borrowUserInfo.urgeNameOne);
                                $("#urge_relation_one").textbox("setValue",data.borrowUserInfo.urgeRelationOne);
                                $("#urge_phone_one").textbox("setValue",data.borrowUserInfo.urgePhoneOne);
                                $("#urge_name_tow").textbox("setValue",data.borrowUserInfo.urgeNameTow);
                                $("#urge_relation_tow").textbox("setValue",data.borrowUserInfo.urgeRelationTow);
                                $("#urge_phone_tow").textbox("setValue",data.borrowUserInfo.urgePhoneTow);

                                $("#amount").textbox("setValue",data.urgeOrderInfo.amount);
                                $("#borrow_time").textbox("setValue",data.urgeOrderInfo.borrowTime);
                                $("#time_limit").textbox("setValue",data.urgeOrderInfo.timeLimit);
                                $("#unit").textbox("setValue",data.urgeOrderInfo.unit);
                                $("#repay_time").textbox("setValue",data.urgeOrderInfo.repayTime);
                                $("#penalty_day").textbox("setValue",data.urgeOrderInfo.penaltyDay);
                                $("#penalty_amout").textbox("setValue",data.urgeOrderInfo.penaltyAmout);
                                $("#state").textbox("setValue",data.urgeOrderInfo.state);
                                $("#level").textbox("setValue",data.urgeOrderInfo.level);
                                $("#urgeOrderId").textbox("setValue",data.urgeOrderInfo.id);
                                $("#orderNo").textbox("setValue",data.urgeOrderInfo.orderNo);
                                $("#urgeUserId").textbox("setValue",data.urgeOrderInfo.urgeUserId);
                            }
                        });
                        $('#borrowUserInfo').dialog("open");
                    }else{
                        $.messager.alert('提示',data.msg,undefined,function(){
                        });
                    }
                }
            });
        }
    }

    /*不能删除*/
    var toolbar = [{}];
    /*重置*/
    function clearForm(){
        $('#itemAddForm').form('reset');
        itemAddEditor.html('');
    };

    /*提交*/
    function submitForm(){
        var urgeWay = $('#urgeWay').combobox('getValue');
        var urgeResult = $('#urgeResult').combobox('getValue');
        var promiseTime= $("#promiseTime").datebox("getValue");
        var remark = $("#remark").val();
        var urgeOrderId = $("#urgeOrderId").val();
        var orderNo = $("#orderNo").val();
        var urgeUserId = $("#urgeUserId").val();

        $.ajax({
            url: "/save/urge/info.do",
            data: {"urgeWay":urgeWay,"urgeResult":urgeResult,"promiseTime":promiseTime,"remark":remark,"urgeOrderId": urgeOrderId, "orderNo": orderNo, "urgeUserId": urgeUserId },
            async: true,
            success: function (data) {//返回数据根据结果进行相应的处理

                alert(data.msg);
            }
        })
    };


</script>