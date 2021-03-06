if(typeof zuiMain == 'undefined'){
	zuiMain = {};
}
zuiMain.winWidth = 0; 			// 网页可见区域宽度
zuiMain.winHeight = 0; 			// 网页可见区域高度
zuiMain.zuiHeaderHeight = 50;	// 顶部横向菜单栏高度
zuiMain.zuiLefterHeight = 0;	// 左边一级菜单栏高度
zuiMain.zuiLefterWidth = 180;	// 左边一级菜单栏宽度
zuiMain.zuiCenterWidth = 0;		// 中间主体区域宽度
zuiMain.nullSizeTwo = 4;		// 空白尺寸
/**
 * 入口函数
 */
$(document).ready(function(){
	// 初始化全局参数
	initStaticParams();
	// 加载主面板各类事件
	loadCenterPanelEvent();
	// 加载中间显示区域面板内容
	loadCenterShowData(null);
});
// 添加浏览器窗口大小改变事件
$(window).resize(initStaticParams);
/**
 * 初始化全局参数
 */
function initStaticParams() {
    // 获取窗口宽度
    if (window.innerWidth)
        zuiMain.winWidth = window.innerWidth;
    else if ((document.body) && (document.body.clientWidth))
        zuiMain.winWidth = document.body.clientWidth;
    // 获取窗口高度
    if (window.innerHeight)
        zuiMain.winHeight = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        zuiMain.winHeight = document.body.clientHeight;
    // 通过深入Document内部对body进行检测，获取窗口大小
    if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
        zuiMain.winHeight = document.documentElement.clientHeight;
        zuiMain.winWidth = document.documentElement.clientWidth;
    }
    // 初始化主面板
	initMainPanel();
};
/**
 * 初始化主面板
 */
function initMainPanel() {
	autoDataListPanelSize();
};
/**
 * 加载主面板各类事件
 */
function loadCenterPanelEvent() {
	$(".menuOneLevel").click(function(){
		var obj = $(this);
		var cldMenuId = obj.attr("id") + 'MenuList';
		var menuOneIcon = obj.children('.menuOneIcon');
		if($("#"+cldMenuId).is(":hidden")) {
			$("#"+cldMenuId).show();
			obj.children('.menuOneIcon').removeClass('zk_icon');
			obj.children('.menuOneIcon').addClass('sh_icon');
			obj.removeClass('bkg_37424f');
			obj.addClass('bkg_22282E');
		} else {
			$("#"+cldMenuId).hide();
			obj.children('.menuOneIcon').removeClass('sh_icon');
			obj.children('.menuOneIcon').addClass('zk_icon');
			obj.removeClass('bkg_22282E');
			obj.addClass('bkg_37424f');
		}
	});
	$(".menuChilder li a").click(function(){
		loadCenterShowData($(this).attr("action"), $(this));
	});
	// 退出系统事件
	$("#userLogoutPanel").click(function(){
		userLogout();
	});
};
/**
 * 加载中间显示区域面板内容
 */
function loadCenterShowData(reqUrl, pressed, funcName) {
	// 默认加载第一个菜单的数据
	if(!reqUrl) {
		pressed = $(".menunode").first();
		reqUrl = pressed.attr("action");
	}
	if(reqUrl) {
		var loadReqUrl = '';
		if(reqUrl.indexOf('http') < 0) {
			loadReqUrl = zuiMain.contentPath;
		}
		loadReqUrl += reqUrl;
		$.post(
			loadReqUrl, 
			{},
			function(data) {
				$(".zuiCenter").html(data);
				if(funcName) {
					funcName();
				}
			}
		);
		// 修改点击菜单样式
		updateBtmMenuStyle(pressed);
	} else {
		showTipWin(false, '未找到菜单资源的URL[' + reqUrl + ']');
	}
};
/**
 * 修改点击菜单样式
 */
zuiMain.selectMenuId = null;
function updateBtmMenuStyle(menuObj) {
	if(null != zuiMain.selectMenuId) {
		$('#'+zuiMain.selectMenuId).removeClass('bkg_37424f');
		$('#'+zuiMain.selectMenuId).addClass('bkg_22282E');
		$('#'+zuiMain.selectMenuId).addClass('menuHover');
	}
	zuiMain.selectMenuId = menuObj.attr('id');
	$('#'+zuiMain.selectMenuId).removeClass('bkg_22282E');
	$('#'+zuiMain.selectMenuId).addClass('bkg_37424f');
	$('#'+zuiMain.selectMenuId).removeClass('menuHover');
};
/**
 * 设置表单区域CSS属性
 */
function autoDataListPanelSize() {
	var zuiCenter = $(".zuiCenter");
	// 判断主体区域是否出现滚动条
	if(zuiCenter[0].scrollHeight > zuiCenter[0].clientHeight || zuiCenter[0].offsetHeight > zuiCenter[0].clientHeight) {
		if($(".data_list_div").length > 0 && $(".data_list_div").is(":visible")) {
			//  + 17 - 21
			$(".data_list_one").css({
				'height': ($(".data_list_tab").height() + $(".tabfotsm").height() + 16) + 'px',
				'bottom': $(".tabfotsm").height() + 'px'
			});
			$(".tabfotsm").removeClass('tabfootcls');
			$(".tabfotsm").addClass('tabfootclsfot');
		}
	}
	else {
		if($(".tabfotsm").length > 0 && $(".tabfotsm").is(":visible")) {
			$(".data_list_one").css({
				'height': ($(".data_list_tab").height() + $(".tabfotsm").height() - 24) + 'px',
				'bottom': $(".tabfotsm").height() + 'px'
			});
			$(".tabfotsm").removeClass('tabfootclsfot');
			$(".tabfotsm").addClass('tabfootcls');
		}
	}
	if($(".tabfotsm").length > 0 && $(".tabfotsm").is(":visible")) {
		$(".tabfotsm").css({
			'width': $(".data_list_div").width() + 'px'
		});
	}
}
/**
 * 退出系统操作
 */
function userLogout() {
	// 发送登录请求
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
