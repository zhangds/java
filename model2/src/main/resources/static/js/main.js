/**
 * 
 */
 
$(function(){
	var html = {
			template : function (source,jsonObj){
	    		var _temp = template.compile(source);
	    		return _temp(jsonObj);
	    	},
			menuPanel : ["<div id=\"sysPanel\" class=\"easyui-panel\" title=\"系统管理\" style=\"width:208px;height:auto;padding:0px;\"",
				" data-options=\"iconCls:'icon-blank',collapsible:true\">",
				"<ul id=\"sysMenu\" class=\"easyui-tree\" ></ul>",
				"</div>"
				].join("")
	};
	if (mainPage.menus && mainPage.menus.length >0){
		$(".main_body #menuTool").empty();
		for (var i=0;i<mainPage.menus.length;i++){
			
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