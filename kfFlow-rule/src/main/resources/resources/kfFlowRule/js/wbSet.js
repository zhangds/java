$(function(){
	
	wbSet = {
		sysInfos : [],
		getAllWbDatas : [],
		__init__ : function (){
			WebUtil.ajax("POST","../FlowRule/getAllWbConfigs",
						{"r":new Date().getTime(),"staffno":workGroupInfo.staffno,
						"flowId":workGroupInfo.flowId,"nodeId":workGroupInfo.nodeId},
					    wbSet.renderSelect,true,
					    function(){},function(){alert('初始化外部接口参数错误!');});
			$("#wbSet .content .row .spanTitle > .searh > .buttonCls.saveWbSet").off("click")
			.on("click",function(){
				var _pdIds = $("#wbSet .content .row .spanEle input#wbPdIds").val();
				var _sysId = $("#wbSet .content .row .spanEle select#wbSysSelect").val();
				var _mothodId = $("#wbSet .content .row .spanEle select#wbSysMothodSelect").val();
				if (!_sysId){
					alert('选择系统!');
					return ;
				}
				if (!_mothodId){
					alert('选择方法!');
					return ;
				}
				var flag = false;
				var _currentData = null ;
				for (var i=0;i<wbSet.getAllWbDatas.length;i++){
					if (wbSet.getAllWbDatas[i].id == _sysId &&
						wbSet.getAllWbDatas[i].md == _mothodId){
						_currentData = wbSet.getAllWbDatas[i];
						flag = true;
						break;
					}
				}
				if (!flag){
					alert('系统和方法不匹配!');
					return ;
				}
				if (_currentData){
					WebUtil.ajax("POST","../FlowRule/setCurrentNodeWbEx",
						{"r":new Date().getTime(),"staffno":workGroupInfo.staffno,
						 "flowId":workGroupInfo.flowId,"nodeId":workGroupInfo.nodeId,
						 "sysId":_currentData.id,"mothodId":_currentData.md,
						 "classZ":_currentData.path,"pdIds" : _pdIds
						},
					    wbSet.okEvent,true,
					    function(){},function(){alert('初始化外部接口参数错误!');});
				}
			});
		},
		okEvent : function (data){
			if (data && data.flag){
				alert('保存成功!');
			}else{
				alert('保存失败!');
			}
		},
		renderSelect : function (data){
			var _datas = [];
			if (data && data.configs && data.configs.length >0){
				wbSet.getAllWbDatas = data.configs;
				for (var i=0;i<data.configs.length;i++){
					_datas.push({"key":data.configs[i].md,"value":data.configs[i].mdName});
					if (data.configs[i].id && data.configs[i].name){
						console.log(data.configs[i]);
						if (wbSet.sysInfos && wbSet.sysInfos.length ==0){
							wbSet.sysInfos.push({"key":data.configs[i].id,"value":data.configs[i].name});
						}else {
							var _flag = true;
							for (var y=0;y<wbSet.sysInfos.length;y++){
								if (wbSet.sysInfos[y].key == data.configs[i].id){
									flag = false;
									break;
								}
							}
							if (flag){
								wbSet.sysInfos.push({"key":data.configs[i].id,"value":data.configs[i].name});
							}else{
								flag = true;
							}
						}
					}
				}
			}
			$("#wbSet .content .row .spanEle select#wbSysSelect").empty()
				.html(WebUtil.template(wbSet.selectHtml,{"datas":wbSet.sysInfos}));

			$("#wbSet .content .row .spanEle select#wbSysMothodSelect").empty()
				.html(WebUtil.template(wbSet.selectHtml,{"datas":_datas}));

			if (data && data.set && data.set.length ==3 && data.set[0] && data.set[1] && data.set[2]){
				$("#wbSet .content .row .spanEle input#wbPdIds").val(data.set[2]);
				$("#wbSet .content .row .spanEle select#wbSysSelect").val(data.set[0]);
				$("#wbSet .content .row .spanEle select#wbSysMothodSelect").val(data.set[1]);
			}
			$("#wbSet .content .row .spanEle select#wbSysSelect").off("change").on("change",function(){
					var _key = $(this).children('option:selected').val();
					if (_key && _key != ""){
						wbSet.readerElement(_key);
					}
				});	
		},
		readerElement : function (keyId){
			var _datas = [];
			if (wbSet.getAllWbDatas && wbSet.getAllWbDatas.length >0){
				for (var i=0;i<wbSet.getAllWbDatas.length;i++){
					if (wbSet.getAllWbDatas[i] && wbSet.getAllWbDatas[i].id==keyId){
						_datas.push({"key":wbSet.getAllWbDatas[i].md,"value":wbSet.getAllWbDatas[i].mdName});
					}
				}
			}
			$("#wbSet .content .row .spanEle select#wbSysMothodSelect").empty()
				.html(WebUtil.template(wbSet.selectHtml,{"datas":_datas}));
		},
		selectHtml : [
					'<option value="">请选择...</option>',
					"{{if datas != null && datas.length>0}}",
						'{{each datas as value index}}',
						'<option value="{{=value.key}}">{{=value.value}}</option>',
						'{{/each}}',
					"{{/if}}"].join("")
	};
	wbSet.__init__();
});