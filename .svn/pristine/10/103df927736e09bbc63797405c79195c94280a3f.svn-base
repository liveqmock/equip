<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#equipcheck_datagrid').datagrid( {
			url : '${pageContext.request.contextPath}/selectAction!searchForCheck.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			rownumbers:true,
			pageSize : 10,
			singleSelect : true,
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
			}, {
				field : 'selectCount',
				title : '数量',
				width : 100,
				sortable : true
			}, {
				field : 'selectParam',
				title : '参数',
				width : 100,
				sortable : true
			}, {
				field : 'selectInfo',
				title : '简介',
				width : 100,
				sortable : true
			}, {
				field : 'selectApprove',
				title : '审批',
				width : 100,
				sortable : true
			}, {
				field : 'selectAccept',
				title : '验收',
				width : 100,
				sortable : true
			}, {
				field : 'selectStat',
				title : '合同',
				width : 100,
				sortable : true
			}, {
				field : 'selectVender',
				title : '厂家',
				width : 100,
				sortable : true
			} ] ],
			toolbar : [ {
				text : '设备验收',
				iconCls : 'icon-edit',
				handler : function() {
					equipcheck();
				}
			} ]
		});
	});
	function searchFun() {
		$('#equipcheck_datagrid').datagrid('load',
				serializeObject($('#equipcheck_searchForm')));
	}
	function clearFun() {
		$('#equipcheck_layout input[name=selectName]').val('');
		$('#equipcheck_datagrid').datagrid('load', {});
	}
	function equipcheck() {
		var rows = $('#equipcheck_datagrid').datagrid('getChecked');
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要验收当前选中的项目？',
				function(r) {
					if (r) {
						for ( var i = 0; i < rows.length; i++) {
							ids.push(rows[i].id);
						}
						$.ajax({
							url : '${pageContext.request.contextPath}/selectAction!equipCheck.action?username=${sessionScope.user.username}',
							data : {
								ids : ids.join(',')
							},
							dataType : 'json',
							success : function(r) {
								$('#equipcheck_datagrid').datagrid('load');
								$('#equipcheck_datagrid').datagrid('unselectAll');
								$.messager.show({
									title : '提示',
									msg : r.msg
								});
							}
						});
					}
				});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请勾选要验收的记录！'
			});
		}
	}
</script>


<div id="equipcheck_layout" class="easyui-layout"
	data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false"
		style="height: 100px;">
		<form id="equipcheck_searchForm">
			检索型号(可模糊查询)：<input name="selectName" type="text" /> <a href="#"
				class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel',plain:true"
				onclick="clearFun();">清空</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="equipcheck_datagrid"></table>
	</div>
</div>
