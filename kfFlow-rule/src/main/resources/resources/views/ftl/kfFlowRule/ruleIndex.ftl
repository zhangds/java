<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<link rel="Bookmark" href="${mvcPath}/resources/images/favicon.ico" />
	<link rel="Shortcut Icon" href="${mvcPath}/resources/images/favicon.ico" />
	<link rel="stylesheet" type="text/css" href="${mvcPath}/resources/kfFlowResources/zTree_v3/css/metroStyle/metroStyle.css" /><!--zTreeStyle/zTreeStyle.css-->
	<link rel="stylesheet" type="text/css" href="${mvcPath}/resources/kfFlowResources/css/flowPublic.css"/>
    <link rel="stylesheet" type="text/css" href="${mvcPath}/resources/kfFlowRule/css/ruleIndex.css"/>
	<title>规则设置</title>
</head>
<body id="ruleIndexBody" >
    <div class="ruleIndex wrap">
	    <div class="row title">
    		<div class="titleCls">
				规则设计
			</div>
    	</div>
    	<div class="row contentArea">
    		<div class="spanEle">
    			<div id="ruleZtree" class="ztree">
    			</div>
    		</div>
    		
    	</div>
	</div>
</body>

<script type="text/javascript">
    var ruleIndexInfo = {
        staffno : "${staffno!''}",
        flowId : "${flowId!''}",
        nodeId : "${nodeId!''}",
        lineId : "${lineId!''}",
        opType : "${opType!''}",
        currentRules : ${currentRules!"[]"}
    };
</script>
	<script type="text/javascript" src="${mvcPath}/webjarslocator/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/layer/2.4/layer.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/laypage/laypage.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/template.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/js/work.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/zTree_v3/js/jquery.ztree.core.min.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/zTree_v3/js/jquery.ztree.excheck.min.js"></script>
    <script type="text/javascript" src="${mvcPath}/resources/kfFlowResources/zTree_v3/js/jquery.ztree.exedit.min.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowRule/js/ruleIndex.js"></script>
</html>