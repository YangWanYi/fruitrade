<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.fruitrade.domain.NewsDo;"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<link href="https://cdn.bootcss.com/bootstrap-table/1.16.0/bootstrap-table.css" rel="stylesheet">
		<title>新闻详情</title>
		<style type="text/css">
			*{
				padding: 0 0;
				margin: 0 0;
			}
			body{
				width: 100%;
				height: 100%;
				overflow-x: hidden; 
				padding: 0px 10px;
			}
			.content{
				width: 60%;
				min-height: 600px;
				border: 1px solid #eee;
				margin: 5% 20%;
				text-align: center;
				border-radius: 10px;
				padding: 10px 10px;
				font-size: 18px;
			}
			#newsTitle{
			    font-size: 20px;
			    font-family: monospace;
			    font-weight: bold;
			}
			#newsTime{
				font-size: 13px;
	    		color: #8b8c80;
	    		line-height: 40px;
			}
			#newsContent{
			    text-align: left;
			    text-indent: 2rem;
			    font-size: 21px;
			    letter-spacing: 2px;
			    font-family: initial;
			}
		</style>
	</head>
	<body>
		<div class="content">
			<div id="newsTitle"></div>
			<div id="newsTime"></div>
			<div id="newsContent"></div>
		</div>
	</body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/bootstrap-table/1.16.0/bootstrap-table.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript">
		$(function() {
			var id = "${requestScope.newsData.id}";
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/getNewsById.action',
				data: {'id': id },
				acyns: false,
				success: function(s){
					$("#newsTitle").html(s.frontNewsData.title);
					$("#newsTime").html(s.frontNewsData.createTime.replace('T', ' '));
					$("#newsContent").html(s.frontNewsData.content);
				},
				error: function(e){
					alert("数据查询失败！");
				}
			});
		});
	</script>
</html>
