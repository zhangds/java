/**
 * 
 */
(function (global) {
	
	function twoTimeData(){
		$.ajax({
            type:"post",
            url:"./screenGetData",
            //data:{encryptString:value},
            success: function (result) {
            	console.log(result);
            	var todayEntrys = result.dr_entry;
            	var todayDecls = result.dr_decl;
            	var userNumbers = result.user_numbers;
            	var tradeNum = result.dr_trade_num;
        		
            	var _length = todayEntrys.length ;
            	_length = (todayDecls.length > _length ? todayDecls.length :_length) ;
            	_length = (userNumbers.length > _length ? userNumbers.length :_length) ;
            	_length = (tradeNum.length > _length ? tradeNum.length :_length) ;
            	
            	var countNum = 6;
            	var _value = getVal(todayEntrys,_length);
            	var _objEle = $("#todayEntryNumbers.twoLeftTop .row").find(".bg");
            	_objEle.each(function(index,element){
            		if (index>=(countNum-_length)){
    					$(this).find("span").text(_value.charAt(index-countNum+_length));
    				}else{
    					$(this).find("span").text("");
    				}
            	});
            	_value = getVal(todayDecls,_length);
            	_objEle = $("#todayDeclsNumbers.twoLeftTop .row").find(".bg");
            	_objEle.each(function(index,element){
            		if (index>=(countNum-_length)){
    					$(this).find("span").text(_value.charAt(index-countNum+_length));
    				}else{
    					$(this).find("span").text("");
    				}
            	});
            	_value = getVal(userNumbers,_length);
            	_objEle = $("#userNumbers.twoLeftTop .row").find(".bg");
            	_objEle.each(function(index,element){
            		if (index>=(countNum-_length)){
    					$(this).find("span").text(_value.charAt(index-countNum+_length));
    				}else{
    					$(this).find("span").text("");
    				}
            	});
            	_value = getVal(tradeNum,_length);
            	_objEle = $("#tradeNums.twoLeftTop .row").find(".bg");
            	_objEle.each(function(index,element){
            		if (index>=(countNum-_length)){
    					$(this).find("span").text(_value.charAt(index-countNum+_length));
    				}else{
    					$(this).find("span").text("");
    				}
            	});

            	var totalGoods = result.total_goods;
            	var totalNums = result.total_nums;
            	var totalPrice = result.total_price;
            	$(".twoCenterTitleSub .twoCenterTitleNumberText.totalGoods").text(totalGoods);
            	$(".twoCenterTitleSub .twoCenterTitleNumberText.totalNums").text(totalNums);
            	$(".twoCenterTitleSub .twoCenterTitleNumberText.totalPrice").text(totalPrice);
            	
    			setTimeout(twoTimeData,1000*60*5);
            }
		});
	};
	twoTimeData();
	
})(this); 