<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/common/jspf/common.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->
<title>后台配置</title>
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<!--子页面begin-->
<div class="iframe-cot pull-right">
	<div class="right-form mt0">
		<form onsubmit="return false">
				<div class="crumbs">
                    <p>
                        <span class="active mt12"><i class="fa fa-book mr5"></i>基本信息</span>
                    </p>
                    <div class="crumbs-line"></div>
                </div>
				<ul class="w100">	
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>作者：</label>
					    <div class="form-content">
							<input class=" inputText required" name="createCodeObjects.author" value="${user.configInformation.createCodeObjects.author}" type="text">
					    </div>
					</li>
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>版本号：</label>
					    <div class="form-content">
							<input class=" inputText required" name="createCodeObjects.version" value="${user.configInformation.createCodeObjects.version}" type="text">
					    </div>
					</li>
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>版权：</label>
					    <div class="form-content">
							<input class=" inputText required" name="createCodeObjects.copyright" value="${user.configInformation.createCodeObjects.copyright}" type="text">
					    </div>
					</li>
				</ul>
				<div class="crumbs">
                    <p>
                        <span class="active mt12"><i class="fa fa-book mr5"></i>数据库信息</span>
                    </p>
                    <div class="crumbs-line"></div>
                </div>
				<ul class="w100">	
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>数据库连接：</label>
					    <div class="form-content">
							<input class=" inputText required" name="dbConfig.dbHost" value="${user.configInformation.dbConfig.dbHost}" type="text">
					    </div>
					</li>
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>端口号：</label>
					    <div class="form-content">
							<input class=" inputText required" name="dbConfig.dbPort" value="${user.configInformation.dbConfig.dbPort}" type="text">
					    </div>
					</li>
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>用户：</label>
					    <div class="form-content">
							<input class=" inputText required" name="dbConfig.dbUser" value="${user.configInformation.dbConfig.dbUser}" type="text">
					    </div>
					</li>
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>密码：</label>
					    <div class="form-content">
							<input class=" inputText required" name="dbConfig.dbPassword" value="${user.configInformation.dbConfig.dbPassword}" type="text">
					    </div>
					</li>
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>实例：</label>
					    <div class="form-content">
							<input class=" inputText required" name="dbConfig.dbName" value="${user.configInformation.dbConfig.dbName}" type="text">
					    </div>
					</li>
				</ul>
				<div class="crumbs">
                    <p>
                        <span class="active mt12"><i class="fa fa-book mr5"></i>项目信息</span>
                    </p>
                    <div class="crumbs-line"></div>
                </div>
				<ul class="w100" <c:if test = "${!isDevp}">style="display: none;"</c:if> >	
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>实体类项目路径：</label>
					    <div class="form-content">
							<input class=" inputText required" name="config.domainProjectPath" value="${user.configInformation.config.domainProjectPath}" type="text">
					    </div>
					</li>
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>service项目路径：</label>
					    <div class="form-content">
							<input class=" inputText required" name="config.apiProjectPath" value="${user.configInformation.config.apiProjectPath}" type="text">
					    </div>
					</li>
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>app项目路径：</label>
					    <div class="form-content">
							<input class=" inputText required" name="config.appProjectPath" value="${user.configInformation.config.appProjectPath}" type="text">
					    </div>
					</li>
				</ul>
				<ul class="w100">	
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>实体类项目结构：</label>
					    <div class="form-content">
							<input class=" inputText required" name="config.domainPackageNamePrefix" value="${user.configInformation.config.domainPackageNamePrefix}" type="text">
					    </div>
					</li>
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>service项目结构：</label>
					    <div class="form-content">
							<input class=" inputText required" name="config.apiPackageNamePrefix" value="${user.configInformation.config.apiPackageNamePrefix}" type="text">
					    </div>
					</li>
					<li>
					    <label class="ser-label"><em class="text-red mr5">*</em>app项目结构：</label>
					    <div class="form-content">
							<input class=" inputText required" name="config.appPackageNamePrefix" value="${user.configInformation.config.appPackageNamePrefix}" type="text">
					    </div>
					</li>
				</ul>
		</form>
		<ul>
               <li>
                   <label class="ser-label">&nbsp;</label>
                   <div class="form-content clearfix">
                       <button class="btn btn-orange" id="save"><i class="fa fa-save"></i>保存</button>
                   </div>
               </li>
		</ul>	
		</div>
	</div>				
    <!--子页面end-->
                 
</body>
<script type="text/javascript">
	$("#save").bind("click",function(){
		if($("form").valid()){
			ajaxService.postAjax("/saveConfig",$("form").serialize(),function(data){
				if(data.result=='sucess'){
					window.location="/list";
				}
			});
		}else{
			layer.msg("请完善信息",{icon:7});
		}
	});
</script>
</html>