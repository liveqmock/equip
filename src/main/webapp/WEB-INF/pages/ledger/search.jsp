<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#eq_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/eqAction!search.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			singleSelect : true,
			rownumbers:true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'eqName',
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
				field : 'eqName',
				title : '型号',
				width : 100,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'eqYear',
				title : '年份',
				width : 100,
				sortable : true
			}, {
				field : 'eqParam',
				title : '参数',
				width : 100,
				sortable : true
			}, {
				field : 'eqType',
				title : '类型',
				width : 100,
				sortable : true
			}, {
				field : 'eqPact',
				title : '合同情况',
				width : 100,
				sortable : true
			}, {
				field : 'eqBugN',
				title : '设备故障次数',
				width : 100,
				sortable : true
			},{
				field : 'eqRepairN',
				title : '设备维修次数',
				width : 100,
				sortable : true
			},{
				field : 'eqInfo',
				title : '设备信息',
				width : 100,
				sortable : true
			},{
				field : 'eqState',
				title : '设备状况',
				width : 100,
				sortable : true
			},{
				field : 'eqVender',
				title : '设备出厂厂家',
				width : 100,
				sortable : true
			}
			] ],
			toolbar : [ {
				text : '删除',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			}, '-', {
				text : '修改',
				iconCls : 'icon-edit',
				handler : function() {
					
				}
			}, '-', {
				text : '查询维修履历',
				iconCls : 'icon-search',
				handler : function() {
					// alert(0);
					search();
				}
			}, '-' ]
		});
	});

	function searchFun() {
		$('#eq_datagrid').datagrid('load', serializeObject($('#eq_searchForm')));
	}
	function clearFun() {
		$('#eq_layout input[name=name]').val('');
		$('#eq_datagrid').datagrid('load', {});
	}
	
	$('#eq_searchForm input').bind('keyup', function(event) {/* 增加回车提交功能 */
		if (event.keyCode == '13') {
			$('#eq_searchForm').submit();
		}
	});
	
	function remove() {
		var rows = $('#eq_datagrid').datagrid('getChecked'); 
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/eqAction!remove.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							$('#eq_datagrid').datagrid('load');
							$('#eq_datagrid').datagrid('unselectAll');
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
				msg : '请勾选要删除的记录！'
			});
		}
	}
	
	function search() {
		 
		var rows = $('#eq_datagrid').datagrid('getChecked'); 
		var ids = [];
		
		if (rows.length == 1) {
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			$('#eq_Dialog').dialog('open');
			$('#eqRecord_datagrid').datagrid({
				url : '${pageContext.request.contextPath}/eqAction!searchRecord.action?id='+ids[0],
				columns : [ [ {
					title : '设备编号',
					field : 'eqName',
					width : 100
				}, {
					title : '故障原因',
					field : 'eqBugcase',
					width : 100
				}, {
					title : '故障时间',
					field : 'eqBugtime',
					width : 100,
					align : 'right'
				} , {
					title : '故障类型',
					field : 'eqBugtype',
					width : 100,
					align : 'right'
				} , {
					title : '维修时间',
					field : 'eqRepairtime',
					width : 100,
					align : 'right'
				} , {
					title : '维修情况',
					field : 'eqRepaircase',
					width : 100,
					align : 'right'
				} ] ]
			});
		} else {
			$.messager.show({
				title : '提示',
				msg : '请重新选择的记录！'
			});
		}
	}
	
	
</script>

<div id="eq_layout" class="easyui-layout" data-options="fit:true,border:false">
	
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="eq_searchForm">
			检索型号(可模糊查询)：<input name="eqName" type="text" />
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="eq_datagrid"></table>
	</div>
</div>

<div id="eq_Dialog" class="easyui-dialog" data-options="title:'履历信息',width:650,    
	    height: 200,closed:true, modal:true, closable:true">	
	<table id="eqRecord_datagrid"></table>   
</div>


	
	
	
	
	
	