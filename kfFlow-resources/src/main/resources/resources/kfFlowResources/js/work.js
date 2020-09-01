
$(function(){
	WebUtil = {
		template : function (source,jsonObj){
    		var _temp = template.compile(source);
    		return _temp(jsonObj);
    	},
    	/*ajax : function (type,url,datas,successFunction,isLoader = false,
    			completeFunction=function(){},errorFunction=function(){}){
    	ES6写法不支持客服chrome		*/
    	ajax : function (type,url,datas,successFunction,isLoader,
			completeFunction,errorFunction){
    		if (isLoader == undefined)
    			isLoader = false;
    		if (completeFunction == undefined)
    			completeFunction=function(){};
			if (errorFunction == undefined)
				errorFunction=function(){};
    		var _loaderIndex;
			$.ajax({
	            type: type,
	            url: url,
	            data: datas,
	            async: true,
	            cache:false,
	            success: function (data) {
	            	if(typeof(successFunction) == "function"){        
	            		successFunction(data);
	                  }
	            }, beforeSend : function (XMLHttpRequest) {
	            	if (isLoader)
	            		_loaderIndex = layer.load(2, {shade: false});
	           	}, complete : function(XMLHttpRequest,textStatus){
	           		if (isLoader)
	           			layer.close(_loaderIndex);
	           		if(typeof(completeFunction) == "function"){        
	           			completeFunction();
	                  }
	            },error : function (e){
	            	if(typeof(errorFunction) == "function"){        
	            		errorFunction(datas);
	                  }
	            }
			});
		},
		laypage : function (divId,pages,pageSize,countNum,
				jumpType,jumpUrl,jumpDatas,jumpSuccessFunction,jumpIsLoader){
			if (jumpIsLoader == undefined)
				jumpIsLoader = false;
			laypage({
			    cont: divId
			    ,pages: pages
			    ,limit: pageSize
			    ,skin: '#4476A7'//'#008675'
			    ,count: countNum
			    ,prev: '<em>上一页</em>'
			    ,next: '<em>下一页</em>'
		    	,jump: function(obj,first){
		    		if (!first && typeof(jumpDatas)=="object"){//
		    			jumpDatas.currentNum = (obj.curr-1)*obj.limit ;
		    			WebUtil.ajax(jumpType,jumpUrl,
		    					//{"wkId":wkId,"wkName":wkName,"wkRemark":wkRemark,"currentNum":(obj.curr-1)*obj.limit}
		    					jumpDatas
		    			,jumpSuccessFunction,jumpIsLoader);
		    		}
			    }
			  });
		},
		layerOpen : function (title,areaData,contentUrl){
			if (title == undefined)
				title = false;
			var _index = layer.open({
				type : 2,
				// title: '关闭',
				title : title,
				//area : [ pageOption.width, pageOption.height ],
				area : areaData,
				shade : 0.8,
				closeBtn : 1,
				shadeClose : false,
				move : false,
				resize : false,
				scrollbar : false,
				content : contentUrl
			});
			return _index;
		}
	};
});