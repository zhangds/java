/**
 * 
 */
 
$(function(){
	var html = {
			template : function (source,jsonObj){
	    		var _temp = template.compile(source);
	    		return _temp(jsonObj);
	    	},
	    	accordionPanel : [
	    		"{{if data!=null && data.length>0}}",
	    		"<div class=\"easyui-accordion\" style=\"width:208px;height:auto;padding:0px;\">",
	    		'{{each data as value index}}',
	    		"<div title=\"{{value.text}}\" data-options=\"iconCls:'{{value.iconCls}}'\" style=\"padding:10px;\">",
	    		"{{if value.children != null && value.children.length>0 }}",
	    		"<ul id=\"menuTools_{{value.id}}\" class=\"easyui-tree\">",
	    		"</ul>",
	    		"{{/if}}",
	    		"</div>",
	    		 '{{/each}}',
	    		"</div>",
	    		 "{{/if}}"
	    	].join(""),
	    	transMenuData : function (data){
	    		if (data && data.id){
		    			var _o = {"id":data.id,"text":data.text,
		    				"text":data.text,"iconCls":data.iconCls};
		    			if (data.url && data.url !=""){
		    				_o.attributes = {"url":data.url};
		    			}
		    			if (data.children && data.children.length >0){
		    				_o.state = "closed";
		    				_o.children = [];
		    				for (var i=0;i<data.children.length;i++){
		    					_o.children.push(html.transMenuData(data.children[i]));
		    				}
		    			}
		    			return _o;
	    			}
	    		}
	    	
	};
	if (mainPage.menus && mainPage.menus.length >0){
		$(".main_body #menuTool").empty().html(html.template(html.accordionPanel,{'data':mainPage.menus}));
		$(".main_body .easyui-accordion").accordion();
		for (var i=0;i< mainPage.menus.length;i++){
			if ($(".main_body #menuTools_"+mainPage.menus[i].id).length>0){
				var _treeData = html.transMenuData(mainPage.menus[i]);
				console.log(_treeData);
				console.log(mainPage.menus);
				$(".main_body #menuTools_"+mainPage.menus[i].id).tree({
			        data: _treeData.children,
			        onClick:function(node)
			        {
			        	if (node.attributes){
			        		var tabTitle =node.text;
				            var id = node.id;
				            var url=node.attributes.url;
				            var icon = node.iconCls;
				            if(url){
				                //addTab(id,tabTitle, url, icon);
				            	alert(url);
				            }
			        	}
			        }
			    });
			}
		}
	}
	/*var tree_data = [{
        id : 1,
        text: '人员管理',
        state: 'closed',
        iconCls:'icon-sum',
        children: [{
            id : 11,
            iconCls:'icon-sum',
            text: '人员设置',
            state: 'closed',
            attributes : {
                url : '${path}/menuTree/index/success'
            }
        },{
            id : 12,
            text: '角色设置',
            attributes : {
                url : '${path}/menuTree/index/error'
            }
        }]
    },{
        id:2,
        text: '菜单管理',
        state: 'closed',
        children : [{
            id:21,
            text: '菜单配置',
            attributes : {
                url : '${path}/menuTree/index/error'
            }
        }]
    }];
    $('#sysPanel #sysMenu').tree({
        data: mainPage.menus,
        onClick:function(node)
        {
            var tabTitle =node.text;
            var id = node.id;
            var url=node.attributes.url;
            var icon = node.iconCls;
            if(url){
                //addTab(id,tabTitle, url, icon);
            	alert(url);
            }
        }
    });*/
});