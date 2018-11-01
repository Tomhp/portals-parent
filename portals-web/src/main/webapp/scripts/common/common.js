$(document).ready(function(){
	// 退出系统事件
	$("#userLogoutPanel").click(function(){
		userLogout();
	});
	// 前提：所有ajax请求都是用jquery的$.ajax发起的，而非原生的XHR；
	zuiMain.ajaxBack = $.ajax;
	// 由于get/post/getJSON等，最后还是调用到ajax，因此只要改ajax函数即可
	$.ajax = function(setting) {
		// jquery-ajax请求完成在调用success方法前调用
	  	setting.dataFilter = function(data, type) {
			if(data && data.indexOf("UserNotLoginException") >= 0) {
				Etw.Controller.closeEtwAmong(Etw.type);
				Etw.Msg.alert('提示', '您已登录超时,请重新登录！',function(){
					window.location.href = zuiMain.contentPath;
				});
				
			} else {
				return data;
			}
		}
		// jquery-ajax请求完成并调用完success或error等方法后，最后一个调用改方法
	  	setting.complete = function() {
		    //alert(setting.complete.arguments[0].responseText);
	  	}
	  	zuiMain.ajaxBack(setting);
	}
	
});

/**------ 显示提示窗口 ------*/
function showTipWin(status, message) {
	if(zuiMain.deviceType == 'web') {
		Etw.Msg.show({
			title: '提示',
			isHaveMask: status,
			skin: 'gray',
			content: message
		});
	}
	else {
		Etw.Msg.appWin(message);
	}
}

/**------ 退出系统操作 ------*/
function userLogout() {
	// 发送退出请求
	var url = zuiMain.contentPath + 'user/logout';
	$.ajax({
		type: 'POST',
		url: url,
		data: {},
		success: function(callData) {
			window.location.href = zuiMain.contentPath;
		},
		error: function() {
			showTipWin(false, '处理失败，请稍后再试！');
		}
	});
}


