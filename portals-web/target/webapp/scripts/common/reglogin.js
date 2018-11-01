/**
 * 主函数入口
 */
$(document).ready(function(){
	// 初始化事件
	initReglogin();
});

function initReglogin() {
	$(".accountIpts").unbind("keydown");
	$(".accountIpts").bind('keydown', function (e) {
        var key = e.which;
        if (key == 13) {
            userLoginFn();
        }
    });
	$(".passwordIpts").unbind("keydown");
	$(".passwordIpts").bind('keydown', function (e) {
        var key = e.which;
        if (key == 13) {
			userLoginFn()
        }
    });
}

/**
 * 系统登录
 */
function userLoginFn() {
	// 验证必填项输入
	if(!isCheckUserInfoVal('Ipts')) {
		return;
	}
	// 发送登录请求
	var url = zuiMain.contentPath + 'user/login';
	$.ajax({
		type: 'POST',
		url: url,
		data: {
			'usernum': zuiMain.userAct,
			'userpwd': zuiMain.userPwd
		},
		success: function(callData) {
//			alert(callData);
			var json = eval('(' + callData + ')');
			if(json && !json.resultFlag) {
				showTipWin(false, json.message);
			}
			else {
				window.location.href = zuiMain.contentPath;
			}
		},
		error: function() {
			showTipWin(false, '处理失败，请稍后再试！');
		}
	});
}

/**
 * 验证必填项输入
 */
function isCheckUserInfoVal(cls) {
	// 工号
	zuiMain.userAct = $(".account"+cls).val();
	if(zuiMain.userAct.trim().length <= 0) {
		showTipWin(true, '请输入账号！');
		return false;
	}else{
		var myreg=/^[1][3,4,5,7,8][0-9]{9}$/; 
		if (!myreg.test(zuiMain.userAct)) { 
			showTipWin(true, '账号为手机号，您输入的账号格式不对，请检查！');
		return false; 
		} 
	}
	// 密码
	zuiMain.userPwd = $(".password"+cls).val();
	if(zuiMain.userPwd.trim().length <= 0) {
		showTipWin(true, '请输入密码！');
		return false;
	}
	// 验证码
	/*if(cls == "Ipt") {
		zuiMain.code = $(".code"+cls).val();
		if(zuiMain.code.trim().length <= 0) {
			showTipWin(true,'请输入验证码！');
			return false;
		}
	}*/
	return true;
}

/**
 * 获取验证码
 */
function createCode() {
	$("#codeImage").attr("src", "../verifyCode/getVerifyCode?timestamp="+new Date().getTime());
}
