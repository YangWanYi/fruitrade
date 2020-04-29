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
		<link href="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/css/bootstrap-datetimepicker.min.css" rel="stylesheet" />
		<title>编辑订单</title>
		<style type="text/css">
			*{
				padding: 0 0;
				margin: 0 0;
			}
			body{
				width: 90%;
				height: 90%;
				padding: 0px 5px;
			}
		</style>
	</head>
	<body>
		<form id="myForm">
			<input type="hidden" name="id" value="${requestScope.orderData.id}">
			<input type="hidden" name="userId" value="${requestScope.orderData.userId}">
			<input type="hidden" name="orderState" value="${requestScope.orderData.orderState}">
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">客户姓名</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="colFormLabelSm" name="userName" value="${requestScope.orderData.userName}" readonly="readonly">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">订单状态</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="colFormLabelSm" name="orderStateX" value="${requestScope.orderData.orderStateX}" readonly="readonly">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">总额（元）</label>
			    <div class="col-sm-10">
			      <input type="number" class="form-control" id="colFormLabelSm" name="totalPrice" value="${requestScope.orderData.totalPrice}" placeholder="请输入总额">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">邮费（元）</label>
			    <div class="col-sm-10">
			      <input type="number" class="form-control" id="colFormLabelSm" name="postage" value="${requestScope.orderData.postage}" placeholder="请输入邮费">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">下单时间</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="colFormLabelSm" name="createTime" value="${requestScope.orderData.createTime}" readonly="readonly">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">预计送达时间</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control datetimepicker-input"  data-toggle="datetimepicker" data-target="#datetimepicker5" id="planDeliveryTime" name="planDeliveryTime" value="${requestScope.orderData.planDeliveryTime}" placeholder="请输入预计送达时间">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">付款时间</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="colFormLabelSm" name="payTime" value="${requestScope.orderData.payTime}" readonly="readonly">
			    </div>
			</div>
		</form>
	</body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="https://cdn.bootcss.com/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript">
	 $(function () {
         $('#planDeliveryTime').datetimepicker({
		     format: 'YYYY-MM-DD',
		     locale: 'zh-CN'    
	     });
     });
	</script>
</html>
