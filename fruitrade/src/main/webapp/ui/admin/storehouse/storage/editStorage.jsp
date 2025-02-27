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
		<title>编辑仓库</title>
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
			<input type="hidden" name="id" value="${requestScope.storageData.id}">
			<div class="form-group row">
			    <label class="col-sm-2 control-label">选择仓库</label>
			    <div class="col-sm-7 input-group">
					<select class="form-control" name="storeHouseId" id="storeHouseId">
						
					</select>
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">货架编号</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control" id="colFormLabelSm" name="storageCode" value="${requestScope.storageData.storageCode}" placeholder="请输入货架编号">
			    </div>
			</div>
		</form>
	</body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script type="text/javascript">
	$(function(){
		$.ajax({
			type: 'post',
			dataType: 'json',
			url: '/listStorehouse.action',
			async: false,
			success: function(s){
				var str = "";
				if(s.total>0){
					$(s.rows).each(function(m,n){
						str += '<option value="'+n.id+'">'+n.storeHouseCode+'</option>';
					});
				}
				$("#storeHouseId").html(str);
			},
			error: function(e){
				alert("仓库查询失败！");
			}
		});
		
		$('#storeHouseId').find('option[value="${requestScope.storageData.storeHouseId}"]').attr('selected','selected'); 
	});
	</script>
</html>
