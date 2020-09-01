/**
 * 
 */
 $(function(){
 	setParamRule = {
		init : function (){
			$(".setParamRule .row #paramObject").val("baseForms");
			$(".setParamRule .row #paramKey").val("");
			this.find();
			$(".setParamRule.wrap .row .spanTitle .searh .buttonCls.qry").off("click").on("click",function(){
				setParamRule.find();
			});
		},
		find : function (){
			WebUtil.ajax("POST","../FlowRule/getParamsByType",
					{"r":new Date().getTime(),"staffno":setParam.staffno,
				"flowId":setParam.flowId,"nodeId":setParam.nodeId,
				"ruleId":setParam.ruleId,"type":$(".setParamRule .row #paramObject").val(),
				"keyword":$(".setParamRule .row #paramKey").val()},
				setParamRule.showParams,true,function(){},function(){});
		},
		tablesHtml : [
			'{{if datas!=null && datas.length>0}}',
			'{{each datas as value index}}',
			'<tr {{if index%2==0}}class="interleave"{{/if}} form="{{= value.form }}" fieldId="{{=value.fieldId}}">',
			'<td>{{= value.id }}</td><td>{{= value.name }}</td></tr>',
			'{{/each}}',
			'{{/if}}'
			].join(""),//
		showParams : function (datas){
			$(".setParamRule.wrap .row .spanEle table tbody").empty()
				.html(WebUtil.template(setParamRule.tablesHtml,{"datas":datas}));
			$(".setParamRule.wrap .row .spanEle table tbody tr").off("dblclick").on("dblclick",function(){
				//alert($(this).attr("form")+"."+$(this).attr("fieldId"));
				//alert(setParam.paramTypes[$(this).attr("form")]+"."+$(this).find("td").eq(1)[0].innerText);
				WebUtil.ajax("POST","../FlowRule/saveOneRuleParam",
					{"r":new Date().getTime(),"staffno":setParam.staffno,
				"flowId":setParam.flowId,"nodeId":setParam.nodeId,
				"ruleId":setParam.ruleId,"ruleType":setParam.ruleType,
				"opType":setParam.opType,
				"showParam":setParam.paramTypes[$(this).attr("form")]+"."+$(this).find("td").eq(1)[0].innerText,
				"realParam":$(this).attr("form")+"."+$(this).attr("fieldId")
				},
				setParamRule.parentEvent,true,function(){},function(){alert('保存失败!');});
			});
		},
		parentEvent : function (datas){
			if ( datas && datas.flag==true ){//&& datas.showP && datas.realP ){
				if (window.parent){
					if ( window.parent.ruleSet ){
						if (typeof window.parent.ruleSet.zTreeFreshNode == 'function')
							window.parent.ruleSet.zTreeFreshNode(setParam.ruleId,datas.showP,datas.realP);
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
 	setParamRule.init();
 });