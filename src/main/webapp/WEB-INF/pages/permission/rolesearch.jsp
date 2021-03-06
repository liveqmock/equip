<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#menu_Form').form({
			url : '${pageContext.request.contextPath}/roleAction!grantmenu.action',
			onSubmit:function(param){
				 param.menuIds = getChecked();
				 var rows = $('#role_datagrid').datagrid('getChecked'); 
				 param.id = rows[0].id;
				 //alert(param.ids);
				 //alert(param.id);
				
			},
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#addMenu_dialog').dialog('close');
					$('#role_datagrid').datagrid('load');
					$('#role_datagrid').datagrid('unselectAll');
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
				}
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
		});
		$('#roleadd_Form').form({
			url : '${pageContext.request.contextPath}/roleAction!roleadd.action',
			onSubmit:function(param){
				 return $("#roleadd_Form").form('validate');
			},
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#roleadd_Dialog').dialog('close');
					$('#role_datagrid').datagrid('load');
					$('#role_datagrid').datagrid('unselectAll');
					$.messager.show({
						title : '提示',
						msg : r.msg
					});
				}
				$.messager.show({
					title : '提示',
					msg : obj.msg
				});
			}
		});
		$('#role_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/roleAction!search.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			singleSelect : true,
			rownumbers:true,
			pageSize : 10,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'roleName',
			sortOrder : 'asc',
			/*pagePosition : 'both',*/
			checkOnSelect : false,
			selectOnCheck : false,
			frozenColumns : [ [ {
				field : 'id',
				title : '编号',
				width : 100,
				checkbox : true
			} ] ],
			columns : [ [ {
				field : 'roleName',
				title : '角色',
				width : 100,
				sortable : true
			}, {
				field : 'roleDesc',
				title : '角色描述',
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
				text : '添加',
				iconCls : 'icon-edit',
				handler : function() {
					add();
				}
			},'-' , {
				text : '添加可执行的菜单',
				iconCls : 'icon-add',
				handler : function() {
					addMenu();
				}
			},'-' ]
		});
	});

	function searchFun() {
		$('#role_datagrid').datagrid('load', serializeObject($('#right_searchForm')));
	}
	function clearFun() {
		$('#role_layout input[name=roleName]').val('');
		$('#role_datagrid').datagrid('load', {});
	}
	
	$('#role_searchForm input').bind('keyup', function(event) {/* 增加回车提交功能 */
		if (event.keyCode == '13') {
			$('#role_searchForm').submit();
		}
	});
	
	function add(){
		$('#roleadd_Dialog').dialog('open');
	}
	
	function remove() {
		var rows = $('#role_datagrid').datagrid('getChecked'); 
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？', function(r) {
				if (r) {
					for ( var i = 0; i < rows.length; i++) {
						ids.push(rows[i].id);
					}
					$.ajax({
						url : '${pageContext.request.contextPath}/roleAction!remove.action',
						data : {
							ids : ids.join(',')
						},
						dataType : 'json',
						success : function(r) {
							$('#role_datagrid').datagrid('load');
							$('#role_datagrid').datagrid('unselectAll');
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
		 
		var rows = $('#role_datagrid').datagrid('getChecked'); 
		var ids = [];
		
		if (rows.length == 1) {
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			$('#role_Dialog').dialog('open');
			
		} else {
			$.messager.show({
				title : '提示',
				msg : '请重新选择的记录！'
			});
		}
	}
	function addMenu(){
		var rows = $('#role_datagrid').datagrid('getChecked'); 
		var ids = [];
		
		if (rows.length == 1) {
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			}
			getRolesIncludeChecked(rows[0].id);
			$('#addMenu_dialog').dialog('open');
			
		} else {
			$.messager.show({
				title : '提示',
				msg : '请重新选择的记录！'
			});
		}
	}
function getChecked(){
	var nodes = $('#menu_tree').tree('getChecked',['checked','indeterminate']);
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if (s != '') s += ',';
		s += nodes[i].id;
	} 
	//alert(s);
	return s;
} 
function getRolesIncludeChecked(roleId){
	$('#menu_tree').tree({    
	    url: '${pageContext.request.contextPath}/menuAction!getAllTreeNodeChecked.action?roleId='+roleId, 
	    lines : true,
		checkbox:true,
		parentField : 'pid',
		animate : true
	    
	});  
	 
}
</script>

<div id="role_layout" class="easyui-layout" data-options="fit:true,border:false">
	
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="role_searchForm">
			检索型号(可模糊查询)：<input name="roleName" type="text" />
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="role_datagrid"></table>
	</div>
</div>
<div id="roleadd_Dialog" class="easyui-dialog" data-options="title:'添加角色',width:250,    
    height:200,closed:true, modal:true,
				closable:true, buttons:[{
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					$('#roleadd_Form').submit();
				}
			}]">
	<form id="roleadd_Form" method="post" > 
		<table>
			<tr>
				<th>角色名称：</th>
				<td><input type="text" name="roleName" class="easyui-validatebox"  data-options="required:true"  />  
				</td>
			</tr>
			<tr>
				<th>角色描述：</th>
				<td><textarea name="roleDesc" rows="3" cols="15" class="easyui-validatebox"  data-options="required:true" ></textarea></td>
			</tr>
		</table>
	</form>
</div>
 
<div id="addMenu_dialog" class="easyui-dialog" data-options="title:'添加可执行的菜单',width:265,    
    height:300,closed:true, modal:true,
				closable:true, buttons:[{
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					$('#menu_Form').submit();
				}
			}]">
	<form id="menu_Form" method="post" > 
		 <ul id="menu_tree" ></ul>  
	</form> 
</div>

	
	
	
	
	
	