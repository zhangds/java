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
		.row .row .twoLeftTop{margin-left: 20px;border-width: 1px;border-style: solid;border-color:#4592E0;height:134px;background:#273F92;}
		.row .row .twoLeftTop .row{margin-left: 20px;margin-top: 24px;font-size:24px;color:#BEF5FF;}
		.row .row .twoLeftTop .row .bg{background:#3a4f9d; width: 32px; height: 47px;position:relative;margin:0px 9px 20px 4px}/*  #42509b */
		.row .row .twoLeftTop .row .bg span{font-size:38px;position:absolute;margin-left: 6px;letter-spacing:8px;}
		.row .row .twoLeftCenter{height: 272px;padding: 0px;}
		.row .row .twoLeftCenter .twoLeftCenterArea{background:#3a4f9d;height: 272px; border-width: 1px;border-style: solid;border-color:#4592E0; margin-left: 20px;background:#273F92;}
		.row .row .twoLeftCenterArea.entryIm{background: url(./images/enteryImage.png) center no-repeat;}
		.row .row .twoLeftCenterArea.declsIm{background: url(./images/declsImage.png) center no-repeat;}
		.row .row.twoCenterTitle{background: url(./images/twoCenterTitleBack.png) center no-repeat;}
		.row .row.twoCenterIm{background: url(./images/twoCenterImage.png) center no-repeat;height: 730px;margin: 10px 0px 0px 20px;margin-left: -10px;/*border-width: 1px;border-style: solid;border-color:#4592E0; background:#273F92; */}
		.row .row.twoCenterTitle .twoCenterTitleText{padding:0px 20px 0px 20px;height: 28px;line-height: 33px;text-align :center;font-size:28px;color:#FB656B;}
  		.row .row.twoCenterTitleSub .twoCenterTitleNumber{margin:5px 0px;padding: 0px;font-size:18px;height: 70px;background: url(./images/twoCenterTitleNumber.png) left no-repeat;}
  		.row .row.twoCenterTitleSub .twoCenterTitleNumber .twoCenterTitleNumberText{font-size:22px;position:absolute;margin: 25px 20px;}
  		.row .waterPat{padding: 0px;margin: 32px 20px 8px -10px;background: url(./images/waterPatImage.png) center no-repeat;height: 904px;}
  </style>
</head>
<style type="text/css">
</style>
<title>${(appName)?default("大屏第二页")}</title>
</head>
<body style="overflow: auto;">
	<div class="container-fluid" style="padding:0">
		<div class="row">
			<div class="col-md-4" style="padding-left: -15px;padding-right: 0px;">
				<div class="row" style="margin-bottom: 0px;margin-top: 32px;margin-left: 0px;margin-right: 0px;">
					<div class="col-md-6" style="padding: 0px;">
						<div class="twoLeftTop" id="todayEntryNumbers">
							<div class="row">
							今日报关量
							</div>
							<div class="row" style="margin-top: 8px;margin-right: 0px;">
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
							</div>
						</div>
					</div>
					<div class="col-md-6" style="padding: 0px;">
						<div class="twoLeftTop" id="todayDeclsNumbers">
							<div class="row">
							今日转关量
							</div>
							<div class="row" style="margin-top: 8px;margin-right: 0px;">
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="row" style="margin-bottom: 0px;margin-top: 20px;margin-left: 0px;margin-right: 0px;">
					<div class="col-md-6" style="padding: 0px;">
					<div class="twoLeftTop" id="userNumbers">
							<div class="row">
							注册用户数
							</div>
							<div class="row" style="margin-top: 8px;margin-right: 0px;">
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
							</div>
						</div>
					</div>
					<div class="col-md-6" style="padding: 0px;">
						<div class="twoLeftTop" id="tradeNums">
							<div class="row">
							服务企业数
							</div>
							<div class="row" style="margin-top: 8px;margin-right: 0px;">
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
								<div class="col-md-2 bg" style="padding-left: 0px;padding-right: 0px;"><span>0</span></div>
							</div>
						</div>
					</div>
				</div>
				<div class="row" style="margin-bottom: 0px;margin-top: 20px;margin-left: 0px;margin-right: 0px;">
					<div class="col-md-12 twoLeftCenter">
					<div class="twoLeftCenterArea entryIm"></div>
					</div>
				</div>
				<div class="row" style="margin-bottom: 0px;margin-top: 20px;margin-left: 0px;margin-right: 0px;">
					<div class="col-md-12 twoLeftCenter">
					<div class="twoLeftCenterArea declsIm"></div>
					</div>
				</div>
			</div>
			<div class="col-md-4" style="padding-left: 0px;padding-right: 0px;">
				<div class="row twoCenterTitle" style="height: 28px;margin: 35px 0px 0px 20px;">
					<div class="col-md-2">
					</div>
					<div class="col-md-8 twoCenterTitleText">武汉关区2018年&nbsp;&nbsp;1月--12月
					</div>
					<div class="col-md-2" >
					</div>
				</div>
				<div class="row twoCenterTitleSub" style="margin: 20px 0px 0px 20px;padding: 0px;">
					<div class="col-md-4" style="padding: 0px;">
						<div class="row" style="margin:0px;padding: 0px;">
							<div class="col-md-12" style="margin:0px;padding: 0px;font-size:18px;">
								累计进出口总值
							</div>
							<div class="col-md-12 twoCenterTitleNumber">
								<div class="twoCenterTitleNumberText totalPrice">248.78亿美元</div>
							</div>
						</div>
					</div>
					<div class="col-md-4" style="padding: 0px;">
						<div class="row" style="margin:0px;padding: 0px;">
							<div class="col-md-12" style="margin:0px;padding: 0px;font-size:18px;">
								累计进出口报关总量
							</div>
							<div class="col-md-12 twoCenterTitleNumber">
								<div class="twoCenterTitleNumberText totalNums">40.69万票</div>
							</div>
						</div>
					</div>
					<div class="col-md-4" style="padding: 0px;">
						<div class="row" style="margin:0px;padding: 0px;">
							<div class="col-md-12" style="margin:0px;padding: 0px;font-size:18px;">
								累计进出口货运总量
							</div>
							<div class="col-md-12 twoCenterTitleNumber">
								<div class="twoCenterTitleNumberText totalGoods">19.47百万吨</div>
							</div>
						</div>
					</div>
				</div>
				<div class="row twoCenterIm" style="">
				</div>
			</div>
			<div class="col-md-4" style="padding-left: 0px;padding-right: 0px;">
				<div class="waterPat"></div>
				<!-- <div class="row" style="padding: 0px;margin: 32px 20px 8px 20px;height:277px;
					margin-left: 20px;border-width: 1px;border-style: solid;border-color:#4592E0;background:#273F92;">11
				
				</div>
				<div class="row" style="padding: 0px;margin: 20px 20px 8px 20px;height:277px;
					margin-left: 20px;border-width: 1px;border-style: solid;border-color:#4592E0;background:#273F92;">11
				
				</div> -->
			</div>
		</div>
	</div>
<script type="text/javascript"
	src="./webjarslocator/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="./webjarslocator/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="./js/two.js"></script>
<script type="text/javascript"
	src="./js/date.js"></script>
<script type="text/javascript">
	
</script>
</body>
</html>