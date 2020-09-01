/**
 * 
 */
$(function(){
	actSetTable={
		init : function(){
			if (workGroupInfo && workGroupInfo.currentLines){
				this.readerHtml(workGroupInfo.currentLines);
			}
		},
		tablesHtml : [
			'{{if datas!=null && datas.length>0}}',
			'{{each datas as value index}}',
			'<tr {{if index%2==0}}class="interleave"{{/if}} lineId={{= value.id }}>',
			'<td>{{= value.id }}</td><td>{{= value.name }}</td>',
			'<td>{{= value.formName }}',
			'<span class="spanImg" title=""></span>',
			'{{= value.nextName }}</td>',
			'<td style="text-align: center;">{{if value.isSet==true}}已配置{{else}}未配置{{/if}}</td></tr>',
			'{{/each}}',
			'{{/if}}'
			].join(""),
		setRuleIndex : null,
		readerHtml : function (datas){
			if (datas){
				$("#actSet .content .row table#actSetTable tbody").empty()
					.html(WebUtil.template(this.tablesHtml,{"datas":datas}));
				$("#actSet .content .row table#actSetTable tbody tr").off("dblclick").on("dblclick",function(){
					actSetTable.setRuleIndex = WebUtil.layerOpen(false,[ '670px','520px' ],
							'../FlowRule/setAllRule?staffno='+workGroupInfo.staffno+
							'&flowId='+workGroupInfo.flowId+'&nodeId='+workGroupInfo.nodeId+
							'&lineId='+$(this).attr("lineId")+'&opType=actLine');
				});
			}
		}
	};
	actSetTable.init();
});