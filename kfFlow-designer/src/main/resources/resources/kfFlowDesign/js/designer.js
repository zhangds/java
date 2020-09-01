/**
 * 
 */
$(function(){
	var property={
			width:1024,
			height:600,
			toolBtns:["start round","end round"/*,"task round","node","chat"*/,"state"/*,"plug","join","fork","complex mix"*/],
			haveHead:true,
			headBtns:["open","save","undo","redo"],//如果haveHead=true，则定义HEAD区的按钮
			haveTool:true,
			//haveGroup:true,
			useOperStack:true
		};
		var remark={
			cursor:"选择指针",
			"mutiselect":"通用节点",
			direct:"结点连线",
			start:"入口结点",
			"end":"结束结点",
			//"task":"任务结点",
			//node:"自动结点",
			//chat:"决策结点",
			state:"状态结点",
			//plug:"附加插件",
			//fork:"分支结点",
			//"join":"联合结点",
			//"complex":"复合结点",
			group:"组织划分框编辑开关"
		};
	property.width = $("body .rightArea").outerWidth(true);
	property.height = $("body .rightArea").outerHeight(true);
	demo = $.createGooFlow($("#demo"), property);
	demo.setNodeRemarks(remark);
	demo.loadData(jsondata);
	
	demo.onItemBlur = function(id, model) {
		return true;
	};
	
	var currentId = null;
	demo.onItemFocus = function(id, model) {
		var obj = null;
		if ( id == currentId )
			return true ;
		currentId = id ;
		if (model == "node") {
			obj = this.$nodeData[id];
			//runOption.nodeRender(obj,id,flowId);
			if (obj && obj.type //&& obj.type.indexOf('start')!=0 
					&& obj.type.indexOf('end')!=0 ){
				//layer.alert(flowId +':'+ id + ':'+obj.type);
				designer.nodeRender(obj,id,flowId);
			}else{
				designer.emptyElement();
			}
		}else if (model == "line"){
			designer.emptyElement();
//			obj = this.$lineData[id];
//			runOption.actionRender(obj,id,flowId);
//			layer.alert(flowId +':'+ id + ':'+obj.type);
		}
		return true;
	};
	demo.onBtnOpenClick = function() {
		layer.prompt({
			 formType: 2,
			 value: JSON.stringify(this.exportData()),
			 title: '导入流程图',
			 maxlength : 90000
			},function(val, index){
				demo.clearData();
				demo.$undoStack=[];
				val = val.replace(/(^\s*)|(\s*$)/g, "");
				if (val != ""){
					demo.loadData(JSON.parse(val));
				}
				layer.close(index);
			});
	};
	demo.onBtnSaveClick = function() {
		var _nodes = this.exportData().nodes;
		var _startCount = 0,_endCount = 0;
		for (var key in _nodes){
			if ( _nodes[key] && _nodes[key].type && _nodes[key].type.indexOf('start')==0 ){
				_startCount ++;
			}
			if ( _nodes[key] && _nodes[key].type && _nodes[key].type.indexOf('end')==0 ){
				_endCount ++;
			}
		}
		if (_startCount != 1 || _endCount != 1){
			alert('开始和结束节点必须唯一！');
			return ;
		}
		
		WebUtil.ajax("POST","../FlowWebDesign/designSave",
				{"flowId":flowId,
			"flowJson":JSON.stringify(this.exportData()),
			"r":new Date().getTime(),
			"staffno":staffno},successEvent,true);
		function successEvent(data){
			if (data != null && data.msg )
        		layer.alert(data.msg, {title:"提示:",icon: (data.flag?1:5)});
		}
		
	};
	
	designer = {
			nodeHtml : [
				"<table>",
					"<tr>",
						"<td class=\"th\">节点名称：</td>",
						"<td><input type=\"text\" style=\"width: 220px\" id=\"node_id\" value=\"{{= value.name }}\" disabled=\"disabled\" /></td>",
					"</tr>",
					"<tr>",
						"<td colspan=\"2\" style=\"text-align: center;\">",
							"<div class=\"btn blue\" id=\"wkGroupOpt\" currentName=\"{{= value.name }}\" currentId=\"{{= id }}\" currentFlowId=\"{{= flowId }}\" currentType=\"{{= value.type }}\">节点设置</div>",
						"</td>",
					"</tr>",
				"</table>"
			].join(""),
			nodeRender : function (obj,id,flowId){
				designer.emptyElement();
				$("#propertyForm .form_content").html(WebUtil.template(designer.nodeHtml,{"value":obj,"id":id,"flowId":flowId}));
				designer.nodeSetEvent();
			},emptyElement : function (){
				$("#propertyForm .form_content").empty();
			},nodeSetEvent : function (){
				$("#propertyForm .form_content #wkGroupOpt").off("click").on("click",function(){
					if (nodeSetUrl && nodeSetUrl != "")
						WebUtil.layerOpen('节点设置:'+$(this).attr("currentName"),
							['730px', '580px'],
							nodeSetUrl+"?staffno="+staffno+"&flowId="+flowId+"&nodeId="+$(this).attr("currentId")+"&nodeType="+$(this).attr("currentType"));
				});
			}
	};
});