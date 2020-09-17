/**
 * 
 */
$(function(){
	var infoPage = {
			init : function (){
				$(".row .searh .buttonCls.cancel").off("click").on("click",function(){
					infoPage.cancelEvent();
				});
				$(".row .searh .buttonCls.ok").off("click").on("click",function(){
					var datas = {};
					datas.staffno = $(this).attr("userId");
					datas.flowId = $("#wkId").val().replace(/(^\s*)|(\s*$)/g, "");
					datas.flowName = $("#wkName").val().replace(/(^\s*)|(\s*$)/g, "");
					datas.flowStates = $("#wkStates").val();
					datas.flowForm = $("#wkForm").val().replace(/(^\s*)|(\s*$)/g, "");
					datas.flowRemark = $("#wkRemark").val().replace(/(^\s*)|(\s*$)/g, "");
					datas.opType = $(this).attr("option");
					datas.version = $(this).attr("version");
					WebUtil.ajax("POST","../FlowWebDesign/WkOneSave",datas,
							infoPage.successEvent,true,infoPage.completeEvent,infoPage.errorEvent)
				});
			},
			cancelEvent : function (){
				if (window.parent && window.parent.layer){
					window.parent.layer.closeAll();
				}
			},
			successEvent : function (data){
				if (data && data.msg){
            		//layer.alert(data.msg, {title:"提示:",icon: 6});
					alert(data.msg);
            	}
			},
			completeEvent : function(){
				if (window.parent && window.parent.managerPage){
        			if (typeof window.parent.managerPage.find == 'function')
        				window.parent.managerPage.find();
        			if (window.parent.layer){
    					window.parent.layer.closeAll();
    				}
        		}
			},
			errorEvent :function (datas){
				if (datas.opType == "add"){
            		alert("新增失败!");
            	}else{
            		alert("修改失败!");
            	}
			}
	};
	
	infoPage.init();

});