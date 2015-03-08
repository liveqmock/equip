<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(function() {
		$('#add_recordForm').form({    
		    url : '${pageContext.request.contextPath}/eqRecordAction!add.action',
		    onSubmit: function(){    
		         
		    }, 
		    success : function(r){    
		    	var obj = jQuery.parseJSON(r);
				if (obj.success) {
					$('#add_recordDialog').dialog('close');
					$('#eqRecord_datagrid').datagrid('load');
					$('#eqRecord_datagrid').datagrid('unselectAll');
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
		
		$('#eqRecord_datagrid').datagrid({
			url : '${pageContext.request.contextPath}/eqRecordAction!search.action',
			fit : true,
			fitColumns : true,
			border : false,
			pagination : true,
			singleSelect : true,
			rownumbers:true,
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
				text : '新增故障',
				iconCls : 'icon-edit',
				handler : function() {
					addRecord();
				}
			}, '-' ]
		});
		
	});

	function searchFun() {
		$('#eqRecord_datagrid').datagrid('load', serializeObject($('#eqRecord_searchForm')));
	}
	
	function clearFun() {
		$('#eqRecord_layout input[name=eqName]').val('');
		$('#eqRecord_datagrid').datagrid('load', {});
	}
	
	$('#eqRecord_searchForm input').bind('keyup', function(event) {/* 增加回车提交功能 */
		if (event.keyCode == '13') {
			$('#eqRecord_searchForm').submit();
		}
	});
	
	function addRecord() {
		$('#add_recordDialog').dialog('open');
	}
	
	
	
	
</script>

<div id="eqRecord_layout" class="easyui-layout" data-options="fit:true,border:false">
	
	<div data-options="region:'north',title:'查询条件',border:false" style="height: 100px;">
		<form id="eqRecord_searchForm">
			检索型号(可模糊查询)：<input name="eqName" type="text" />
			 <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="searchFun();">查询</a> <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" onclick="clearFun();">清空</a>
		</form>
	</div>
	<div data-options="region:'center',border:false">
		<table id="eqRecord_datagrid"></table>
	</div>
</div>

<div id="add_recordDialog" class="easyui-dialog" title="新增故障记录"   
        data-options="width:250,height:180,iconCls:'icon-add',closed:true,
        resizable:true,modal:true, buttons:[{
				text:'保存',
				iconCls:'icon-edit',
				handler:function(){
					$('#add_recordForm').submit();
				}
			},{
				text:'关闭',
				iconCls:'icon-cancel',
				handler:function(){
					$('#add_recordDialog').dialog('close');
				}
			}]
        ">   
	<form id="add_recordForm" method="post">
		<div>   
	        <label for="eqName">故障设备:</label>   
	        <input id="eqName" class="easyui-combobox easyui-validatebox" name="eqId"   
    				data-options="valueField:'id',textField:'eqName',panelHeight:120,url:'${pageContext.request.contextPath }/eqAction!findAllEq.action',required:true" />  
	    </div>   
		<div>   
	        <label for="eqBugcase">故障原因:</label>   
	        <input class="easyui-validatebox" type="text" name="eqBugcase" data-options="required:true" />   
	    </div>   
		<div>   
	        <label for="eqBugtime">故障时间:</label>   
	       <input class="easyui-datetimebox" name="eqBugtime"     
       			 data-options="required:true,showSeconds:false" value="" style="width:150px">  
         </div>   
	    <div>   
	        <label for="eqBugtype">故障类型:</label>   
	        <input class="easyui-validatebox" type="text" name="eqBugtype" data-options="required:true" />   
	    </div>   
	</form>   		   
</div>  



	
	
	
	
	
	