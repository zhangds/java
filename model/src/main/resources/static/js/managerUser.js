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
				this.selectAll();
			},
			pageNo : 1,
			likeKey : '',
			show : function (){
				var _data = {"pageNo":this.pageNo};
				if ( this.likeKey != '' ){
					_data.likeKey = this.likeKey;
				}
				
    			$.ajax({
    			    url:'../user/getUserList',
    			    type:'POST', //GET
    			    async:true,    //或false,是否异步
    			    data:_data,
    			    timeout:2000,    //超时时间
    			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
    			    success:function(data,textStatus,jqXHR){
    			        if (data.flag == true){
    			        	var getTpl = userManagerList.innerHTML;
    		    			laytpl(getTpl).render(data.page, function(html){
    		    				user_manager_list.innerHTML = html;
    		    			});
    		    			if (showList.pageNo ==1){
    		    				showList.showPage(data.page.pages);
    		    			}
    		    			//添加事件
    		    			showList.editEvent();
    		    			showList.initPwdEvent();
    			        	form.render();
    			        }else{
    			        	layer.msg(data.msg, {icon: 5});
    			        }
    			    }
    			});
			},
			showPage : function (pages){
				laypage({
                    cont: 'userManagerList_page'
                    ,pages: pages
                    ,skin: '#5FB878'
                    ,skip: true
                    ,jump: function(obj, first){
                      if(!first){
                    	  showList.pageNo = obj.curr;
                    	  showList.show();
                    	  //layer.msg('第'+ obj.curr +'页');
                      }
                    }
                  });
			},
			selectAll : function (){
				//全选
				form.on('checkbox(allChoose)', function(data){
					var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]:not([name="show"])');
					child.each(function(index, item){
						item.checked = data.elem.checked;
					});
					form.render('checkbox');
				});
			},
			getSelectOpt : function (){
				var $checked = $('#userManagerList_content input[type="checkbox"][name="selectOne"]:checked');
				var _checkedId = new Array();
				for(var j=0;j<$checked.length;j++){
					_checkedId.push($checked.eq(j).parents("tr").eq(0).find("td").eq(2).html());
				}
				return _checkedId;
			},
			editEvent : function (){
				$(".userManagerList_content td .layui-btn-group [option='editUserOne']").unbind();
				$(".userManagerList_content td .layui-btn-group [option='editUserOne']").click(function(){
					layer.open({
					      type: 2,
					      title: '修改用户信息',
					      shadeClose: true,
					      shade: false,
					      maxmin: true, //开启最大化最小化按钮
					      area: ['893px', '650px'],
					      content: './user/userAddAndEdit?userId='+$(this).attr("oppara"),
					      end : function (){
					    	  showList.show();
					      }
					    });
				});
			},
			initPwdEvent : function (){
				$(".userManagerList_content td .layui-btn-group [option='initPwdUserOne']").unbind();
				$(".userManagerList_content td .layui-btn-group [option='initPwdUserOne']").click(function(){
					var _id = $(this).attr("oppara");
					layer.prompt({title: '输入密码', formType: 1},function(val, index){
						  //layer.msg(_id+'得到了'+val);
						  $.ajax({
							    url:'../user/actChangeOnePwd',
							    type:'POST', //GET
							    async:true,    //或false,是否异步
							    data:{"loginId":_id,"pwd":val},
							    timeout:2000,    //超时时间
							    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
							    success:function(data,textStatus,jqXHR){
//							    	if (data.success == true){
//							    		layer.msg("修改密码成功!", {icon: 6});
//							    	}else{
//							    		layer.msg("修改密码失败!", {icon: 5});
//							    	}
							    	layer.msg(data.msg, {icon: data.success==true?6:5});
							    },
							    complete: function(){
							    	layer.close(index);
							    }
							});
						  
					});
				});
			}
	};
	showList.initialize();
	
	$(".user_manager_search .search_btn").click(function(){
		var _index = layer.msg('查询中，请稍候',{icon: 16,time:false,shade:0.8});
		var _likeKey = $("#likeKey").val();
		if ( $("#likeKey").val() != '' || (showList.likeKey != '' && _likeKey=='') ){
			showList.pageNo = 1;
			showList.likeKey = _likeKey;
			showList.show();
		}
		layer.close(_index);
	});
	
	$(".user_manager_search .addUser").click(function(){
		var _index = layer.msg('添加',{icon: 16,time:false,shade:0.8});
		layer.open({
		      type: 2,
		      title: '添加用户信息',
		      shadeClose: true,
		      shade: false,
		      maxmin: true, //开启最大化最小化按钮
		      area: ['893px', '650px'],
		      content: './user/userAddAndEdit',
		      end : function (){
		    	  showList.show();
		      }
		    });
		layer.close(_index);
	});
	
	$(".user_manager_search .batchOpenUser").click(function(){
		var _index = layer.msg('开启',{icon: 16,time:false,shade:0.8});
		var _checkedId = showList.getSelectOpt();
		if (_checkedId.length>0){
			$.ajax({
			    url:'../user/userLocked/on',
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    data:{"ids":_checkedId},
			    timeout:2000,    //超时时间
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data,textStatus,jqXHR){
			    	layer.msg(data.msg, {icon: 5});
			    }
			});
			showList.show();
		}
		layer.close(_index);
	});
	
	$(".user_manager_search .batchCloseUser").click(function(){
		var _index = layer.msg('锁定',{icon: 16,time:false,shade:0.8});
		var _checkedId = showList.getSelectOpt();
		if (_checkedId.length>0){
			$.ajax({
			    url:'../user/userLocked/close',
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    data:{"ids":_checkedId},
			    timeout:2000,    //超时时间
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data,textStatus,jqXHR){
			    	layer.msg(data.msg, {icon: 5});
			    }
			});
			showList.show();
		}
		layer.close(_index);
	});
	
	$(".user_manager_search .batchDelUser").click(function(){
		var _index = layer.msg('删除',{icon: 16,time:false,shade:0.8});
		var _checkedId = showList.getSelectOpt();
		if (_checkedId.length>0){
			$.ajax({
			    url:'../user/deleteUser',
			    type:'POST', //GET
			    async:true,    //或false,是否异步
			    data:{"ids":_checkedId},
			    timeout:2000,    //超时时间
			    dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
			    success:function(data,textStatus,jqXHR){
			    	layer.msg(data.msg, {icon: 5});
			    }
			});
			showList.show();
		}
		layer.close(_index);
	});
	layer.close(index);
});