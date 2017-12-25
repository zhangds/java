/**
 * 
 */
layui.config({
	base : "js/"
}).use(['form','layer','jquery','laypage','laytpl'],function(){
	var form = layui.form(),
		layer = parent.layer === undefined ? layui.layer : parent.layer,
		laypage = layui.laypage,
		$ = layui.jquery;
	var laytpl = layui.laytpl;
	var index = layer.load(2, {
		  shade: [0.6,'#fff'] //0.1透明度的白色背景
		});
	var showList = {
			initialize : function (){
				this.show();
				this.showPage();
			},
			show : function (){
				var getTpl = userManagerList.innerHTML;
    			laytpl(getTpl).render("1", function(html){
    				user_manager_list.innerHTML = html;
    			});
    			
			},
			showPage : function (){
				laypage({
                    cont: 'userManagerList_page'
                    ,pages: 100
                    ,skin: '#5FB878'
                    ,skip: true
                    ,jump: function(obj, first){
                      if(!first){
                        layer.msg('第'+ obj.curr +'页');
                      }
                    }
                  });
			}
	};
	showList.initialize();
	layer.close(index);
});