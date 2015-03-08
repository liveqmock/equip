<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#eqrecord_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/eqRecordAction!searchWarning.action',
			rownumbers : true,
			singleSelect : true,
			fitColumns : true,
			columns : [ [ {
				field : 'eqBugtype',
				title : '故障类型',
				width : 100
			}, {
				field : 'eqBugcase',
				title : '故障原因',
				width : 100
			}, {
				field : 'eqBugtime',
				title : '发送日期',
				width : 100,
				align : 'right'
			}, {
				field : 'eqRepaircase',
				title : '状态',
				width : 100,
				align : 'right'
			} ] ]
		});
	});
</script>
<div style="padding: 10px;">
	<table class="easyui-datagrid" id="eqrecord_datagrid"
		style="height: auto; overflow: auto">
	</table>
</div>