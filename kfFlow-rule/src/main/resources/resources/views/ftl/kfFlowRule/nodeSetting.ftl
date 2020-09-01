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
    <link rel="stylesheet" type="text/css" href="${mvcPath}/resources/kfFlowRule/css/nodeSetting.css"/>
	<title>流程节点条件设置</title>
</head>

<body>
    <div class="wrap">
	    <label id="workGroupSet">
	        <span>工作组设置</span>
	        <input type="radio" name="tab" checked>
	        <div class="content">
	        	<#include "/kfFlowRule/workGroupSet.ftl">
	        </div>
	    </label>
	    <label id="ruleSet">
	        <span>规则设置</span>
	        <input type="radio" name="tab">
	        <div class="content">
	        	<div class="row" style="width:700px;height: 408px;">
	        		<div class="spanEle" style="width:690px;border: 1px solid #00B4E1;margin:0px 5px;overflow:auto;">
	        			<#include "/kfFlowRule/ruleSet.ftl">
	        		</div>
	        	</div>
	        </div>
	    </label>
	    <label id="actSet">
	        <span>行为设置</span>
	        <input type="radio" name="tab">
	        <div class="content">
	        	<div class="row" style="width:700px;height: 408px;">
	        		<div class="spanEle" style="width:690px;border: 1px solid #00B4E1;margin:0px 5px;overflow:auto;">
	        			<#include "/kfFlowRule/actSet.ftl">
	        		</div>
	        	</div>
	        </div>
	    </label>
	    <label id="nodeStepSet">
	        <span>时限设置</span>
	        <input type="radio" name="tab">
	        <div class="content">
	        	<div class="row" style="width:700px;height: 408px;">
	        		<div class="spanEle" style="width:690px;border: 1px solid #00B4E1;margin:0px 5px;overflow:auto;">
	        			<#include "/kfFlowRule/nodeStepSet.ftl">
	        		</div>
	        	</div>
	        </div>
	    </label>
	    <label id="backFormSet">
	        <span>回单设置</span>
	        <input type="radio" name="tab">
	        <div class="content">
	        	<div class="row" style="width:700px;height: 458px;font-size: 12px;margin: 0 auto;">
	        		<div class="spanEle" style="width:690px;/*border: 1px solid #00B4E1;*/margin:0px 5px;overflow:auto;">
	        			<#include "/kfFlowRule/backFormSet.ftl">
	        		</div>
	        	</div>
	        </div>
	    </label>
	    <label id="wbSet">
	        <span>外部接口设置</span>
	        <input type="radio" name="tab">
	        <div class="content">
	        	<div class="row" style="width:700px;height: 458px;font-size: 12px;margin: 0 auto;">
	        		<div class="spanEle" style="width:690px;/*border: 1px solid #00B4E1;*/margin:0px 5px;overflow:auto;">
	        			<#include "/kfFlowRule/wbSet.ftl">
	        		</div>
	        	</div>
	        </div>
	    </label>
	</div>
</body>

<script type="text/javascript">
    var workGroupInfo = {
        staffno: "${staffno!''}",
        flowId: "${flowId!''}",
        nodeId: "${nodeId!''}",
        nodeType: "${nodeType!''}",
        currentNodeWorkGroups:${currentNodeWorkGroups!"[]"},
        currentNodeRules:${currentNodeRules!"[]"},
        currentLines : ${currentLines!"[]"},
        timeLimitSet : ${currentNodeTimeLimitSet!"[]"}
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
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowRule/js/nodeSetting.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowRule/js/ruleSet.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowRule/js/actSet.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowRule/js/nodeStepSet.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowRule/js/backFormSet.js"></script>
	<script type="text/javascript" src="${mvcPath}/resources/kfFlowRule/js/wbSet.js"></script>
</html>