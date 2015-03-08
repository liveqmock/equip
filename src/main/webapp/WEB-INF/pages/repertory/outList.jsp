<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <script type="text/javascript">
	$(function() {
		$('#outList_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/storageAction!outList.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			singleSelect : true,
			rownumbers:true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'eqNumb',
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
				field : 'eqNumb',
				title : '设备数量',
				width : 100,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'storageNumb',
				title : '机房号',
				width : 100,
				sortable : true
			}, {
				field : 'storageParam',
				title : '参数',
				width : 100,
				sortable : true
			}, {
				field : 'storageW',
				title : '温度',
				width : 150,
				sortable : true
			},{
				field : 'storageS',
				title : '湿度',
				width : 100,
				sortable : true
			}
			] ],
			toolbar : [ 
			 '-', {
				text : '入库',
				iconCls : 'icon-add',
				handler : function() {
					inputData();
				}
			}, '-' ]
		});
	});

	function searchFun() {
		$('#outList_datagrid').datagrid('load', serializeObject($('#outList_searchForm')));
	}
	
	function clearFun() {
		$('#outList_layout input[name=storageNumb]').val('');
		$('#outList_datagrid').datagrid('load', {});
	}
	 
	
	function inputData() {
		var rows = $('#outList_datagrid').datagrid('getChecked'); 
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要将库中当前选中的项目入库？',
					function(r) {
						if (r) {
							for ( var i = 0; i < rows.length; i++) {
								ids.push(rows[i].id);
							}
							$.ajax({
								url : '${pageContext.request.contextPath}/storageAction!inputData.action',
								data : {
									ids : ids.join(',')
								},
								dataType : 'json',
								success : function(r) {
									$('#outList_datagrid').datagrid('load');
									$('#outList_datagrid').datagrid('unselectAll');
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
				msg : '请勾选要入库的记录！'
			});
		}
	}

</script>

<div id="outList_layout" class="easyui-layout"
	data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false"
		style="height: 100px;">
		<form id="outList_searchForm">
			检索机房号(可模糊查询)：<input name="storageNumb" type="text" /> <a href="#"
				class="easyui-linkbutton"
				data-options="iconCls:'icon-search',plain:true"
				onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton"
				data-options="iconCls:'icon-cancel',plain:true"
				onclick="clearFun();">清空</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="outList_datagrid"></table>
	</div>
</div>
