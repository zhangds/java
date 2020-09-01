/**
 * 
 */
$(function(){
	managerPage = {
		init : function (){
			this.find();
			$(".row .searh .buttonCls.qry").off("click").on("click",function(){
				managerPage.find();
			});
			$(".row .searh .buttonCls.add").off("click").on("click",function(){
				WebUtil.layerOpen(false,[ '800px','400px' ],'../FlowWebDesign/detail?staffno='+$(this).attr("staffno"));
			});
			$(".row .searh .buttonCls.update").off("click").on("click",function(){
				var _temp = $("#radioFlowId:checked").val();
		    	if (_temp== null){
		    		layer.alert('请选择需要修改的工作流!', {title:"提示:",icon: 6});
		    	}else{
		    		WebUtil.layerOpen(false,[ '800px','400px' ],'../FlowWebDesign/detail?staffno='+$(this).attr("staffno")+"&flowId="+_temp);
		    	}
			});
		},
		find : function (){
			//alert(1);
			WebUtil.ajax("POST","../FlowWebDesign/getWkList",
					{"wkId":$(".row #wkId").val(),
				"wkName":$(".row #wkName").val(),
				"wkRemark":$(".row #wkRemark").val()},this.success,true);
		},
		success :function (data){
			if (data != null){
				$(".content table tbody").empty().html(WebUtil.template(managerPage.tempTbody,{"datas":data.datas}));
				
				$(".row.content table tbody tr").off("dblclick").on("dblclick",function(){
					var _flowId = $(this).children().eq(1).text();
					if (_flowId != null && _flowId != ''){
						var _staffno = $(".row .searh .buttonCls.update").attr("staffno");
						var options = 'height=' +  (window.screen.availHeight-30) + ', width=' +  (window.screen.availWidth-10) + ', top=0,left=0,resizable=yes,status=no,menubar=no,scrollbars=yes,toolbar=no, menubar=no, location=no';
						window.open("../FlowWebDesign/design?flowId="+_flowId+"&staffno="+_staffno,_flowId,options);
					}
				});
				if (data.showLaypage){
					$(".content #pageTools").empty();
					WebUtil.laypage("pageTools",data.pageNum,data.pageSize,data.counts,
							"POST","../FlowWebDesign/getWkList",
							{"wkId":$(".row #wkId").val(),
								"wkName":$(".row #wkName").val(),
								"wkRemark":$(".row #wkRemark").val()}
							,managerPage.success,true);
				}
			}
		},
		tempTbody : ['{{if datas!=null && datas.length>0}}',
			'{{each datas as value index}}',
			'<tr class="{{if index%2==0}}interleave{{/if}}"><td class="center"><input type="radio" id="radioFlowId" name="radioFlowId" value="{{= value.wkId }}"></td><td>{{= value.wkId }}</td><td>{{= value.wkName }}</td>',
			'<td>{{= value.wkRemark }}</td><td>{{= value.createrId }}</td><td>{{= value.updaterId }}</td>',
			'<td class="center">{{= value.wkState}}</td>',
			'<td class="center">{{= value.createDt }}</td><td class="center">{{= value.currVersion }}</td><td>{{= value.defForm }}</td></tr>',
			'{{/each}}',
			'{{else}}',
			'<tr><td colspan="10">无数据</td></tr>',
			'{{/if}}'
			].join(''),
		findBtnEvent : function (){
			
		}
	};
	managerPage.init();

});