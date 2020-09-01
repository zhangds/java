/**
 * 
 */
$(function(){
	/*var data = [{"id":"1",pId:"","name":"规则","open":true}
//				,{"id":"1-1",pId:"1","name":"<span style='color:blue;margin-right:0px;'>参数</span>.<span style='color:red;margin-right:0px;'>nameIsHTML</span>",iconSkin:"iconParam","open":true}
//				,{"id":"1-2",pId:"1","name":"<span style='color:blue;margin-right:0px;'>条件</span>.<span style='color:red;margin-right:0px;'>nameIsHTML</span>",iconSkin:"iconCondition","open":true}
//				,{"id":"1-3",pId:"1","name":"nameIsHTML",iconSkin:"iconAction","open":true}
				];*/
	ruleSet = {
			newNode : {},
			selecNode : {},
			ruleTypeSetIndex : null,
			ruleInfoPageIndex : null,
			deleteNode : function (_idNo,_idPid,zTree){
				WebUtil.ajax("POST","../FlowRule/deleCurrentRule",
						{"r":new Date().getTime(),"staffno":workGroupInfo.staffno,
					    "flowId":workGroupInfo.flowId ,"nodeId":workGroupInfo.nodeId,
					    "ruleId":_idNo,"opType":"ruleNode"},
					    function(){alert('删除成功!');},true,
					    ruleSet.ztreeRefresh,function(){alert('删除失败!');});
			},
			zTreeFreshNode : function (ruleId,p1,p2){
				//alert(ruleId+':'+p1+':'+p2);
				ruleSet.ztreeRefresh();
				if (ruleSet.ruleInfoPageIndex)
					layer.close(ruleSet.ruleInfoPageIndex);
			},
			ztreeRefresh : function (){
				WebUtil.ajax("POST","../FlowRule/refreshRule",
						{"r":new Date().getTime(),"staffno":workGroupInfo.staffno,
					    "flowId":workGroupInfo.flowId ,"nodeId":workGroupInfo.nodeId,
					    "opType":"ruleNode"},
					    function(datas){
					    	$.fn.zTree.destroy(".ruleSetLists #ruleTree");
					    	workGroupInfo.currentNodeRules=datas;
					    	ruleSetArea.init();
					    },true);
			},
			ztreeDblClick : function (event, treeId, treeNode, clickFlag){
				if (treeNode.id === '1')
					return ;
				//console.info(treeNode);
				if (treeNode &&treeNode.iconSkin){
					ruleSet.ruleInfoPageIndex=
						WebUtil.layerOpen(false,[ '650px','480px' ],
							'../FlowRule/setParamRule?staffno='+workGroupInfo.staffno+
							'&flowId='+workGroupInfo.flowId+'&nodeId='+workGroupInfo.nodeId+
							'&ruleId='+treeNode.id+'&ruleType='+treeNode.iconSkin+
							'&opType=ruleNode');
				}
			},
			setNodeName : function(newNode){
				if (newNode && newNode.iconSkin){
					if (newNode.p1 || newNode.p2){
						switch (newNode.iconSkin) {
						case 'iconParam':
							newNode.name = "<span style='color:#2160d7;margin-right:0px;'>" +
									"参数</span>:<span style='color:#1F6F19;margin-right:0px;'>" +
									newNode.p1+"</span>";
							break;
						case 'iconCondition':
							if (newNode.p2 && newNode.p2.length >20)
								newNode.p2 = newNode.p2.substring(0,20)+"...";
							newNode.name = "<span style='color:#2160d7;margin-right:0px;'>" +
									"条件</span>:<span style='color:#C7092D;margin-right:0px;'>"+
									newNode.p1+"</span>"+
									"<span style='color:#1F6F19;margin-right:0px;'>" +
									newNode.p2+"</span>";
							break;
						case 'iconAction':
							if (newNode.p2 && newNode.p2.length >20)
								newNode.p2 = newNode.p2.substring(0,20)+"...";
							newNode.name = "<span style='color:#2160d7;margin-right:0px;'>" +
									"优先级:("+newNode.p3+")行为</span>:<span style='color:#C7092D;margin-right:0px;'>"+
									newNode.p1+"</span>"+
									"<span style='color:#1F6F19;margin-right:0px;'>" +
									newNode.p2+"</span>";
							break;
						default:
							break;
						}
					}else{
						switch (newNode.iconSkin) {
						case 'iconParam':
							newNode.name = "<span style='color:#2160d7;margin-right:0px;'>" +
									"参数</span>:<span style='color:red;margin-right:0px;'>" +
									"待编辑...</span>";
							break;
						case 'iconCondition':
							newNode.name = "<span style='color:#2160d7;margin-right:0px;'>" +
							"条件</span>:<span style='color:red;margin-right:0px;'>" +
							"待编辑...</span>";
							break;
						case 'iconAction':
							newNode.name = "<span style='color:#2160d7;margin-right:0px;'>" +
							"行为</span>:<span style='color:red;margin-right:0px;'>" +
							"待编辑...</span>";
							break;
						default:
							break;
						}
					}
				}
				return newNode;
			},
			addNodeEle : function (ruleType){
				if (ruleSet.newNode && ruleSet.newNode.id &&
						ruleSet.selecNode && ruleSet.selecNode.id){
					ruleSet.newNode.iconSkin = ruleType;
					ruleSet.newNode = ruleSet.setNodeName(ruleSet.newNode);
					var _zTree = $.fn.zTree.getZTreeObj("ruleTree");
					_zTree.addNodes(ruleSet.selecNode, ruleSet.newNode);
				}
			},
			tipHtml : ['<ul style="list-style-type: none;"><li optType="childAdd" idNo="{{= data.id }}" idPid="{{= data.pId }}">添加子级节点</li>',
				'{{if data!=null && data.pId != null && data.pId != ""}}',
				'<li optType="levelAdd" idNo="{{= data.id }}" idPid="{{= data.pId }}">添加同级节点</li>',
				//'<li optType="update" idNo="{{= data.id }}" idPid="{{= data.pId }}">修改</li>',
				'<li optType="del" idNo="{{= data.id }}" idPid="{{= data.pId }}">删除</li>{{/if}}</ul>'].join(""),
			addHoverDom : function (treeId, treeNode){
				//layer.closeAll('tips');
				var sObj = $("#" + treeNode.tId + "_span");
	            if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
	                + "' title='编辑' onfocus='this.blur();'></span>";
	            sObj.after(addStr);
	            var btn = $("#addBtn_"+treeNode.tId);
	            if (btn) btn.bind("click", function(){
	            	layer.tips(WebUtil.template(ruleSet.tipHtml,{"data":treeNode}), "#addBtn_"+treeNode.tId,{
	            		  tips: [2, '#3595CC'],
	            		  tipsMore: false,
	            		  id:"ruleSetTips",
	            		  time: 3000
	            	});
	            	$("#ruleSetTips.layui-layer-content ul li").off("click").on("click",function(){
	            		var _type = $(this).attr('optType');
	            		var _idPid = $(this).attr('idPid');
	            		var _idNo = $(this).attr('idNo');
	            		var zTree = $.fn.zTree.getZTreeObj("ruleTree");
	            		switch(_type) {
	            	     case 'update':
	            	    	 //取消
	            	    	 break;
	            	     case 'del':
	            	    	 ruleSet.deleteNode(_idNo,_idPid,zTree);
	            	    	 break;
	            	     default:
	            	    	 //console.log(zTree.getNodesByParam('pId',_idNo));
	            	    	 var _newTreeNode = ruleSet.getNewTreeNode(_idNo,_idPid,zTree,_type);
	            	     	 break;
	            		}
	            	});
	            });
			},removeHoverDom : function (treeId, treeNode){
				$("#addBtn_"+treeNode.tId).unbind().remove();
			},getNewTreeNode : function (oldId,oldPid,treeNodes,type){
				var _pnodes = null;
				ruleSet.selecNode = {};
				var _treeNode = [];
				ruleSet.newNode = {"open":true};
				ruleSet.newNode.pId = oldId;
				if (type == 'childAdd'){
					_treeNode = treeNodes.getNodesByParam("id",oldId);
					_pnodes = treeNodes.getNodesByParam('pId',oldId);
				}else{
					_treeNode = treeNodes.getNodesByParam("id",oldPid);
					_pnodes = treeNodes.getNodesByParam('pId',oldPid);
					ruleSet.newNode.pId = oldPid;
				}
				ruleSet.newNode.id = ruleSet.getNewTreeNodeId(_pnodes);
				//console.log(ruleSet.newNode);
				ruleSet.newNode.name='****';
				if ( Array.isArray(_treeNode) && _treeNode.length > 0 ){
					ruleSet.selecNode = _treeNode[0];
					//treeNodes.addNodes(_treeNode[0], ruleSet.newNode);
					ruleSet.ruleTypeSetIndex=WebUtil.layerOpen("节点类型设置",[ '400px','200px' ],
							'../FlowRule/setNodeType?staffno='+workGroupInfo.staffno+
							'&flowId='+workGroupInfo.flowId+
							'&nodeId='+workGroupInfo.nodeId+
							'&ruleId='+ruleSet.newNode.id+
							'&rulePid='+ruleSet.newNode.pId);
				}
			},getNewTreeNodeId : function (nodes,count){
				if (count == undefined )
					count = 1;
				if ( Array.isArray(nodes) ){
					var _id = ruleSet.newNode.pId + '_' +
					(nodes.length + count);
					for (var _i=0;_i<nodes.length;_i++){
						if (nodes[_i].id == _id){
							count +=1; 
							ruleSet.getNewTreeNodeId(nodes,count);
							break;
						}
					}
					return _id;
				}
				return null;
			}};
	ruleSetArea = {
		init : function (){
			var _ruleTreedata = [{"id":"1",pId:"","name":"规则","open":true}];
			//_ruleTreedata.push(workGroupInfo.currentNodeRules);
			var _node = null;
			for (var _i=0;_i<workGroupInfo.currentNodeRules.length;_i++){
				_node = workGroupInfo.currentNodeRules[_i];
				_node.open=true;
				_node = ruleSet.setNodeName(_node)
				_ruleTreedata.push(_node);
			}
			$.fn.zTree.init($(".ruleSetLists #ruleTree"),
					ruleSetArea.ztreeSetting, _ruleTreedata);
		},
		ztreeSetting : {
			view: {
				dblClickExpand: false,
                addHoverDom: ruleSet.addHoverDom,
                removeHoverDom: ruleSet.removeHoverDom,
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
    			//beforeClick: beforeClick,onDblClick:
            	onDblClick: ruleSet.ztreeDblClick
    		},
        edit: {
            enable: false
        }}
	};
	ruleSetArea.init();
});