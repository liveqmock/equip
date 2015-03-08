<%@ page language="java" pageEncoding="UTF-8"%>

<script type="text/javascript">
function addTab(opts) {
	var t = $('#layout_center_tabs');
	if (t.tabs('exists', opts.title)) {
		t.tabs('select', opts.title);
	} else {
		t.tabs('add', opts);
		console.debug(opts.content);
	}
}
$(function() {
	/**************/
	if('${user.username}' != ''){
	window.setTimeout(function(){
		parent.$.messager.show({
			title:"消息提示",
			msg:'欢迎登录，${user.username}！ <a href="javascript:void" onclick="top.showAbout();">联系管理员</a>',
			timeout:5000
		})
	},3000);
	}
	/*************/
	
	panels = [ {
		id : 'p1',
		title : '公共栏',
		height : 200,
		collapsible : true,
		href:'pages_portal_gonggao.action'
	}, {
		id : 'p2',
		title : '待办事宜',
		height : 200,
		collapsible : true,
		href:'pages_portal_daiban.action'
	}, {
		id : 'p3',
		title : '预警信息',
		height : 200,
		collapsible : true,
		href:'pages_portal_yujing.action'
	}, {
		id : 'p4',
		title : '系统BUG反馈',
		height : 200,
		collapsible : true,
		href:'pages_portal_bug.action'
	}];
	 $('#layout_portal_portal').portal({
		border : false,
		fit : true
		/*
		onStateChange : function() {
			$.cookie('portal-state', getPortalState(), {
				expires : 7
			});
		}
	 	*/
	});
	var state = state = 'p1,p2:p3,p4';/*冒号代表列，逗号代表行*/
	/*
	var state = $.cookie('portal-state');
	if (!state) {
		state = 'p1,p2:p3,p4';
	}
	*/
	addPortalPanels(state);
	$('#layout_portal_portal').portal('resize');

});

function getPanelOptions(id) {
	for ( var i = 0; i < panels.length; i++) {
		if (panels[i].id == id) {
			return panels[i];
		}
	}
	return undefined;
}
function getPortalState() {
	var aa=[];
	for(var columnIndex=0;columnIndex<2;columnIndex++) {
		var cc=[];
		var panels=$('#layout_portal_portal').portal('getPanels',columnIndex);
		for(var i=0;i<panels.length;i++) {
			cc.push(panels[i].attr('id'));
		}
		aa.push(cc.join(','));
	}
	return aa.join(':');
}
function addPortalPanels(portalState) {
	var columns = portalState.split(':');
	for (var columnIndex = 0; columnIndex < columns.length; columnIndex++) {
		var cc = columns[columnIndex].split(',');
		for (var j = 0; j < cc.length; j++) {
			var options = getPanelOptions(cc[j]);
			if (options) {
				var p = $('<div/>').attr('id', options.id).appendTo('body');
				p.panel(options);
				$('#layout_portal_portal').portal('add', {
					panel : p,
					columnIndex : columnIndex
				});
			}
		}
	}
}

</script>
<div id="layout_center_tabs" class="easyui-tabs" data-options="fit:true,border:false,
	onSelect:function(title,index){ 
			var tab = $('#layout_center_tabs').tabs('getSelected'); 
			tab.panel('open').panel('refresh');
	} " style="overflow: hidden;">
	<div title="消息中心" id="subWarp" style="width:100%;height:100%;overflow:hidden">
		<div id="layout_portal_portal" style="position:relative;height:600px;">
		<div></div>
		<div></div>
	</div>
		
	</div> 
</div>