<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.fruitrade.domain.UserDo;"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<title>编辑商品</title>
		<style type="text/css">
			*{
				padding: 0 0;
				margin: 0 0;
			}
			body{
				width: 100%;
				height: 90%;
				padding: 0px 5px;
			}
		</style>
	</head>
	<body>
		<form id="myForm">
			<input type="hidden" name="id" value="${requestScope.fruitData.id}">
			<input type="hidden" name="purchasingTime" value="${requestScope.fruitData.purchasingTime}">
			<input type="hidden" name="pic" value="${requestScope.fruitData.pic}">
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">水果名称</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="colFormLabelSm" name="fruitName" value="${requestScope.fruitData.fruitName}" placeholder="请输入水果名称">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">水果种类</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="colFormLabelSm" name="fruitClassifyID" value="${requestScope.fruitData.fruitClassifyID}">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">进货量</label>
			    <div class="col-sm-6">
			      <input type="number" class="form-control" id="colFormLabelSm" name="purchasingNum" value="${requestScope.fruitData.purchasingNum}">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">进货单位</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="colFormLabelSm" name="purchasingUnit" value="${requestScope.fruitData.purchasingUnit}">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">产地</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="colFormLabelSm" name="place" value="${requestScope.fruitData.place}" placeholder="请输入产地">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">进价</label>
			    <div class="col-sm-10">
			      <input type="number" class="form-control" id="colFormLabelSm" name="purchasingPrice" value="${requestScope.fruitData.purchasingPrice}" placeholder="请输入进价">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">售价</label>
			    <div class="col-sm-10">
			      <input type="number" class="form-control" id="colFormLabelSm" name="salePrice" value="${requestScope.fruitData.salePrice}" placeholder="请输入售价">
			    </div>
			</div>
		</form>
	</body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(function(){
			initSupplys(2);
		});
		
		// 加载供货商
		function initSupplys(supplyType){
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/listSupplier.action',
				data: {'supplyType': supplyType},
				async: false,
				success: function(s){
					var str = "";
					if(s.total >0){
						$(s.rows).each(function(m,n){
							str += '<option value="'+n.id+'" >'+n.companyName+'</option>';
						});
					}
					$("#supplyId").html(str);
				},
				error: function(e){
					alert("供货商加载失败！");
				}
			});
		}
	</script>
</html>
