$(function(){
	nodeStepTablePage = {
		__init__ : function (data){
			if (data && Array.isArray(data) && data.length ==4){
				$("body div.wrap label#nodeStepSet > div.content >div.row > .spanEle > #nodeStepTable > tbody").empty()
					.html(WebUtil.template(this.bodyHtml,{"data":data}));
				$("body div.wrap label#nodeStepSet > div.content >div.row > .spanEle > #nodeStepTable > tbody >tr").off("dblclick").on("dblclick",function(){
					nodeStepTablePage.rulePageIndex = WebUtil.layerOpen(false,[ '670px','520px' ],
							'../FlowRule/setAllRule?staffno='+workGroupInfo.staffno+
							'&flowId='+workGroupInfo.flowId+'&nodeId='+workGroupInfo.nodeId+
							'&opType='+$(this).attr("opType"));
				});
			}
		},
		rulePageIndex : null,
		bodyHtml : '<tr class="interleave" opType="{{=data[2]}}"><td>{{=data[0]}}</td><td>{{=data[1]}}</td><td style="text-align: center;">{{if data[3]=="N"}}未配置{{else}}已配置{{/if}}</td></tr>'
	};

	nodeStepTablePage.__init__(workGroupInfo.timeLimitSet);
});