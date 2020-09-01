<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>流程图设计器:${flowId!""}</title>
<!--[if lt IE 9]>
<?import namespace="v" implementation="#default#VML" ?>
<![endif]-->
<link rel="Bookmark" href="${mvcPath}/resources/images/favicon.ico" />
<link rel="Shortcut Icon" href="${mvcPath}/resources/images/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${mvcPath}/resources/kfFlowDesign/css/GooFlow2.css"/>
<link rel="stylesheet" type="text/css" href="${mvcPath}/resources/kfFlowDesign/css/designerPage.css"/>


<script type="text/javascript">
var jsondata=${flowJson!""};
var flowId = "${flowId!''}";
var staffno = "${staffno!''}";
var nodeSetUrl = "${nodeSetUrl!''}";
</script>
</head>
<body style="background:#EEEEEE">
	<div style="width: 100%; position: absolute; height: 99%;">
		<div class="rightArea" style="display: inline; float: left; position: absolute; height: 100%; right: 324px; left: 0; overflow: hidden; z-index: 1; border-top: 4px solid #e5e5e5; border-left: 4px solid #e5e5e5; border-bottom: 4px solid #e5e5e5;">
			<div id="demo"></div>
		</div>
		<div style="position: absolute; width: 320px; top: 0px; right: 0; height: 100%; bottom: 0; overflow: hidden; z-index: 1; border: 4px solid #e5e5e5;">
			<div class="myForm" id="propertyForm">
				<div class="form_title">属性设置</div>
				<div class="form_content" style="height: 100%;position: absolute;width: 100%;">
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${mvcPath}/webjarslocator/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/template.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/work.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowDesign/js/GooFunc.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowDesign/js/json2.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowDesign/js/GooFlow.min.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowDesign/js/designer.js"></script>
</body>
</html>