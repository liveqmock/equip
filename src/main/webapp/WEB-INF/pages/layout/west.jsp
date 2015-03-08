<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript"> 
var setting = {
		async : {
			enable : true,
			url : "${pageContext.request.contextPath }/ztreeAction!getAllTreeNodeWithRight.action",
		},
		data: {
			key: {
				title : "t"
			},
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "pid",
				rootPId: 0
			}
		},
		callback: {
			onClick: addTabToCenter
		}
		 
	};
function addTabToCenter(event, treeId, treeNode, clickFlag) {
	console.debug(treeNode);
	if (treeNode.click != false && treeNode.isParent == false) {
		var ourl = '${pageContext.request.contextPath}' + treeNode.ourl;
		 
		var opts = {
				title:treeNode.name, 
				href: ourl,
				closable:true 
		};
		// 这个函数在center中定义了
		addTab(opts);
	}
}


	$(document).ready(function(){
		$.fn.zTree.init($("#tree"), setting); 
	});
</script>
<div class="easyui-panel" data-options="title:'功能导航',border:false,fit:true">
	<div class="easyui-accordion" data-options="fit:true,border:false">
		<div title="系统菜单" data-options="iconCls:'icon-tip'">
			<ul id="tree" class="ztree"></ul>
		</div>
		<div title="备用" data-options="iconCls:'icon-reload'"></div>
	</div>
</div>