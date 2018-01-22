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
		body{background: url(./images/backgroudImg.png) center repeat;width: 1920px; height: 1024px;font-family:"黑体";color:#FFFFFF;}/* ;#00133e;background:#0d2251; */
		.topRow,.topRow div{height:110px;font-size:18px;margin:0;padding:0}
		.topRow .leftArea{text-indent:30px;height:110px;overflow: auto;background: url(./images/leftAreaNew.png) no-repeat;background-size:66% 100%;}
		.topRow .leftArea .row .lineText{height:36px;}
		.topRow .centerArea{height:110px;overflow: auto;background: url(./images/centerAreaNew.png) center no-repeat;background-size:80% 100%;}
		.topRow .rightArea{text-align:right;height:110px;overflow: auto;background: url(./images/rightAreaNew.png) right no-repeat;background-size:66% 100%;}
		.topRow .rightArea .row .lineText{height:36px;padding-right: 30px;}
		.mainRow{height:auto;margin: 0px;}
		
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
		</div>
		<div class="row mainRow">
				
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
					  <!-- Indicators -->
					  <ol class="carousel-indicators">
					    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
					    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
					    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
					  </ol>

					  <!-- Wrapper for slides -->
					  <div class="carousel-inner" role="listbox">
					    <div class="item active">
					      <iframe src="./one" width="100%" scrolling="no" height="859px" style="border-top-width: 0px;border-top-style: solid;border-bottom-width: 0px;border-bottom-style: solid;border-left-width: 0px;border-left-style: solid;border-right-width: 0px;border-right-style: solid;"></iframe>
					    </div>
					    <div class="item">
					      <iframe src="./two" width="100%" scrolling="no" height="859px" style="border-top-width: 0px;border-top-style: solid;border-bottom-width: 0px;border-bottom-style: solid;border-left-width: 0px;border-left-style: solid;border-right-width: 0px;border-right-style: solid;"></iframe>
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
		$(".col-md-*").css({"padding-left": "0px","padding-right": "0px"});
		$(".leftArea .row .lineText.one").html(today.getFullYear()+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日 "+"&nbsp;&nbsp;农历"+getNLyyyyMMdd(yy,mm,dd));
		$(".rightArea .row .lineText.one").html(today.getFullYear()+"年"+(today.getMonth()+1)+"月"+today.getDate()+"日 "+"&nbsp;&nbsp;农历"+getNLyyyyMMdd(yy,mm,dd));
		time();
		$('.carousel').carousel({interval:1000*60});
	}(this)); 
</script>
</body>
</html>