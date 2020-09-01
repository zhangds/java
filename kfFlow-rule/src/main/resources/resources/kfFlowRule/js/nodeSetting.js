/**
 * 
 */
$(function(){
	workArea = {
		keyWords : "",
		ztreeDblClick : function (event, treeId, treeNode, clickFlag){
			//layer.alert("treeNode="+treeNode.id+"$$$"+treeNode.name, {title:"提示:",icon: 3});
			var _index = $("#workGroupSet .content .row .spanEle table#tableElent tbody tr").length ;
			var _flag = true;
			for (var _i=0;_i<workArea.oldGroupIds.length;_i++){
				if (workArea.oldGroupIds[_i]==treeNode.id){
					_flag = false;
					break;
				}
			}
			for (var _i=0;_i<workArea.addGroupIds.length;_i++){
				if (workArea.addGroupIds[_i]==treeNode.id){
					_flag = false;
					break;
				}
			}
			if (_flag){
				workArea.addGroupIds.push(treeNode.id);
				$("#workGroupSet .content .row .spanEle table tbody").append(
						WebUtil.template(workArea.workGroupTableAddTrHtml,{"index":_index,"id":treeNode.id,"name":treeNode.name}));
			}else{
				layer.alert("工作组:"+treeNode.name+",已经在此节点选择!", {title:"提示:",icon: 3});
			}
			
		},
		addGroupIds : [],
		oldGroupIds : [],
		ztreeSuccessEvent : function (data){
			if (data && data.length && data.length >0){
				if (workArea.keyWords != ""){
					$.fn.zTree.destroy("tree");
				}
				$(".qryArea .spanEle #wkTxt").val(workArea.keyWords);
				$.fn.zTree.init($("#tree"), workGroup.ztreeSetting, data);
			}
		},
		workGroupTableHtml : [
			'{{if datas!=null && datas.length>0}}',
			'{{each datas as value index}}',
			'<tr class="{{if index%2==0}}interleave{{/if}}" idNo="{{= value.id }}"><td class="center"></td><td>{{= value.id }}</td><td>{{= value.name }}</td>',
			'</tr>',
			'{{/each}}',
			'{{/if}}'
		].join(""),
		workGroupTableAddTrHtml : ['<tr class="{{if index%2==0}}interleave{{/if}}" idNo="{{= id }}"><td class="center"></td><td>{{= id }}</td><td>{{= name }}</td></tr>'].join(""),
		workGroupSuccessEvent : function (data){
			//workArea.oldGroupIds
			if (data){
				for (var _i=0;_i<data.length;_i++ ){
					workArea.oldGroupIds.push(data[_i].id);
				}
				$("#workGroupSet .content .row .spanEle table tbody").empty().html(WebUtil.template(workArea.workGroupTableHtml,{"datas":data}));
			}
			$("#workGroupSet .row .searh .buttonCls.saveBtn").off("click").on("click",function(){
					if (workArea.addGroupIds.length >0){
						WebUtil.ajax("POST","../FlowRule/saveCurrentNodeWrokGroups",
								{"r":new Date().getTime(),"staffno":workGroupInfo.staffno,
							"flowId":workGroupInfo.flowId,"nodeId":workGroupInfo.nodeId,"groupIds":workArea.addGroupIds.join(",")},
							workArea.workGroupSaveSuccessEvent,true,function(){},function(){alert("请联系系统管理员!")});
					}
				});
		},
		workGroupSaveSuccessEvent : function (data){
//			for (var _i=0;_i<workArea.addGroupIds;_i++){
//				workArea.oldGroupIds.push(workArea.addGroupIds[_i]);
//			}
//			workArea.oldGroupIds.push(workArea.addGroupIds);
//			workArea.addGroupIds = [];
			if (data && data.flag=="true"){
				workArea.oldGroupIds.push(workArea.addGroupIds);
				workArea.addGroupIds = [];
				layer.alert("工作组保存成功!", {title:"提示:",icon: 2});
			}else{
				layer.alert("工作组保存失败!", {title:"提示:",icon: 3});
			}
		}
	};
	workGroup = {
		ztreeSetting : {data: {
			simpleData: {
				enable: true,
				idKey: "id",
		        pIdKey: "pId",
			}
		},
		callback: {
			//beforeClick: beforeClick,
			onDblClick: workArea.ztreeDblClick
		}},
		workGroupInit : function (){
			workArea.workGroupSuccessEvent(workGroupInfo.currentNodeWorkGroups);
			workGroup.ztreeInit();
			if (workGroupInfo.nodeType && workGroupInfo.nodeType.indexOf("start")>=0){
				$("body .wrap label#workGroupSet,\
					body .wrap label#ruleSet,\
					body .wrap label#backFormSet").css({"display":"none"});
				$("body .wrap label#nodeStepSet").trigger("click");
			}else{
				$("#workGroupSet").trigger("click");
			}
		},
		ztreeInit : function(){
			WebUtil.ajax("POST","../FlowRule/getAllWrokGroup",
					{"r":new Date().getTime(),"staffno":workGroupInfo.staffno},
					workArea.ztreeSuccessEvent,true,function(){},workArea.ztreeSuccessEvent);
			$(".searh .qryKeyWord").off("click").on("click",function(){
				if (workArea.keyWords != $(".qryArea .spanEle #wkTxt").val()){
					WebUtil.ajax("POST","../FlowRule/getAllWrokGroup",
							{"r":new Date().getTime(),"staffno":workGroupInfo.staffno,
								"keyWords":$(".qryArea .spanEle #wkTxt").val()},
							workArea.ztreeSuccessEvent,true,function(){},workArea.ztreeSuccessEvent);
					workArea.keyWords = $(".qryArea .spanEle #wkTxt").val();
				}
				
			});
		}
	};

	workGroup.workGroupInit();
});