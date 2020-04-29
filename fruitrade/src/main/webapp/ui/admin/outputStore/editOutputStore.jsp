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
		<title>编辑出库</title>
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
			<input type="hidden" name="id" value="${requestScope.outputStoreData.id}">
			<input type="hidden" name="storeId" value="${requestScope.outputStoreData.storeId}">
			<input type="hidden" name="fruitId" value="${requestScope.outputStoreData.fruitId}">
			<input type="hidden" name="createId" value="${requestScope.outputStoreData.createId}">
			<input type="hidden" name="createTime" value="${requestScope.outputStoreData.createTime}">
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">仓库名称</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="colFormLabelSm" name="storeName" value="${requestScope.outputStoreData.storeName}" readonly="readonly" placeholder="请输入仓库名称">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">仓库编号</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="colFormLabelSm" name="storeCode" value="${requestScope.outputStoreData.storeCode}" readonly="readonly" placeholder="请输入仓库编号">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">仓库位置</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="colFormLabelSm" name="storePlace" value="${requestScope.outputStoreData.storePlace}" readonly="readonly" placeholder="请输入仓库位置">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">联系人名称</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="colFormLabelSm" name="linkman" value="${requestScope.outputStoreData.linkman}" readonly="readonly" placeholder="请输入联系人名称">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">联系人电话</label>
			    <div class="col-sm-10">
			      <input type="tel" class="form-control" id="colFormLabelSm" name="contactNum" value="${requestScope.outputStoreData.contactNum}" readonly="readonly" placeholder="请输入联系人电话">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">水果名称</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="colFormLabelSm" name="fruitName" value="${requestScope.outputStoreData.fruitName}" readonly="readonly" placeholder="请输入水果名称">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">水果出货量  </label>
			    <div class="col-sm-10">
			      <input type="number" class="form-control" id="colFormLabelSm" name="outPutNum" value="${requestScope.outputStoreData.outPutNum}" placeholder="请输入水果出货量 ">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">水果折损量  </label>
			    <div class="col-sm-10">
			      <input type="number" class="form-control" id="colFormLabelSm" name="wreckNum" value="${requestScope.outputStoreData.wreckNum}" placeholder="请输入水果折损量 ">
			    </div>
			</div>
		</form>
	</body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</html>
