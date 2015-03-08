<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#fixplan_form').form({    
		    url : '${pageContext.request.contextPath}/eqRecordAction!fix.action',
		    onSubmit: function(param){     
		    	var rows = $('#fixPlan_datagrid').datagrid('getChecked'); 
				param.id = rows[0].id;
		    	// alert(param.id);
		    }, 
		    success : function(r){    
		    	var obj = jQuery.parseJSON(r);
				if (obj.success) { 
					$('#fixplan_dialog').dialog('close'); 
					$('#fixPlan_datagrid').datagrid('load');
					$('#fixPlan_datagrid').datagrid('unselectAll');
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
		$('#fixPlan_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/eqRecordAction!searchByType.action',
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
				text : '设备维修完成',
				iconCls : 'icon-add',
				handler : function() {
					fixEq();
				}
			}, '-' ]
		});
		
	});

	function searchFun() {
		$('#fixPlan_datagrid').datagrid('load', serializeObject($('#eqRecordType_searchForm')));
	}
	
	function clearFun() {
		$('#fixplan_layout input[name=eqBugtype]').val('');
		$('#fixPlan_datagrid').datagrid('load', {});
	}
	
	
	function fixEq() {
		// alert(0);
		var rows = $('#fixPlan_datagrid').datagrid('getChecked'); 
		// var ids = [];
		if (rows.length == 1) {
			$('#fixplan_dialog').dialog('open'); 
		} else {
			$.messager.show({
				title : '提示',
				msg : '请重新勾选维修记录！'
			});
		}
	}
</script>

<div id="fixplan_layout" class="easyui-layout" data-options="fit:true,border:false">
	
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="eqRecordType_searchForm">
			检索故障类型(可模糊查询)：<input name="eqBugtype" type="text" />
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="fixPlan_datagrid"></table>
	</div>
</div>

<div id="fixplan_dialog" class="easyui-dialog" title="填写维修信息"   
        data-options="width:240,height:100,iconCls:'icon-add',closed:true,
        resizable:true,modal:true, buttons:[{
				text:'保存',
				iconCls:'icon-edit',
				handler:function(){
					$('#fixplan_form').submit();
				}
			},{
				text:'关闭',
				iconCls:'icon-cancel',
				handler:function(){
					$('#fixplan_dialog').dialog('close');
				}
			}]
        ">   
	<form id="fixplan_form" method="post">
	    <div>   
	        <label for="eqRepaircase">维修情况:</label>
	        	 <select id="eqRepaircase" class="easyui-combobox" data-options="required:true" name="eqRepaircase" style="width:150px;">   
				    <option value="已修好">已修好</option>   
				    <option value="已报废">已报废</option>    
				</select>  
	    </div>   
	</form>   		   
</div>  
	
	
	
	
	
	