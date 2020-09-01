/**
 * 
 */
$(function(){
	$("#ruleTypeSet.wrap .row .searh .buttonCls.checkOk").off("click").on("click",function(){
		var _type = $("#ruleTypeSet.wrap .row #ruleType[type='radio']:checked").val();
		if (_type != ""){
			WebUtil.ajax("POST","../FlowRule/saveCurrentRule",
					{"r":new Date().getTime(),"staffno":ruleTypeSet.staffno,
				"flowId":ruleTypeSet.flowId,"nodeId":ruleTypeSet.nodeId,
				"ruleId":ruleTypeSet.ruleId,"rulePid":ruleTypeSet.rulePid,
				"lineId":ruleTypeSet.lineId,"opType":ruleTypeSet.opType,"ruleType":_type},
				ruleTypeSetPage.triggerParent,true,function(){},function(){alert("保存失败!")});
		}
	});
	
	ruleTypeSetPage = {
		triggerParent : function (){
			var _type = $("#ruleTypeSet.wrap .row #ruleType[type='radio']:checked").val();
			if (window.parent){
				if (window.parent.ruleSet){
					if (typeof window.parent.ruleSet.addNodeEle == 'function')
						window.parent.ruleSet.addNodeEle(_type);
					if (window.parent.layer && window.parent.ruleSet.ruleTypeSetIndex)
						window.parent.layer.close(window.parent.ruleSet.ruleTypeSetIndex);
				}else if (window.parent.ruleTreeConfigs){
					if (typeof window.parent.ruleTreeConfigs.addNodeEle == 'function')
						window.parent.ruleTreeConfigs.addNodeEle(_type);
					if (window.parent.layer && window.parent.ruleTreeConfigs.ruleTypeSetIndex)
						window.parent.layer.close(window.parent.ruleTreeConfigs.ruleTypeSetIndex);
				}
			}
		}
	};
	
});