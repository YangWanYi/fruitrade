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
		<title>新闻中心</title>
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
				border-radius: 10px;
				padding: 10px 10px;
				font-size: 18px;
			}
			.eachNews{
				height: 35px;
				line-height: 35px;
				cursor: pointer;
			}
			.newsTitle{
				letter-spacing: 2px;
			    display: inline-block;
			    font-family: monospace;
			}
			.newsTime{
				float: right;
			    padding-right: 10px;
			    font-size: 15px;
			    color: #827c7c;
			}
		</style>
	</head>
	<body>
		<div class="content">
		
		</div>
	</body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/bootstrap-table/1.16.0/bootstrap-table.js"></script>
	<script src="https://cdn.bootcss.com/bootstrap-table/1.16.0/locale/bootstrap-table-zh-CN.js"></script>
	<script type="text/javascript">
		$(function() {
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/listNews.action',
				acyns: false,
				success: function(s){
					var str = '<span class="noData">暂无数据！</span>';
					if(s.total>0){
						str = '';
						$(s.rows).each(function(m,n){
							str += '<div class="eachNews" onClick="toDteail(\''+n.id+'\');"><span class="newsTitle">'+n.title+'</span><span class="newsTime">'+n.createTime.replace('T', ' ')+'</span></div>';
						});
						$(".content").html(str);
					}
				},
				error: function(e){
					alert("数据查询失败！");
				}
			});
		});
		
		// 去新闻详情页
		function toDteail(id){
			parent.$("#mainIframe").attr('src','/toNewsDetailPage.action?id='+id);
		}
	</script>
</html>
