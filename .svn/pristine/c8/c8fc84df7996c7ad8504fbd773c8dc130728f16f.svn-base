<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#select_apply_form').form({
			url : '${pageContext.request.contextPath}/selectAction!apply.action',
			onSubmit:function(params){
				return $(this).form('validate');
			},
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#select_apply_form').form('reset');
					$('#application_dialog').dialog('close');
					$('#select_datagrid').datagrid('load', {});
				}
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
		});
		$('#select_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/selectAction!search.action',
			fit : true,
			fitColumns : true,
			border : false,
			rownumbers:true,
			pagination : true,
			idField : 'id',
			singleSelect : true,
			pageSize : 10,
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
				text : '申请取消',
				iconCls : 'icon-remove',
				handler : function() {
					remove();
				}
			},'-',{
				text : '审批',
				iconCls : 'icon-ok',
				handler : function() {
					approveOk();
				}
			}, '-',{
				text : '采购申请',
				iconCls : 'icon-add',
				handler : function() {
					application();
				}
			}, '-']
		});
	});

	function searchFun() {
		$('#select_datagrid').datagrid('load', serializeObject($('#select_searchForm')));
	}
	function clearFun() {
		$('#select_layout input[name=selectName]').val('');
		$('#select_datagrid').datagrid('load', {});
	}
	 
	function remove() {
		var rows = $('#select_datagrid').datagrid('getChecked'); 
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/selectAction!remove.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							$('#select_datagrid').datagrid('load');
							$('#select_datagrid').datagrid('unselectAll');
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
	function approveOk() {
		var rows = $('#select_datagrid').datagrid('getChecked'); 
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要通过当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/selectAction!approveOk.action?username=${sessionScope.user.username}',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							$('#select_datagrid').datagrid('load');
							$('#select_datagrid').datagrid('unselectAll');
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
				msg : '请勾选要审批的记录！'
			});
		}
	}
	function application() {
		$('#application_dialog').dialog('open');
	}
	
</script>

<div id="select_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="select_searchForm">
			检索型号(可模糊查询)：<input name="selectName" type="text" />
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="select_datagrid"></table>
	</div>
</div>
<div id="application_dialog" class="easyui-dialog" data-options="title:'添加申请',width:210,    
    height:250,closed:true, modal:true,
				closable:true, buttons:[{
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					$('#select_apply_form').submit();
				}
			},{ text:'重置',
				iconCls:'icon-reload',
				handler:function(){
					$('#select_apply_form').form('reset');
				}
			}]">
	<form id="select_apply_form" method="post"  >
	<div>
		<label for="selectName">型号:</label> <input class="easyui-validatebox"
			type="text" id="selectName"  name="selectName" data-options="required:true" />
	</div>
	<div>
		<label for="selectYear">年份:</label> <input id="selectYear" type="text" name="selectYear"
		class="easyui-datebox" required="required"></input> 
	</div>
	<div>
		<label for="selectType">类型:</label> <input class="easyui-validatebox" id="selectType"
			type="text" name="selectType" data-options="required:true" />
	</div>
	<div>
		<label for="selectParam">参数:</label> <input class="easyui-validatebox" id="selectParam" 
			type="text" name="selectParam" data-options="required:true" />
	</div>
	<div>
		<label for="selectInfo">简介:</label> <input class="easyui-validatebox"
			type="text" name="selectInfo" data-options="required:true" />
	</div>
</form>
</div>
