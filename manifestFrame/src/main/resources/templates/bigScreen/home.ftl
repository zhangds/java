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
		body{background:#00123e;width: 4096px;height: 1024px;font-family:"黑体";color:#FFFFFF;}
		.topRow,.topRow div{height:165px;font-size:36px;margin:0;padding:0}
		.topRow .leftArea{text-indent:30px;height:165px;overflow: auto;background: url(./images/leftArea.png) no-repeat;background-size:66% 100%;}
		.topRow .leftArea .row .lineText{height:36px;}
		.topRow .centerArea{height:165px;overflow: auto;background: url(./images/centerArea.png) center no-repeat;background-size:80% 100%;}
		.topRow .rightArea{text-align:right;height:165px;overflow: auto;background: url(./images/rightArea.png) right no-repeat;background-size:66% 100%;}
		.topRow .rightArea .row .lineText{height:36px;padding-right: 30px;}
		.centerRow{height:859px;margin: 0px;}
		.centerRow div.one{padding:0px;height:859px;overflow: auto;background: url(./images/leftImage.png) center no-repeat;}
		.centerRow div.two{padding:0px;height:859px;overflow: auto;background: url(./images/centerImage.png) center no-repeat;left: -150px;}
		.centerRow div.thrid{padding:0px;margin-left: -80px;}
		.centerRow div.thrid div{height:104px;padding:0px;}
		.centerRow div.thrid .row .centerFolder{color:#B4F7FF;padding:0px;height:104px;overflow: auto;background: url(./images/rightFolder.png) center no-repeat;}
		.centerRow div.thrid .row .centerFolder span{font-size:36px;position:absolute;margin:32px 0px 32px 55px}
		.centerRow div.thrid .row .centerText{padding:0px;height:104px;color:#5587FB;}
		.centerRow div.thrid .row .centerText .row .bg{background:#0B2568;width: 50px;height: 74px;position:relative;margin:18px 5px 20px 10px;}
		.centerRow div.thrid .row .centerText .row span{font-size:42px;position:absolute;margin:12px 15px;}
		/*.topRow div.left{width:700px;text-align:right}
		.topRow div.right{width:700px} */
  </style>
</head>
<style type="text/css">
</style>
<title>${(appName)?default("大屏首页")}</title>
</head>
<body style="overflow: auto;">
	<div class="container-fluid" style="padding:0">
		<div class="row topRow">
			<div class="col-md-3">
				<div class="leftArea">
					<div class="row" >
						<div class="col-md-12 lineText one" style="margin-top: 30px;"></div>
						<div class="col-md-12 lineText two" style="margin-top: 20px;"></div>
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
						<div class="col-md-12 lineText one" style="margin-top: 30px;"></div>
						<div class="col-md-12 lineText two" style="margin-top: 20px;"></div>
					</div>
				</div>
			</div>
		</div>
		<div class="row centerRow">	
			<div class="col-md-5 one"></div>
			<div class="col-md-4 two"></div>
			<div class="col-md-3 thrid">
				<div class="col-md-12" style="margin-top: 50px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span>今日报关单数</span>
						</div>
						<div class="col-md-8 centerText" id="todayEntrys">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 50px;"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>1</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 20px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span>今日转关单数</span>
						</div>
						<div class="col-md-8 centerText" id="todayDecls">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 50px;"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>2</span></div>
								<div class="col-md-1 bg"><span>2</span></div>
								<div class="col-md-1 bg"><span>8</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 20px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span>报关单总数</span>
						</div>
						<div class="col-md-8 centerText" id="totalEntrys">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 50px;"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>1</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 20px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span>转关单总数</span>
						</div>
						<div class="col-md-8 centerText" id="totalDecls">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 50px;"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>2</span></div>
								<div class="col-md-1 bg"><span>4</span></div>
								<div class="col-md-1 bg"><span>6</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 20px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span>服务企业数</span>
						</div>
						<div class="col-md-8 centerText" id="totalComs">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 50px;"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>7</span></div>
								<div class="col-md-1 bg"><span>5</span></div>
								<div class="col-md-1 bg"><span>5</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-12" style="margin-top: 20px">
					<div class="row" style="margin:0px;">
						<div class="col-md-4 centerFolder">
							<span>在区船舶数</span>
						</div>
						<div class="col-md-8 centerText" id="totalShips">
							<div class="row">
								<div class="col-md-1 bg" style="margin-left: 50px;"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
								<div class="col-md-1 bg"><span>0</span></div>
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
<!-- <div class="container-fluid" style="margin-top: 5px;">
  	<div class="row noPaddinglr">
  		<div class="col-sm-2 col-xs-3 col-md-2 noPaddinglr" >
  			<div class="container-fluid noPaddinglr" >
  				<div class="row noPaddinglr mainTitle" style="overflow: auto; background: url(${mvcPath}/webjarslocator/base/images/ued-logo2.png) no-repeat;background-size:80% 100%;">
  					<div class="col-sm-3 col-xs-3 col-md-3 noPaddinglr mainIcon">
  					<i class="fa fa-cubes fa-3x fa-spin fa-fw" aria-hidden="true"></i>
  					</div>
  					<div class="col-sm-5 col-xs-5 col-md-5 text-nowrap noPaddinglr">数据资产</div>
  					<div class="col-sm-4 col-xs-4 col-md-4 text-nowrap noPaddinglr mainTitle_sub">试图</div>
  				</div>
  				<div class="row noPaddinglr">
  					<div class="col-sm-12 col-xs-12 col-md-12 noPaddinglr titleSplitBar" ></div>
  				</div>
  				<div class="row noPaddinglr">
  					
  					<div class="col-sm-12 col-xs-12 col-md-12 noPaddinglr subTitle">
  						总值
  						<div class="row noPaddinglr">
  							<div class="col-sm-10 col-xs-14 col-md-10 col-sm-offset-2 col-sm-offset-4 col-md-offset-2">
  							    <div class="row noPaddinglr">
 							    	<div class="col-sm-1 col-xs-2 col-md-1 noPaddinglr subTitle_split">&nbsp;&nbsp;</div>
  							    	<div class="col-sm-4 col-xs-6 col-md-4 noPaddinglr subTitle_title">222</div>
  							    </div>
  							    <div class="row noPaddinglr">
  							    	<div class="col-sm-8 col-xs-10 col-md-8 noPaddinglr subTitle_title_sub">&nbsp;&nbsp;数据资源&nbsp;&nbsp;／类</div>
  							    </div>
  							</div>
  						</div>
  						<div class="row subTitle_placeholder"></div>
  						<div class="row noPaddinglr">
  							<div class="col-sm-10 col-xs-14 col-md-10 col-sm-offset-2 col-sm-offset-4 col-md-offset-2">
  							    <div class="row noPaddinglr">
 							    	<div class="col-sm-1 col-xs-2 col-md-1 noPaddinglr subTitle_split">&nbsp;&nbsp;</div>
  							    	<div class="col-sm-4 col-xs-6 col-md-4 noPaddinglr subTitle_title">0</div>
  							    </div>
  							    <div class="row noPaddinglr">
  							    	<div class="col-sm-8 col-xs-10 col-md-8 noPaddinglr subTitle_title_sub">&nbsp;&nbsp;字段数&nbsp;&nbsp;／个</div>
  							    </div>
  							</div>
  						</div>
  						<div class="row subTitle_placeholder"></div>
  						<div class="row noPaddinglr">
  							<div class="col-sm-10 col-xs-14 col-md-10 col-sm-offset-2 col-sm-offset-4 col-md-offset-2">
  							    <div class="row noPaddinglr">
 							    	<div class="col-sm-1 col-xs-2 col-md-1 noPaddinglr subTitle_split">&nbsp;&nbsp;</div>
  							    	<div class="col-sm-4 col-xs-6 col-md-4 noPaddinglr subTitle_title">0</div>
  							    </div>
  							    <div class="row noPaddinglr">
  							    	<div class="col-sm-8 col-xs-10 col-md-8 noPaddinglr subTitle_title_sub">&nbsp;&nbsp;记录数&nbsp;&nbsp;／条</div>
  							    </div>
  							</div>
  						</div>
  						<div class="row subTitle_placeholder"></div>
  					</div>
  					
  					<div class="row noPaddinglr">
  						<div class="col-sm-12 col-xs-12 col-md-12 noPaddinglr titleSplitBar" ></div>
  					</div>
  					<div class="col-sm-12 col-xs-12 col-md-12 noPaddinglr subTitle">
  						当月新增
  						<div class="row noPaddinglr">
  							<div class="col-sm-10 col-xs-14 col-md-10 col-sm-offset-2 col-sm-offset-4 col-md-offset-2">
  							    <div class="row noPaddinglr">
 							    	<div class="col-sm-1 col-xs-2 col-md-1 noPaddinglr subTitle_split">&nbsp;&nbsp;</div>
  							    	<div class="col-sm-4 col-xs-6 col-md-4 noPaddinglr subTitle_title">222</div>
  							    </div>
  							    <div class="row noPaddinglr">
  							    	<div class="col-sm-8 col-xs-10 col-md-8 noPaddinglr subTitle_title_sub">&nbsp;&nbsp;数据资源&nbsp;&nbsp;／类</div>
  							    </div>
  							</div>
  						</div>
  						<div class="row subTitle_placeholder"></div>
  						<div class="row noPaddinglr">
  							<div class="col-sm-10 col-xs-14 col-md-10 col-sm-offset-2 col-sm-offset-4 col-md-offset-2">
  							    <div class="row noPaddinglr">
 							    	<div class="col-sm-1 col-xs-2 col-md-1 noPaddinglr subTitle_split">&nbsp;&nbsp;</div>
  							    	<div class="col-sm-4 col-xs-6 col-md-4 noPaddinglr subTitle_title">0</div>
  							    </div>
  							    <div class="row noPaddinglr">
  							    	<div class="col-sm-8 col-xs-10 col-md-8 noPaddinglr subTitle_title_sub">&nbsp;&nbsp;字段数&nbsp;&nbsp;／个</div>
  							    </div>
  							</div>
  						</div>
  						<div class="row subTitle_placeholder"></div>
  						<div class="row noPaddinglr">
  							<div class="col-sm-10 col-xs-14 col-md-10 col-sm-offset-2 col-sm-offset-4 col-md-offset-2">
  							    <div class="row noPaddinglr">
 							    	<div class="col-sm-1 col-xs-2 col-md-1 noPaddinglr subTitle_split">&nbsp;&nbsp;</div>
  							    	<div class="col-sm-4 col-xs-6 col-md-4 noPaddinglr subTitle_title">0</div>
  							    </div>
  							    <div class="row noPaddinglr">
  							    	<div class="col-sm-8 col-xs-10 col-md-8 noPaddinglr subTitle_title_sub">&nbsp;&nbsp;记录数&nbsp;&nbsp;／条</div>
  							    </div>
  							</div>
  						</div>
  						<div class="row subTitle_placeholder"></div>
  					</div>
  				</div>
  			</div>
  		</div>
	  	<div class="col-sm-7 col-xs-6 col-md-7 noPaddinglr">轮播</div>
	  	<div class="col-sm-3 col-xs-3 col-md-3 noPaddinglr">
	  		<div class="row noPaddinglr rightFirstRow">
	  			<div class="col-sm-12 col-xs-12 col-md-12 noPaddinglr" style="margin-top: 22px;">&nbsp;&nbsp;&nbsp;&nbsp;当前任务完成度</div>
	  		</div>
	  		<div class="row noPaddinglr">
  				<div class="col-sm-12 col-xs-12 col-md-12 noPaddinglr rightSplitBar"></div>
  			</div>
  			<div class="row noPaddinglr">
  				<div class="col-sm-2 col-xs-3 col-md-2 col-sm-offset-2 col-xs-offset-3 col-md-offset-2 rightSecTitle">应用层</div>
  				<div class="col-sm-6 col-xs-6 col-md-6 rightgraph"><span class="rightblue" style="width:76.1%;">76.1%</span></div>
  				<div class="clearfix"></div>
  				<div class="col-sm-6 col-xs-7 col-md-6 col-sm-offset-4 col-xs-offset-3 col-md-offset-4 rightThirdTitle" style="padding-left: 0px;padding-right: 0px;">
  					当前完成任务数:<span class="numberColor">153</span>&nbsp;&nbsp;&nbsp;&nbsp;总任务数：<span class="numberColor">201</span>
  				</div>
  			</div>
  			=============
  			<div class="row noPaddinglr">
  				<div class="col-sm-2 col-xs-3 col-md-2 col-sm-offset-2 col-xs-offset-3 col-md-offset-2 rightSecTitle">标准库</div>
  				<div class="col-sm-6 col-xs-6 col-md-6 rightgraph"><span class="rightblue" style="width:84.9%;">84.9%</span></div>
  				<div class="clearfix"></div>
  				<div class="col-sm-6 col-xs-7 col-md-6 col-sm-offset-4 col-xs-offset-3 col-md-offset-4 rightThirdTitle" style="padding-left: 0px;padding-right: 0px;">
  					当前完成任务数:<span class="numberColor">124</span>&nbsp;&nbsp;&nbsp;&nbsp;总任务数：<span class="numberColor">146</span>
  				</div>
  			</div>
  			=============
  			<div class="row noPaddinglr">
  				<div class="col-sm-2 col-xs-3 col-md-2 col-sm-offset-2 col-xs-offset-3 col-md-offset-2 rightSecTitle">汇集库</div>
  				<div class="col-sm-6 col-xs-6 col-md-6 rightgraph"><span class="rightblue" style="width:92.0%;">92.0%</span></div>
  				<div class="clearfix"></div>
  				<div class="col-sm-6 col-xs-7 col-md-6 col-sm-offset-4 col-xs-offset-3 col-md-offset-4 rightThirdTitle" style="padding-left: 0px;padding-right: 0px;">
  					当前完成任务数:<span class="numberColor">748</span>&nbsp;&nbsp;&nbsp;&nbsp;总任务数：<span class="numberColor">813</span>
  				</div>
  			</div>
  			<div class="row noPaddinglr">
  				<div class="col-sm-12 col-xs-12 col-md-12 rightRowCenterTitle">模型热度排名表</div>
  				<div class="clearfix"></div>
  				<div class="col-sm-8 col-xs-9 col-md-8 col-sm-offset-4 col-xs-offset-3 col-md-offset-4 rightSplitBar"></div>
  				<div class="clearfix"></div>
  				<div class="col-sm-8 col-xs-9 col-md-8 col-sm-offset-4 col-xs-offset-6 col-md-offset-4 rightRow_placeholder"></div>
  			</div>
  			<div class="row noPaddinglr">
  				<div class="col-sm-8 col-xs-10 col-md-8 col-sm-offset-2 col-xs-offset-2 col-md-offset-2 rightTableTitle">
  					<table style="width:100%">
  					<tbody style="border-bottom-style: solid;border-bottom-width: 1px;border-color:#0D3C50;height:28px;">
	  					<tr>
	  						<th style="width:20%;text-align:center;">排名</th><th style="width:60%;text-align:center;">模型名称</th>
	  						<th style="width:20%;text-align:center;">热度值</th>
	  					</tr>
  					</tbody>
  					</table>
  				</div>
  			</div>
  			
	  	</div>
	</div>
</div> -->
<script type="text/javascript"
	src="./webjarslocator/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="./webjarslocator/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="./js/date.js"></script>
<script type="text/javascript">
	(function (global) {  
		$(".col-md-*").css({"padding-left": "0px","padding-right": "0px"});
		$(".leftArea .row .lineText.one").html(today.getFullYear()+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日 "+"&nbsp;&nbsp;农历"+getNLyyyyMMdd(yy,mm,dd));
		$(".rightArea .row .lineText.one").html(today.getFullYear()+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日 "+"&nbsp;&nbsp;农历"+getNLyyyyMMdd(yy,mm,dd));
		time();
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
            		var _totalComs = getVal(totalComs);
        			var todayEntrysObj = $("#totalComs").find(".bg");
        			todayEntrysObj.each(function(index,element){
        				$(this).find("span").text(_totalComs.charAt(index));
        			});
            		var _todayEntrys = getVal(todayEntrys);
        			var todayEntrysObj = $("#todayEntrys").find(".bg");
        			todayEntrysObj.each(function(index,element){
        				$(this).find("span").text(_todayEntrys.charAt(index));
        			});
        			var _totalEntrys = getVal(totalEntrys);
        			var totalEntrysObj = $("#totalEntrys").find(".bg");
        			totalEntrysObj.each(function(index,element){
        				$(this).find("span").text(_totalEntrys.charAt(index));
        			});
        			var todayDeclsObj = $("#todayDecls").find(".bg");
        			var _todayDecls = getVal(todayDecls);
        			todayDeclsObj.each(function(index,element){
        				$(this).find("span").text(_todayDecls.charAt(index));
        			});
        			var totalDeclsObj = $("#totalDecls").find(".bg");
        			var _totalDecls = getVal(totalDecls);
        			totalDeclsObj.each(function(index,element){
        				$(this).find("span").text(_totalDecls.charAt(index));
        			});
        			var totalShipsObj = $("#totalShips").find(".bg");
        			var _totalShips = getVal(totalShips);
        			totalShipsObj.each(function(index,element){
        				$(this).find("span").text(_totalShips.charAt(index));
        			});
        			setTimeout(timeData,1000*60*10);
                }
			});
		};
		timeData();
	}(this)); 
</script>
</body>
</html>