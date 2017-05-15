
jQuery.extend(jQuery.validator.messages, {
        required: "请完善必填信息",
        tell: "电话格式不正确",
		remote: "请修正该字段",
		email: "请输入正确格式电子邮件",
		url: "网址不合法",
		date: "请输入正确的日期",
		dateISO: "不合法的日期 (ISO)",
		number: "请输入正确的数字",
		digits: "只能输入整数",
		creditcard: "卡号不合法",
		equalTo: "值不相同",
		accept: "不合法后缀",
		maxlength: jQuery.format("最多{0}字符"),
		minlength: jQuery.format("最少{0}字符"),
		rangelength: jQuery.format("字符长度介于 {0}和 {1}之间"),
		range: jQuery.format("数值不介于 {0}和 {1}之间"),
		max: jQuery.format("数值最大为{0}"),
		min: jQuery.format("数值最小为{0}")
});