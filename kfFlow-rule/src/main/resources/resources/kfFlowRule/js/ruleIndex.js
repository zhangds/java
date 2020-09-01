/**
 * 
 */
$(function(){
	ruleTreeConfigs = {
		newNode : {},
		selectNode : {},
		ruleInfoPageIndex : null,
		tipHtml : ['<ul style="list-style-type: none;"><li optType="childAdd" idNo="{{= data.id }}" idPid="{{= data.pId }}">添加子级节点</li>',
				'{{if data!=null && data.pId != null && data.pId != ""}}',
				'<li optType="levelAdd" idNo="{{= data.id }}" idPid="{{= data.pId }}">添加同级节点</li>',
				'<li optType="del" idNo="{{= data.id }}" idPid="{{= data.pId }}">删除</li>{{/if}}</ul>'].join(""),
		addHoverDom : function (treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
	        if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
	                + "' title='操作' onfocus='this.blur();'></span>";
	        sObj.after(addStr);
	        var btn = $("#addBtn_"+treeNode.tId);
	        if (btn)
	        	btn.bind("click",function (){
	        		layer.tips(WebUtil.template(ruleTreeConfigs.tipHtml,{"data":treeNode}), "#addBtn_"+treeNode.tId,{
	            		  tips: [2, '#3595CC'],
	            		  tipsMore: false,
	            		  id:"ruleIndexPageTips",
	            		  time: 3000
	            	});

	            	$("#ruleIndexPageTips.layui-layer-content ul li").off("click").on("click",function(){
	            		var _type = $(this).attr('optType');
	            		var _idPid = $(this).attr('idPid');
	            		var _idNo = $(this).attr('idNo');
	            		if (ruleIndexPage.zTreeId){
	            			var _treeId = ruleIndexPage.zTreeId.substring(ruleIndexPage.zTreeId.lastIndexOf("#")+1,ruleIndexPage.zTreeId.length);
	            			var _zTree = $.fn.zTree.getZTreeObj(_treeId);
	            			switch(_type) {
		            			case 'del':
			            	    	ruleTreeConfigs.deleteNode(_idNo,_idPid,_zTree);
			            	    	break;
			            	    default:
			            	    	ruleTreeConfigs.setNewTreeNode(_idNo,_idPid,_zTree,_type);
			            	     	break;
	            			}
	            		}

	            	});
	        	});
		},
		deleteNode : function (_idNo,_idPid,_zTree){
			WebUtil.ajax("POST","../FlowRule/deleCurrentRule",
						{"r":new Date().getTime(),"staffno":ruleIndexPage.runPageInfo.staffno,
					    "flowId":ruleIndexPage.runPageInfo.flowId ,"nodeId":ruleIndexPage.runPageInfo.nodeId,
					    "ruleId":_idNo,"opType":ruleIndexPage.runPageInfo.opType},
					    function(){alert('删除成功!');},true,
					    this.zTreeFreshNode,function(){alert('删除失败!');});
		},
		setNewTreeNode : function (cid,cpid,treeNodes,type){
			if (type){
				var _ptreeNode = null,_selectNodes=null;
				this.newNode = {"open":true};
				if ( type == 'childAdd' ){
					_selectNodes = treeNodes.getNodesByParam("id",cid);
					_ptreeNode = treeNodes.getNodesByParam("pId",cid);
					this.newNode.pId = cid;
				}else{
					_selectNodes = treeNodes.getNodesByParam("id",cpid);
					_ptreeNode = treeNodes.getNodesByParam("pId",cpid);
					this.newNode.pId = cpid;
				}
				if ( _ptreeNode ){
					this.selectNode = (_selectNodes && _selectNodes.length >0 ? _selectNodes[0] : null);
					this.newNode.id = this.getNewTreeNodeId(_ptreeNode,ruleTreeConfigs.newNode.pId);
					//console.info(ruleTreeConfigs.newNode.id);
					this.newNode.name='****';
					this.ruleTypeSetIndex=WebUtil.layerOpen("节点类型设置",[ '400px','200px' ],
							'../FlowRule/setNodeType?staffno='+ruleIndexPage.runPageInfo.staffno+
							'&flowId='+ruleIndexPage.runPageInfo.flowId+
							'&nodeId='+ruleIndexPage.runPageInfo.nodeId+
							'&lineId='+ruleIndexPage.runPageInfo.lineId+
							'&opType='+ruleIndexPage.runPageInfo.opType+
							'&ruleId='+this.newNode.id+
							'&rulePid='+this.newNode.pId);
				}
			}
		},
		getNewTreeNodeId : function (nodes,pid,count){
			if ( count == undefined )
				count = 1;
			if ( pid && nodes && Array.isArray(nodes) ){
				var _id = pid +'_' + (nodes.length + count);
				for (var _i=0;_i<nodes.length;_i++){
					if (nodes[_i].id == _id){
						this.getNewTreeNodeId(nodes,count++);
						break;
					}
				}
				return _id;
			}
			return null;
		},
		removeHoverDom : function (treeId, treeNode) {
			$("#addBtn_"+treeNode.tId).unbind().remove();
		},
		ztreeDblClick : function (event, treeId, treeNode, clickFlag){
			if (treeNode && treeNode.id != '1'){
				if (treeNode.iconSkin){
					ruleTreeConfigs.ruleInfoPageIndex = WebUtil.layerOpen(false,[ '650px','480px' ],
							'../FlowRule/setParamRule?staffno='+ruleIndexPage.runPageInfo.staffno+
							'&flowId='+ruleIndexPage.runPageInfo.flowId+
							'&nodeId='+ruleIndexPage.runPageInfo.nodeId+
							'&lineId='+ruleIndexPage.runPageInfo.lineId+
							'&ruleId='+treeNode.id+
							'&ruleType='+treeNode.iconSkin+
							'&opType='+ruleIndexPage.runPageInfo.opType);
				}	
			}
		},
		zTreeFreshNode : function (){
			WebUtil.ajax("POST","../FlowRule/refreshRule",
						{"r":new Date().getTime(),"staffno":ruleIndexPage.runPageInfo.staffno,
					    "flowId":ruleIndexPage.runPageInfo.flowId ,
					    "nodeId":ruleIndexPage.runPageInfo.nodeId,
					    'lineId':ruleIndexPage.runPageInfo.lineId,
					    "opType":ruleIndexPage.runPageInfo.opType},
					    function(datas){
					    	$.fn.zTree.destroy(ruleIndexPage.zTreeId);
					    	ruleIndexPage.runPageInfo.currentRules = datas;
					    	ruleIndexPage.__init__(ruleIndexPage.runPageInfo,ruleIndexPage.zTreeId);
					    },true);
		},
		addNodeEle : function (ruleType){
			if (this.newNode && this.newNode.id && this.selectNode && this.selectNode.id){
				this.newNode.iconSkin = ruleType;
				this.newNode = this.setTreeNode(this.newNode);
				var _treeId = ruleIndexPage.zTreeId.substring(ruleIndexPage.zTreeId.lastIndexOf("#")+1,ruleIndexPage.zTreeId.length);
	            var _zTree = $.fn.zTree.getZTreeObj(_treeId);
	            _zTree.addNodes(this.selectNode, this.newNode);
	            this.selectNode = null;
	            this.newNode = null;
			}
		},
		titleHTML : [
			'{{if data!=null && data.iconSkin != null}}',
				'{{if data.iconSkin=="iconParam"}}',
					'{{if data.p1 != null}}',
						"<span style='color:#2160d7;margin-right:0px;'>参数</span>:<span style='color:#1F6F19;margin-right:0px;'>{{= data.p1 }}</span>",
					'{{else}}',
						"<span style='color:#2160d7;margin-right:0px;'>参数</span>:<span style='color:red;margin-right:0px;'>待编辑...</span>",
					'{{/if}}',
				'{{else if data.iconSkin=="iconCondition"}}',
					'{{if data.p1 != null && data.p2 != null}}',
						"<span style='color:#2160d7;margin-right:0px;'>条件</span>:<span style='color:#C7092D;margin-right:0px;'>{{=data.p1}}</span><span style='color:#1F6F19;margin-right:0px;'>{{if data.p2.length>=20}}{{=data.p2.substring(0,20)}}...{{else}}{{=data.p2}}{{/if}}</span>",
					'{{else}}',
						"<span style='color:#2160d7;margin-right:0px;'>条件</span>:<span style='color:red;margin-right:0px;'>待编辑...</span>",
					'{{/if}}',
				'{{else if data.iconSkin=="iconAction"}}',
					'{{if data.p1 != null && data.p2 != null && data.p3 != null}}',
						"<span style='color:#2160d7;margin-right:0px;'>优先级:({{=data.p3}})行为</span>:<span style='color:#C7092D;margin-right:0px;'>{{=data.p1}}</span><span style='color:#1F6F19;margin-right:0px;'>{{if data.p2.length>=20}}{{=data.p2.substring(0,20)}}...{{else}}{{=data.p2}}{{/if}}</span>",
					'{{else}}',
						"<span style='color:#2160d7;margin-right:0px;'>行为</span>:<span style='color:red;margin-right:0px;'>待编辑...</span>",
			'{{/if}}','{{/if}}','{{/if}}'
		].join(""),
		setTreeNode : function (oneNodeData){
			if (oneNodeData && oneNodeData.iconSkin){
				oneNodeData.open = true;
				oneNodeData.name = WebUtil.template(ruleTreeConfigs.titleHTML,{"data":oneNodeData});
			}
			return oneNodeData;
		}
	};
	ruleIndexPage = {
		__init__ : function (pageInfo,treeId){
			this.runPageInfo = pageInfo;
			this.zTreeId = treeId;
			var _ruleTreedata = [{"id":"1",pId:"","name":"规则","open":true}];
			var _dataNode = null ;
			if (this.runPageInfo.currentRules && this.runPageInfo.currentRules.length >0)
				for (var _i=0;_i<this.runPageInfo.currentRules.length;_i++){
					_dataNode = this.runPageInfo.currentRules[_i];
					_dataNode = ruleTreeConfigs.setTreeNode(_dataNode);
					_ruleTreedata.push(_dataNode);
				}
			$.fn.zTree.init($(this.zTreeId),
					ruleIndexPage.ztreeSetting, _ruleTreedata);
		},
		ztreeSetting : {
			view: {
				dblClickExpand: false,
                addHoverDom: ruleTreeConfigs.addHoverDom,
                removeHoverDom: ruleTreeConfigs.removeHoverDom,
                selectedMulti: false,
				nameIsHTML: true
            },
            check: {
                enable: false
            },data: {
				simpleData: {
					enable: true,
					idKey: "id",
			        pIdKey: "pId",
				}
            },
            callback: {
            	onDblClick: ruleTreeConfigs.ztreeDblClick
    		},
	        edit: {
	            enable: false
	        }
		}
		
	};
	ruleIndexPage.__init__(ruleIndexInfo,"body#ruleIndexBody .ruleIndex .row div#ruleZtree");
});