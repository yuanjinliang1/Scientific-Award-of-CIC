<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="dicipulus" tagdir="/WEB-INF/tags"%>
<%@ page session="true" %>
<!-- Set charset encoding to utf-8  -->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setCharacterEncoding("UTF-8");
%>

<html>
<head>
	<jsp:include page="fragments/header.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/wangEditor/dist/css/wangEditor.min.css" />">
	<title>五、客观评价</title>
</head>
<body>
<div class="container">
			<dicipulus:bodyHeaderForApplier menuName="editApplication"/>
	<div class="wrapper">
		<dicipulus:bodySidebarForEdit page="5"/>
		<div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row" style="margin-left: 20px"><h1>五、客观评价<small>（限两页）</small></h1></div>
            	<form id="fifthFormer" action="/app/edit-objective-evaluation" method="POST" modelAttribute="objectiveEvaluation"
            		data-toggle="validator" role="form">
					<div class="panel panel-default">
						<div class="panel-heading">
							<div class="row">
							<h4 style="margin-left:20px">
								客观评价
							</h4>
							</div>
						</div>
							<div  id="editor-container" class="row panel-body form-group" style="padding-top:0px;padding-bottom:0px">
					        	<!--  <div id="editor-trigger" style="height:400px" ><p>请输入内容</p><p>&lt;script&gt;&lt;/script&gt;</p></div>-->
						        <textarea id="editor-trigger" style="height:400px;display:none;" name="objectiveEvaluation" 
							placeholder="限2页.&#13;&#10围绕科技创新点的创新性、先进性、应用效果和对行业科技进步的作用，做出客观、真实、准确评价。填写的评价意见要有客观依据，主要包括与国内外相关技术的比较，国家相关部门正式作出的技术检测报告、验收意见、鉴定结论，国内外重要科技奖励，国内外同行在重要学术刊物、学术专著和重要国际学术会议公开发表的学术性评价意见等，可在附件中提供证明材料。非公开资料（如私人信函等）不能作为评价依据。"
							form="fifthFormer" data-error="请填写此项" required>${objectiveEvaluationForm.objectiveEvaluation}</textarea>
							<div class="help-block with-errors" style="font-size:15px"></div>
						    </div>
					</div>
					<div class="row" style="margin-left:20px">
						<input type="submit" class="btn btn-primary" value="保存" />
						<spring:url value="/edit-fourth-form" var="fourthFormURL"/>
							<a class="btn btn-default" href="${fn:escapeXml(fourthFormURL)}">上一页</a>
						<c:if test="${applier.applicationType=='科技进步类'||applier.applicationType=='技术发明类' }">
							<spring:url value="/manage-apply-unit-situation" var="sixthFormURL"/>
						</c:if>
						<c:if test="${applier.applicationType=='自然科学类' }">
							<spring:url value="/manage-paper-monograph" var="sixthFormURL"/>
						</c:if>
							<a class="btn btn-default" href="${fn:escapeXml(sixthFormURL)}">下一页</a>
					</div>
				</form>
        </div>
		</div>
	</div>
</div>
<spring:url value="/attached/{applierUid}/5" var="uploadURL">
	<spring:param name="applierUid" value="${person.uid}"></spring:param>
</spring:url>
<script type="text/javascript" src="<c:url value="/resources/wangEditor/dist/js/lib/jquery-1.10.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/wangEditor/dist/js/wangEditor.js" />"></script>
<!--<script type="text/javascript" src="../dist/js/wangEditor.min.js"></script>-->
<script type="text/javascript">
    // 阻止输出log
    // wangEditor.config.printLog = false;

    var editor = new wangEditor('editor-trigger');

    // 上传图片
    editor.config.uploadImgUrl = '${uploadURL}';
    editor.config.uploadParams = {
        // token1: 'abcde',
        // token2: '12345'
    };
    editor.config.uploadHeaders = {
        // 'Accept' : 'text/x-json'
    }
    // editor.config.uploadImgFileName = 'myFileName';

    // 隐藏网络图片
    // editor.config.hideLinkImg = true;

    // 表情显示项
    editor.config.emotionsShow = 'value';
    editor.config.emotions = {
        'default': {
            title: '默认',
            data: './emotions.data'
        },
        'weibo': {
            title: '微博表情',
            data: [
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/7a/shenshou_thumb.gif',
                    value: '[草泥马]'    
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/60/horse2_thumb.gif',
                    value: '[神马]'    
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/bc/fuyun_thumb.gif',
                    value: '[浮云]'    
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/c9/geili_thumb.gif',
                    value: '[给力]'    
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/f2/wg_thumb.gif',
                    value: '[围观]'    
                },
                {
                    icon: 'http://img.t.sinajs.cn/t35/style/images/common/face/ext/normal/70/vw_thumb.gif',
                    value: '[威武]'
                }
            ]
        }
    };

    // 只粘贴纯文本
    // editor.config.pasteText = true;

    // 跨域上传
    // editor.config.uploadImgUrl = 'http://localhost:8012/upload';

    // 第三方上传
    // editor.config.customUpload = true;

    // 普通菜单配置
    // editor.config.menus = [
    //     'img',
    //     'insertcode',
    //     'eraser',
    //     'fullscreen'
    // ];
    // 只排除某几个菜单（兼容IE低版本，不支持ES5的浏览器），支持ES5的浏览器可直接用 [].map 方法
    // editor.config.menus = $.map(wangEditor.config.menus, function(item, key) {
    //     if (item === 'insertcode') {
    //         return null;
    //     }
    //     if (item === 'fullscreen') {
    //         return null;
    //     }
    //     return item;
    // });

    // onchange 事件
    // editor.onchange = function () {
    //     console.log(this.$txt.html());
    // };

    // 取消过滤js
    // editor.config.jsFilter = false;

    // 取消粘贴过来
    // editor.config.pasteFilter = false;

    // 设置 z-index
    // editor.config.zindex = 20000;

    // 语言
    // editor.config.lang = wangEditor.langs['en'];

    // 自定义菜单UI
    // editor.UI.menus.bold = {
    //     normal: '<button style="font-size:20px; margin-top:5px;">B</button>',
    //     selected: '.selected'
    // };
    // editor.UI.menus.italic = {
    //     normal: '<button style="font-size:20px; margin-top:5px;">I</button>',
    //     selected: '<button style="font-size:20px; margin-top:5px;"><i>I</i></button>'
    // };
    editor.create();
</script>
</body>
</html>