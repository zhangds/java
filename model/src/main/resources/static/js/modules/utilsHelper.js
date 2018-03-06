/**
 * 
 */
layui.define(['jquery','layer'], function (exports) {
	"use strict";
    var _MOD = 'utilsHelper',layer = layui.layer,$ = layui.jquery;
    
    var _obj = {
    		/*获取客户端时间函数*/
    		getJsCurrentDateTime : function (){
    		    var now = new Date();
    		    var year = now.getFullYear();
    		    var month = now.getMonth() + 1;
    		    var day = now.getDate();
    		    var hh = now.getHours();
    		    var mm = now.getMinutes();
    		    var ss = now.getSeconds();

    		    var clock = year + "-";

    		    if(month < 10) clock += "0";
    		    clock += month + "-";  
    		        
    		    if(day < 10) clock += "0";
    		    clock += day + " ";
    		        
    		    if(hh < 10) clock += "0";
    		    clock += hh + ":";  
    		    if (mm < 10) clock += '0';
    		    clock += mm + ":";   
    		        
    		    if (ss < 10) clock += '0';
    		    clock += ss;   
    		    return(clock);
    		},
    		/*获取服务器时间*/
    		getServerDateTime : function (fromatString){
    			
    		},
    		/*补位*/
    		coverValue : function (stringValue,intLength,coverString){
    			if ((stringValue + "").length >= intLength) return stringValue;
    			return this.coverValue(coverString + stringValue, intLength ,coverString);
    		}
    };
    
    //输出接口
    exports('utilsHelper', _obj);
});