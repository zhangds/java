/**
 * 
 */
$(function(){
	setConditionRule = {
		init : function (){
			if (setCondition && setCondition.node &&
				setCondition.node.p1){
				$(".setConditionRule .row #paramObject").val(setCondition.node.p1);
			}
			if (setCondition && setCondition.node &&
				setCondition.node.p2){
				$(".setConditionRule .row #paramKey").val(setCondition.node.p2);
			}
			$(".setConditionRule.wrap .row .spanTitle .searh > .buttonCls.saveBtn").off("click").on("click",function(){
				var _math = $(".setConditionRule .row #paramObject").val();
				var _value = $(".setConditionRule .row #paramKey").val();
				if ( !_value || _value ==""){
					layer.alert("值域不能为空!", {title:"提示:",icon: 3});
				}else{
					if (_value.length >=510){
						layer.alert("值域内容超长，请控制在510个以内!", {title:"提示:",icon: 3});
					}else{
						WebUtil.ajax("POST","../FlowRule/saveOneRuleParam",
							{"r":new Date().getTime(),"staffno":setCondition.staffno,
								"flowId":setCondition.flowId,"nodeId":setCondition.nodeId,
								"ruleId":setCondition.ruleId,"ruleType":setCondition.ruleType,
								"opType":setCondition.opType,"lineId":setCondition.lineId,
								"showParam":_math,
								"realParam":_value
							},
						setConditionRule.parentEvent,true,function(){},function(){alert('保存失败!');});
					}
				}
			});
		},
		parentEvent : function (datas){
			if ( datas && datas.flag==true ){//&& datas.showP && datas.realP ){
				if (window.parent ){ 
					if (window.parent.ruleSet ){
						if (typeof window.parent.ruleSet.zTreeFreshNode == 'function')
							window.parent.ruleSet.zTreeFreshNode(setCondition.ruleId);
					}else if (window.parent.ruleTreeConfigs){
						if (typeof window.parent.ruleTreeConfigs.zTreeFreshNode == 'function')
							window.parent.ruleTreeConfigs.zTreeFreshNode();
						if (window.parent.layer && window.parent.ruleTreeConfigs.ruleInfoPageIndex)
							window.parent.layer.close(window.parent.ruleTreeConfigs.ruleInfoPageIndex);
					}
				}
			}
		}
	};
	setConditionRule.init();
});