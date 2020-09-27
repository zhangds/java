/**
 * 
 */
 $(function(){
 	setNodeActionRule = {
 		init : function (){
 			if (setAction.node && setAction.node.p1)
 				$(".setNodeActionRule.wrap .row .spanEle label > input[type=\"radio\"][value='"+setAction.node.p1+"']").attr('checked','true');
 			if (setAction.node && setAction.node.p2)
 				$(".setNodeActionRule.wrap textarea#paramKey").val(setAction.node.p2);
 			if (setAction.node && setAction.node.p3){
 				$(".setNodeActionRule.wrap input#orderNu").val(setAction.node.p3);
 			}else{
 				$(".setNodeActionRule.wrap input#orderNu").val("10");
 			}
 			$(".setNodeActionRule.wrap .row .spanTitle .searh > .saveBtn").off("click").on("click",function(){
 				var _radioValue = $(".setNodeActionRule.wrap .row .spanEle label > input[type=\"radio\"]:checked").val();
 				var _paramKey = $(".setNodeActionRule.wrap textarea#paramKey").val();
 				var _orderNo = parseInt($(".setNodeActionRule.wrap input#orderNu").val());
 				if (!_paramKey || _paramKey =="" || _paramKey.length >=510){
 					layer.alert("表达式不能为空或者超过510个字!", {title:"提示:",icon: 3});
 				}else{
 					WebUtil.ajax("POST","../FlowRule/saveOneRuleParam",
							{"r":new Date().getTime(),"staffno":setAction.staffno,
								"flowId":setAction.flowId,"nodeId":setAction.nodeId,
								"ruleId":setAction.ruleId,"ruleType":setAction.ruleType,
								"opType":setAction.opType,"lineId":setAction.lineId,
								"showParam":_radioValue,
								"realParam":_paramKey,
								"orderNo":_orderNo
							},
						setNodeActionRule.parentEvent,true,function(){},function(){alert('保存失败!');});
 				}
 			});	
 		},
 		parentEvent : function (datas){
 			if ( datas && datas.flag==true ){//&& datas.showP && datas.realP ){
				if (window.parent ){ 
					if (window.parent.ruleSet ){
						if (typeof window.parent.ruleSet.zTreeFreshNode == 'function')
							window.parent.ruleSet.zTreeFreshNode(setAction.ruleId);
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
 	setNodeActionRule.init();
 });