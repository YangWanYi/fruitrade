<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>个人中心</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
		<style type="text/css">
			body{
				width: 100%;
				height: 100%;
			}
			form{
				width: 30%;
				text-align: center;
				margin: 5% auto;
				border: 1px solid #eee;
				border-radius: 5px;
				padding: 10px;
			}
		</style>
	</head>
	<body>
		<form id="myForm">
			<input type="hidden" name="roleType" value="${sessionScope.user.roleType}">
			<input type="hidden" name="id" value="${sessionScope.user.id}">
			<input type="hidden" name="password" value="${sessionScope.user.password}">
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-3 col-form-label">用户名</label>
			    <div class="col-sm-9">
			      <input type="email" class="form-control" id="colFormLabelSm" name="account" value="${sessionScope.user.account}" placeholder="请输入登录账号">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-3 col-form-label">姓名</label>
			    <div class="col-sm-9">
			      <input type="email" class="form-control" id="colFormLabelSm" name="userName" value="${sessionScope.user.userName}" placeholder="请输入您的姓名">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-3 col-form-label">性别</label>
			    <div class="col-sm-9">
					<select class="form-control" name="gender" id="gender">
						<option value="0">男</option>
						<option value="1">女</option>
					</select>
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-3 col-form-label">联系方式</label>
			    <div class="col-sm-9">
			      <input type="number" class="form-control" id="colFormLabelSm" name="phoneNum" value="${sessionScope.user.phoneNum}" placeholder="请输入手机号">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-3 col-form-label">邮箱地址</label>
			    <div class="col-sm-9">
			      <input type="email" class="form-control" id="colFormLabelSm" name="email" value="${sessionScope.user.email}" placeholder="请输入邮箱地址">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-3 col-form-label">账户余额（元）</label>
			    <div class="col-sm-9">
			      <input type="number" class="form-control" id="balance" name="balance"  readonly="readonly" value="${sessionScope.user.balance}">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-3 col-form-label">收货地址</label>
			    <div class="col-sm-9">
			      <input type="email" class="form-control" id="colFormLabelSm" name="address" value="${sessionScope.user.address}" placeholder="请输入收货地址">
			    </div>
			</div>
			<input type="button" id="updateNow" class="btn btn-primary" value="确定修改"/>
		</form>
	</body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(function(){
			$('#gender').find('option[value="${sessionScope.user.gender}"]').attr('selected','selected'); //性别回显
			$("#updateNow").click(function(){
				$.ajax({
					type: 'post',
					dataType: 'json',
					url: '/updateUser.action',
					data: $("#myForm").serialize(),
					async: false,
					success: function(s){
						alert("保存成功！");
						location.reload(); //刷新当前页面
					},
					error: function(e){
						alert("保存失败！");
					}
				});
			});
		});
	</script>
</html>