/**
 * 
 */
$(function(){
	backForm = {
		__init__ : function (){
			WebUtil.ajax("POST","../FlowRule/getBackFormInfo",
						{"r":new Date().getTime(),
						"staffno":workGroupInfo.staffno,
					    "flowId":workGroupInfo.flowId ,
					    "nodeId":workGroupInfo.nodeId
					    },
					    this.initSuccessEvent,true,
					    function(){},
					    function(){
					    	alert('初始化回单数据失败!');
					    });
			$(".wrap label#backFormSet > div.content .row > .spanEle > select#backFormId")
				.off("change").on("change",function(){
					var _key = $(this).children('option:selected').val();
					if (_key && _key != ""){
						backForm.readerElement(_key);
					}
				});
			$(".wrap label#backFormSet > div.content div.row > .spanTitle > .searh > .saveBackForms")
				.off("click").on("click",function(){
					var _data = {};
					_data.backFormId = $(".wrap label#backFormSet > div.content .row > .spanEle > select#backFormId").val();
					if (_data.backFormId && _data.backFormId != ''){
						_data.staffno = workGroupInfo.staffno;
						_data.flowId = workGroupInfo.flowId;
						_data.nodeId = workGroupInfo.nodeId;
						var _list = $(".wrap label#backFormSet > div.content .row table#backFormTable tbody").find("tr");
						_data.backFromEle = JSON.stringify(backForm.getTableLinesData(_list));
						_list = $(".wrap label#backFormSet > div.content .row table#addZRDXTable tbody").find("tr");
						_data.zrdxEle = JSON.stringify(backForm.getTableLinesData(_list));
						_data.r = new Date().getTime();
						WebUtil.ajax("POST","../FlowRule/saveBackForms",
							_data,
						    function(){alert('保存成功!')},true,
						    function(){},
						    function(){
						    	alert('保存失败!');
						 });
					 }
				});
		},
		getTableLinesData : function (_list){
			var _result = [];
			if (_list && _list.length >0){
				for (var _i = 0 ;_i<_list.length;_i++){
					if ($(_list[_i]).length != 1){
					}else{
						var _kj = $(_list[_i]).find("input[name='isKj']:checked");
						if (_kj && _kj.length == 1){
							_kj = 'Y';
						}else{
							_kj = 'N';
						}
						var _bt = $(_list[_i]).find("input[name='isBt']:checked");
						if (_bt && _bt.length == 1){
							_bt = 'Y';
						}else{
							_bt = 'N';
						}
						_result.push({"id":$(_list[_i]).attr("paramId"),"name":$(_list[_i]).attr("paramName"),"isKj":_kj,"isBt":_bt});
					}
				}
			}
			return _result;
		},
		selectHtml : [ "{{ if datas != null && datas.length >0 }}",
							'{{each datas as value index}}',
								'<option value="{{=value[0]}}">{{=value[1]}}</option>',
							'{{/each}}',
		 			   "{{ /if }}"
			].join(""),
		initSuccessEvent : function (data){
			if (data ){ 
				if (data.allBackForms && data.allBackForms.length >0)
					$(".wrap label#backFormSet > div.content .row > .spanEle > select#backFormId")
						.append(WebUtil.template(backForm.selectHtml,{"datas":data.allBackForms}));
				if (data.backForms && data.backForms.length >0){
					if (data.backForms[0].bkId)
						$(".wrap label#backFormSet > div.content .row > .spanEle > select#backFormId").val(data.backForms[0].bkId);
					backForm.initbackFormElementSuccessEvent(data.backForms);
				}
				if (data.addDzForms && data.addDzForms.length >0)
					backForm.initaddDzElementSuccessEvent(data.addDzForms);
				backForm.addEvent();
			}
		},
		readerElement : function(keyId){
			WebUtil.ajax("POST","../FlowRule/getBackFormInfoById",
						{"r":new Date().getTime(),
						"staffno":workGroupInfo.staffno,
					    "flowId":workGroupInfo.flowId ,
					    "nodeId":workGroupInfo.nodeId ,
					    "backFormId" : keyId
					    },
					    this.initbackFormElementSuccessEvent,true,
					    function(){},
					    function(){
					    	alert('获取回单模板元素错误!');
					    });
		},
		initbackFormElementSuccessEvent : function (data){
			$(".wrap label#backFormSet > div.content .row table#backFormTable tbody").empty()
						.append(WebUtil.template(backForm.backFormElementHtml,{"datas":data}));
			backForm.addEvent();
		},
		initaddDzElementSuccessEvent : function (data){
			$(".wrap label#backFormSet > div.content .row table#addZRDXTable tbody").empty()
						.append(WebUtil.template(backForm.backFormElementHtml,{"datas":data}));
		},
		addEvent : function (){
			$(".wrap label#backFormSet > div.content .row table#backFormTable tbody > tr > td > .buttonCls,\
				.wrap label#backFormSet > div.content .row table#addZRDXTable tbody > tr > td > .buttonCls")
						.off("click").on("click",function(){
							var _keyId = $(this).attr("paramId");
							var _trDiv = $(this).parents("tr");
							if (_trDiv && _trDiv.length == 1){
								var _isSet = _trDiv.find("input[name='"+_keyId+"']:checked");
								if (_isSet && _isSet.length == 1){
									WebUtil.layerOpen(false,[ '670px','520px' ],
										'../FlowRule/setAllRule?staffno='+workGroupInfo.staffno+
										'&flowId='+workGroupInfo.flowId+'&nodeId='+workGroupInfo.nodeId+
										'&lineId='+_trDiv.attr("paramId")+'&opType='+_keyId);
	
								}else{
									alert("未开启设置开关!");
								}
							}
						});
		},
		backFormElementHtml : [
			'{{if datas != null && datas.length >0}}',
				'{{each datas as value index}}',
					'<tr {{if index%2==0}}class="interleave"{{/if}} paramId="{{=value.id}}" paramName="{{=value.name}}" paramType="{{=value.type}}">',
						'<td>{{=value.name}}</td>',
						'<td style="text-align: center;"><input type="checkbox" name="isKj" {{if value.isKj=="Y"}}checked="checked"{{/if}}></td>',
						'<td style="text-align: center;"><div class="buttonCls" paramId="isKj">配置</div></td>',
						'<td style="text-align: center;"><input type="checkbox" name="isBt" {{if value.isBt=="Y"}}checked="checked"{{/if}}></td>',
						'<td style="text-align: center;"><div class="buttonCls" paramId="isBt">配置</div></td>',
					'</tr>',
				'{{/each}}',
			'{{/if}}'
		].join("")

	};
	backForm.__init__();
});