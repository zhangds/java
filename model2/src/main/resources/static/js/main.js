/**
 * 
 */
 
$(function(){
	var tree_data = [{
        id : 1,
        text: '人员管理',
        state: 'closed',
        iconCls:'icon-sum',
        children: [{
            id : 11,
            iconCls:'icon-sum',
            text: '人员设置',
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
        data: tree_data,
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
    });
});