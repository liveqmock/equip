<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
$(function() {
	$('#dealsign_Form').form({
		url : '${pageContext.request.contextPath}/selectAction!dealsign.action?username=${sessionScope.user.username}',
		onSubmit:function(param){
			var rows = $('#dealsign_datagrid').datagrid('getChecked'); 
			var ids = [];
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			param.ids = ids;    
			return $(this).form('validate');
		},
		success : function(r) {
			var obj = jQuery.parseJSON(r);
			if (obj.success) {
				$('#dealsign_Dialog').dialog('close');
				$('#dealsign_datagrid').datagrid('load');
				$('#dealsign_datagrid').datagrid('unselectAll');
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}else{
				$.messager.show({
					title : '提示',
					msg : r.msg
				});
			}
		}
	});
	
	$('#dealsign_datagrid').datagrid({
		url : '${pageContext.request.contextPath}/selectAction!searchForSign.action',
		fit : true,
		fitColumns : true,
		border : false,
		pagination : true,
		idField : 'id',
		singleSelect : true,
		pageSize : 10,
		rownumbers:true,
		pageList : [ 10, 20, 30, 40, 50 ],
		sortName : 'selectName',
		sortOrder : 'asc',
		/*pagePosition : 'both',*/
		checkOnSelect : false,
		selectOnCheck : false,
		frozenColumns : [ [ {
			field : 'id',
			title : '编号',
			width : 100,
			checkbox : true
		}, {
			field : 'selectName',
			title : '型号',
			width : 100,
			sortable : true
		} ] ],
		columns : [ [ {
			field : 'selectYear',
			title : '年份',
			width : 100,
			sortable : true
		}, {
			field : 'selectType',
			title : '类型',
			width : 100,
			sortable : true
		},{
			field : 'selectCount',
			title : '数量',
			width : 100,
			sortable : true
		},{
			field : 'selectParam',
			title : '参数',
			width : 100,
			sortable : true
		}, {
			field : 'selectInfo',
			title : '简介',
			width : 100,
			sortable : true
		},{
			field : 'selectApprove',
			title : '审批',
			width : 100,
			sortable : true
		},{
			field : 'selectAccept',
			title : '验收',
			width : 100,
			sortable : true
		},{
			field : 'selectStat',
			title : '合同',
			width : 100,
			sortable : true
		},{
			field : 'selectVender',
			title : '厂家',
			width : 100,
			sortable : true
		}
		] ],
		toolbar : [ {
			text : '合同签订',
			iconCls : 'icon-edit',
			handler : function() {
				dealsign();
			}
		} ]
	});
});
function searchFun() {
	$('#dealsign_datagrid').datagrid('load', serializeObject($('#dealsign_searchForm')));
}
function clearFun() {
	$('#dealsign_layout input[name=selectName]').val('');
	$('#dealsign_datagrid').datagrid('load', {});
}
function dealsign(){
	var rows = $('#dealsign_datagrid').datagrid('getChecked'); 
	var ids = [];
	if (rows.length > 0) {
		$('#dealsign_Dialog').dialog('open');
		for ( var i = 0; i < rows.length; i++) {
			ids.push(rows[i].id);
		}
	} else {
		$.messager.show({
			title : '提示',
			msg : '请勾选要签订合同的记录！'
		});
	}
}
</script>
    
    
<div id="dealsign_layout" class="easyui-layout" data-options="fit:true,border:false">
<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="dealsign_searchForm">
			检索型号(可模糊查询)：<input name="selectName" type="text" />
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="dealsign_datagrid"></table>
	</div>
</div>
<div id="dealsign_Dialog" class="easyui-dialog" data-options="title:'合同签订',width: 250,    
    height: 110,closed:true, modal:true,
				closable:true, buttons:[{
				text:'签订',
				iconCls:'icon-add',
				handler:function(){
					$('#dealsign_Form').submit();
				}
			}]">
	<form id="dealsign_Form" method="post" > 
		<table>
			<tr>
				<th>签订时间：</th>
				<td><input class="easyui-datetimebox" name="selectStat"     
        			data-options="required:true,showSeconds:false"  style="width:150px">
        		</td>
			</tr>
		</table>
	</form>
</div>    