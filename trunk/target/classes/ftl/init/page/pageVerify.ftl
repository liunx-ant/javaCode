		 	    $('#${mainObject.objectName}Page').setTemplateElement("pageTemplate");
				$('#${mainObject.objectName}Page').processTemplate((data_.data));
				pageBind("${mainObject.objectName}Page",pb,function(){${mainObject.objectName}.list(pb,listCallback);});
	