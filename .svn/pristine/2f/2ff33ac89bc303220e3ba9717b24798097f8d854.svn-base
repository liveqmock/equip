<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#searchfix_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/eqRecordAction!searchFix.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			rownumbers:true,
			singleSelect : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'eqName',
			checkOnSelect : false,
			selectOnCheck : false,
			sortOrder : 'asc',
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 100,
				checkbox : true
			}, {
				field : 'eqName',
				title : '设备型号',
				width : 100,
				sortable : true
			} ] ],
			columns : [ [ {
				title : '故障原因',
				field : 'eqBugcase',
				width : 100,
				sortable : true
			}, {
				title : '故障时间',
				field : 'eqBugtime',
				width : 100,
				align : 'right',
				sortable : true
			} , {
				title : '故障类型',
				field : 'eqBugtype',
				width : 100,
				align : 'right',
				sortable : true
			} , {
				title : '维修时间',
				field : 'eqRepairtime',
				width : 100,
				align : 'right',
				sortable : true
			} , {
				title : '维修情况',
				field : 'eqRepaircase',
				width : 100,
				align : 'right',
				sortable : true
			} ] ],
			toolbar : [ '-', {
				 
			}, '-' ]
		});
		
	});

	function searchFun() {
		$('#searchfix_datagrid').datagrid('load', serializeObject($('#eqName_searchForm')));
	}
	
	function clearFun() {
		$('#fixplansearch_layout input[name=eqName]').val('');
		$('#searchfix_datagrid').datagrid('load', {});
	}
	
</script>

<div id="fixplansearch_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="eqName_searchForm">
			检索型号(可模糊查询)：<input name="eqName" type="text" />
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="searchfix_datagrid"></table>
	</div>
</div>

 
	
	
	
	
	