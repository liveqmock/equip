<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#rightupdate_Form').form({
			url : '${pageContext.request.contextPath}/userAction!grantrole.action',
			onSubmit:function(param){
				param.roleIds = getChecked();
				var rows = $('#usersearch_datagrid').datagrid('getChecked'); 
				param.id = rows[0].id;
			},
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#updateright_Dialog').dialog('close');
					$('#usersearch_datagrid').datagrid('load');
					$('#usersearch_datagrid').datagrid('unselectAll');
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
		$('#useradd_Form').form({
			url : '${pageContext.request.contextPath}/userAction!useradd.action',
			onSubmit:function(param){
				return $(this).form('validate');
			},
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#useradd_Dialog').dialog('close');
					$('#usersearch_datagrid').datagrid('load');
					$('#usersearch_datagrid').datagrid('unselectAll');
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
		$('#userupdate_Form').form({
			url : '${pageContext.request.contextPath}/userAction!userupdate.action',
			onSubmit:function(param){
				var rows = $('#usersearch_datagrid').datagrid('getChecked'); 
				param.id = rows[0].id;
				return $(this).form('validate');
			},
			success : function(r) {
				var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#userupdate_Dialog').dialog('close');
					$('#usersearch_datagrid').datagrid('load');
					$('#usersearch_datagrid').datagrid('unselectAll');
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
		$('#usersearch_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/userAction!usersearch.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			idField : 'id',
			singleSelect : true,
			pageSize : 10,
			rownumbers:true,
			pageList : [ 10, 20, 30, 40, 50 ],
			sortName : 'userID',
			sortOrder : 'asc',
			/*pagePosition : 'both',*/
			checkOnSelect : false,
			selectOnCheck : false,
			frozenColumns : [ [ {
				field : 'userID',
				title : '用户id',
				width : 100,
				checkbox : true
			}, {
				field : 'username',
				title : '用户名',
				width : 100,
				sortable : true
			} ] ],
			columns : [ [ {
				field : 'roleName',
				title : '职位',
				width : 100,
			},{
				field : 'birthday',
				title : '出生日期',
				width : 100,
				sortable:true
			},{
				field : 'telephone',
				title : '电话号码',
				width : 100,
				sortable:true
			}
			] ],
			toolbar : [ {
				text : '修改用户',
				iconCls : 'icon-edit',
				handler : function() {
					 update();
				}
			},'-',{
				text : '添加用户',
				iconCls : 'icon-add',
				handler : function() {
					 add();
				}
			}, '-',{
				text : '删除用户',
				iconCls : 'icon-remove',
				handler : function() {
					 remove();
				}
			}, '-',{
				text : '权限修改',
				iconCls : 'icon-reload',
				handler : function() {
					 updateright();
				}
			}]
		});
	});

	function searchFun() {
		$('#usersearch_datagrid').datagrid('load', serializeObject($('#usersearch_searchForm')));
	}
	function clearFun() {
		$('#usersearch_layout input[name=username]').val('');
		$('#usersearch_datagrid').datagrid('load', {});
	}
	function add() {
		$('#useradd_Dialog').dialog('open');
	}
	function update(){
		var rows = $('#usersearch_datagrid').datagrid('getChecked'); 
		if(rows.length == 1){
			$('#userupdate_Dialog').dialog('open');
			$("#userupdate_Form").form("load",rows[0]);
		}else {
			$.messager.show({
				title : '提示',
				msg : '请选择一条记录进行修改！'
			});
		}
	}
	function updateright(){
		var rows = $('#usersearch_datagrid').datagrid('getChecked'); 
		var ids = [];
		
		if (rows.length == 1) {
			for ( var i = 0; i < rows.length; i++) {
				ids.push(rows[i].id);
			} 
			getRolesIncludeChecked(rows[0].id);
			$('#updateright_Dialog').dialog('open');
		} else {
			$.messager.show({
				title : '提示',
				msg : '请重新选择的记录！'
			});
		}
	}
	function remove() {
		var rows = $('#usersearch_datagrid').datagrid('getChecked'); 
		var ids = [];
		if (rows.length > 0) {
			$.messager.confirm('确认', '您是否要删除当前选中的项目？',
					function(r) {
						if (r) {
							for ( var i = 0; i < rows.length; i++) {
								ids.push(rows[i].id);
							}
							$.ajax({
								url : '${pageContext.request.contextPath}/userAction!remove.action',
								data : {
									ids : ids.join(',')
								},
								dataType : 'json',
								success : function(r) {
									$('#usersearch_datagrid').datagrid('load');
									$('#usersearch_datagrid').datagrid('unselectAll');
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
	function getChecked(){
		var nodes = $('#role_tree').tree('getChecked');
		var s = '';
		for(var i=0; i<nodes.length; i++){
			if (s != '') s += ',';
			s += nodes[i].id;
		}
		return s;
	} 
	
	function getRolesIncludeChecked(userId){
		
		$('#role_tree').tree({    
		    url: '${pageContext.request.contextPath}/roleAction!getAllTreeNodeChecked.action?userId='+userId, 
		    lines : true,
			checkbox:true,
			animate : true
		    
		});  
	}

</script>

<div id="usersearch_layout" class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="usersearch_searchForm">
			查找用户(支持用户名模糊查询)：<input name="username" type="text" />
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="usersearch_datagrid"></table>
	</div>
	
	<div id="useradd_Dialog" class="easyui-dialog" data-options="title:'添加用户',width:265,    
    height:190,closed:true, modal:true,
				closable:true, buttons:[{
				text:'添加',
				iconCls:'icon-add',
				handler:function(){
					$('#useradd_Form').submit();
				}
			}]">
	<form id="useradd_Form" method="post" > 
		<table>
			<tr>
				<th>用户名：</th>
				<td><input type="text" name="username" class="easyui-validatebox"  data-options="required:true"  />  
				</td>
			</tr>
			<tr>
				<th>初始密码：</th>
				<td><input type="text" name="userPwd" readonly="readonly"   value="123"   />  
				</td>
			</tr>
			<tr>
				<th>出生日期：</th>
				<td><input type="text" name="birthday" class="easyui-datebox" required="required"  />  
				</td>
			</tr>
			<tr>
				<th>联系电话：</th>
				<td><input type="text" name="telephone" class="easyui-numberbox" required="required"  />  
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="userupdate_Dialog" class="easyui-dialog" data-options="title:'修改用户',width:265,    
    height:190,closed:true, modal:true,
				closable:true, buttons:[{
				text:'提交更改',
				iconCls:'icon-edit',
				handler:function(){
					$('#userupdate_Form').submit();
				}
			}]">
	<form id="userupdate_Form" method="post" > 
		<table>
			<tr>
				<th>用户名：</th>
				<td><input type="text" name="username"   class="easyui-validatebox"  data-options="required:true"  />  
				</td>
			</tr>
			<tr>
				<th>设置密码：</th>
				<td><input type="password" name="userPwd"   class="easyui-validatebox"  data-options="required:true"   />  
				</td>
			</tr>
			<tr>
				<th>出生日期：</th>
				<td><input type="text" name="birthday"   class="easyui-datebox" required="required"  />  
				</td>
			</tr>
			<tr>
				<th>联系电话：</th>
				<td><input type="text" name="telephone" class="easyui-numberbox" data-options="required:true"  />  
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="updateright_Dialog" class="easyui-dialog" data-options="title:'权限修改',width:265,    
    height:160,closed:true, modal:true,
				closable:true, buttons:[{
				text:'提交更改',
				iconCls:'icon-edit',
				handler:function(){
					$('#rightupdate_Form').submit();
				}
			}]">
	<form id="rightupdate_Form" method="post" > 
		<ul id="role_tree"></ul>
	</form>
</div>
</div>
