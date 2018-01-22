<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="keywords" content="">
<meta name="description" content="">
<link rel="Shortcut Icon" type="images/x-icon"
	href="${mvcPath}/images/favicon.ico" />
<link rel="Bookmark" type="images/x-icon" 
	href="${mvcPath}/images/favicon.ico">
<!--[if lt IE 9]>
<script type="text/javascript" src="./webjarslocator/html5shiv/html5shiv.min.js"></script>
<script type="text/javascript" src="./webjarslocator/respond/respond.min.js"></script>
<script type="text/javascript" src="./webjarslocator/pie/PIE.js"></script>
<![endif]-->
<link href="./webjarslocator/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<!-- <link href="./webjarslocator/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" /> -->
<!--[if IE 6]>
<script type="text/javascript" src="${mvcPath}/webjarslocator/base/js/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
  <style type="text/css">
		body{background: url(./images/backgroudImg.png) center repeat;width: 1920px; height: 859px;font-family:"黑体";color:#FFFFFF;}/* ;#00133e;background:#0d2251; */
		/* .topRow,.topRow div{height:110px;font-size:18px;margin:0;padding:0}
		.topRow .leftArea{text-indent:30px;height:110px;overflow: auto;background: url(./images/leftAreaNew.png) no-repeat;background-size:66% 100%;}
		.topRow .leftArea .row .lineText{height:36px;}
		.topRow .centerArea{height:110px;overflow: auto;background: url(./images/centerAreaNew.png) center no-repeat;background-size:80% 100%;}
		.topRow .rightArea{text-align:right;height:110px;overflow: auto;background: url(./images/rightAreaNew.png) right no-repeat;background-size:66% 100%;}
		.topRow .rightArea .row .lineText{height:36px;padding-right: 30px;} */
		.centerRow{height:859px;margin: 0px;}
		.centerRow div.one{padding:0px;height:859px;overflow: auto;background: url(./images/left_image.png) center no-repeat;background-size: 90% 80%;left: 0px;right: 0px;background-position: 50px 40px;margin-left: -20px;}
		.centerRow div.two{padding:0px;height:859px;overflow: auto;background: url(./images/centerimageNew.png) center no-repeat;background-size: 100% 80%;left: 0px;right: 0px;background-position: 0px 40px;}
		.centerRow div.thrid{padding:0px;left: 20px;}
		.centerRow div.thrid div{height:104px;padding:0px;}
		.centerRow div.thrid .row .centerFolder{color:#B4F7FF;padding:0px;height:104px;overflow: auto;background: url(./images/rightFolderNew.png) center no-repeat;background-size: 90% 50%;}
		.centerRow div.thrid .row .centerFolder span{font-size:18px;position:absolute;margin:44px 10px 12px 30px;}
		.centerRow div.thrid .row .centerText{padding:0px;height:104px;color:#bdf5fd;}
		.centerRow div.thrid .row .centerText .row .bg{background:#5568b7; width: 30px; height: 40px;position:relative;margin:32px 2px 20px 10px;}/*  #42509b */
		.centerRow div.thrid .row .centerText .row span{font-size:42px;position:absolute;margin:-6px 10px 12px 4px;letter-spacing:8px;}
		.centerRow div.thrid .row .centerText .row{height:104px;overflow: auto;background: url(./images/numberFolderNew.png) center no-repeat;background-size: 90% 60%;}
		/*.topRow div.left{width:700px;text-align:right}
		.topRow div.right{width:700px} */
  </style>
</head>
<style type="text/css">
</style>
<title>${(appName)?default("大屏第一页")}</title>
</head>
<body style="overflow: auto;">
	<div class="container-fluid" style="padding:0">
		<!-- <div class="row topRow">
			<div class="col-md-3">
				<div class="leftArea">
					<div class="row" >
						<div class="col-md-12 lineText one" style="margin-top: 20px;"></div>
						<div class="col-md-12 lineText two" style="margin-top: 10px;"></div>
					</div>
				</div>
			</div>
			<div class="col-md-6" >
				<div class="centerArea">
				</div>
			</div>
			<div class="col-md-3">
				<div class="rightArea">
					<div class="row" >
						<div class="col-md-12 lineText one" style="margin-top: 20px;"></div>
						<div class="col-md-12 lineText two" style="margin-top: 10px;"></div>
					</div>
				</div>
			</div>
		</div> -->
		<div class="row centerRow">	
			<div class="col-md-5 one"></div>
			<div class="col-md-4 two"></div>
			<div class="col-md-3 thrid">
				<div class="col-md-12" style="margin-top: 70px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span>今日报关量</span>
						</div>
						<div class="col-md-8 centerText" id="todayEntrys">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 55px;"><span>1</span></div>
								<!-- <div class="col-md-1 bg"><span>0</span></div> -->
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>1</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 0px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span>今日转关量</span>
						</div>
						<div class="col-md-8 centerText" id="todayDecls">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 55px;"><span>1</span></div>
								<!-- <div class="col-md-1 bg"><span>0</span></div> -->
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>2</span></div>
								<div class="col-md-1 bg"><span>2</span></div>
								<div class="col-md-1 bg"><span>8</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 0px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span id="totalEntrysTitle">年报关量</span>
						</div>
						<div class="col-md-8 centerText" id="totalEntrys">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 55px;"><span>1</span></div>
								<!-- <div class="col-md-1 bg"><span>0</span></div> -->
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>1</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 0px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span id="totalDeclsTitle">年转关量</span>
						</div>
						<div class="col-md-8 centerText" id="totalDecls">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 55px;"><span>0</span></div>
								<!-- <div class="col-md-1 bg"><span>0</span></div> -->
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>2</span></div>
								<div class="col-md-1 bg"><span>4</span></div>
								<div class="col-md-1 bg"><span>6</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 0px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span>服务企业数</span>
						</div>
						<div class="col-md-8 centerText" id="totalComs">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 55px;"><span>0</span></div>
								<!-- <div class="col-md-1 bg"><span>0</span></div> -->
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>7</span></div>
								<div class="col-md-1 bg"><span>5</span></div>
								<div class="col-md-1 bg"><span>5</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 0px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span>在区船舶数</span>
						</div>
						<div class="col-md-8 centerText" id="totalShips">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 55px;"><span>0</span></div>
								<!-- <div class="col-md-1 bg"><span>0</span></div> -->
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>4</span></div>
								<div class="col-md-1 bg"><span>6</span></div>
								<div class="col-md-1 bg"><span>5</span></div>
								<div class="col-md-1 bg"><span>3</span></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript"
	src="./webjarslocator/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="./webjarslocator/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="./js/date.js"></script>
<script type="text/javascript">
	(function (global) {  
		/* $(".col-md-*").css({"padding-left": "0px","padding-right": "0px"});
		$(".leftArea .row .lineText.one").html(today.getFullYear()+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日 "+"&nbsp;&nbsp;农历"+getNLyyyyMMdd(yy,mm,dd));
		$(".rightArea .row .lineText.one").html(today.getFullYear()+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日 "+"&nbsp;&nbsp;农历"+getNLyyyyMMdd(yy,mm,dd));
		time(); */
		function timeData(){
			$.ajax({
                type:"post",
                url:"./screenGetData",
                //data:{encryptString:value},
                success: function (result) {
                	var todayEntrys = result.dr_entry;
                	var todayDecls = result.dr_decl;
            		var totalEntrys = result.qn_entry;
            		var totalDecls = result.qn_decl ;
            		var totalComs = result.dr_trade_num ;
            		var totalShips = result.qs_ship ;
            		var _length = todayEntrys.length ;
            		_length = (todayDecls.length > _length ? todayDecls.length :_length) ;
            		_length = (totalEntrys.length > _length ? totalEntrys.length :_length) ;
            		_length = (totalDecls.length > _length ? totalDecls.length :_length) ;
            		_length = (totalComs.length > _length ? totalComs.length :_length) ;
            		_length = (totalShips.length > _length ? totalShips.length :_length) ;
            		
            		/* $("#totalComs").find(".bg").find("span").text(totalComs);
            		$("#todayEntrys").find(".bg").find("span").text(todayEntrys);
            		$("#todayDecls").find(".bg").find("span").text(todayDecls);
            		$("#totalDecls").find(".bg").find("span").text(totalDecls);
            		$("#totalShips").find(".bg").find("span").text(totalShips);
            		$("#totalEntrys").find(".bg").find("span").text(totalEntrys); */
            		
            		$("#totalEntrysTitle").text(yy+"年报关量");
            		$("#totalDeclsTitle").text(yy+"年转关量");
            		var countNum = 6;
            		var _totalComs = getVal(totalComs,_length);
        			var todayEntrysObj = $("#totalComs").find(".bg");
        			todayEntrysObj.each(function(index,element){
        				if (index>=(countNum-_length)){
        					$(this).find("span").text(_totalComs.charAt(index-countNum+_length));
        				}else{
        					$(this).find("span").text("");
        				}
        			});
            		var _todayEntrys = getVal(todayEntrys,_length);
        			var todayEntrysObj = $("#todayEntrys").find(".bg");
        			todayEntrysObj.each(function(index,element){
        				if (index>=(countNum-_length)){
        					$(this).find("span").text(_todayEntrys.charAt(index-countNum+_length));
        				}else{
        					$(this).find("span").text("");
        				}
        			});
        			
        			var _totalEntrys = getVal(totalEntrys,_length);
        			var totalEntrysObj = $("#totalEntrys").find(".bg");
        			totalEntrysObj.each(function(index,element){
        				if (index>=(countNum-_length)){
        					$(this).find("span").text(_totalEntrys.charAt(index-countNum+_length));
        				}else{
        					$(this).find("span").text("");
        				}
        			});
        			
        			var todayDeclsObj = $("#todayDecls").find(".bg");
        			var _todayDecls = getVal(todayDecls,_length);
        			todayDeclsObj.each(function(index,element){
        				if (index>=(countNum-_length)){
        					$(this).find("span").text(_todayDecls.charAt(index-countNum+_length));
        				}else{
        					$(this).find("span").text("");
        				}
        			});
        			
        			var totalDeclsObj = $("#totalDecls").find(".bg");
        			var _totalDecls = getVal(totalDecls,_length);
        			totalDeclsObj.each(function(index,element){
        				if (index>=(countNum-_length)){
        					$(this).find("span").text(_totalDecls.charAt(index-countNum+_length));
        				}else{
        					$(this).find("span").text("");
        				}
        			});
        			var totalShipsObj = $("#totalShips").find(".bg");
        			var _totalShips = getVal(totalShips,_length);
        			totalShipsObj.each(function(index,element){
        				if (index>=(countNum-_length)){
        					$(this).find("span").text(_totalShips.charAt(index-countNum+_length));
        				}else{
        					$(this).find("span").text("");
        				}
        			});
        			setTimeout(timeData,1000*60*5);
                }
			});
		};
		timeData();
	}(this)); 
</script>
</body>
</html>