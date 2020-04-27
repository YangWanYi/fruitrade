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
		<title>编辑品牌供应水果</title>
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
			<input type="hidden" name="id" value="${requestScope.supplyVsFruitData.id}">
			<input type="hidden" name="supplyType" value="${requestScope.supplyVsFruitData.supplyType}">
			<input type="hidden" name="supplyId" value="${requestScope.supplyVsFruitData.supplyId}">
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">水果分类</label>
			   	<div class="col-sm-10">
					<select class="form-control" name="classifyId" id="classifyId" onChange="loadFruitList(value)">
					
					</select>
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">水果</label>
			    <div class="col-sm-10">
					<select class="form-control" name="fruitId" id="fruitId">
					
					</select>
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 col-form-label">产地</label>
			    <div class="col-sm-6">
			     <textarea class="form-control" id="exampleFormControlTextarea1" name="place" rows="9" placeholder="请输入产地">${requestScope.supplyVsFruitData.place}</textarea>
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 control-label">批发价</label>
			    <div class="col-sm-7 input-group">
			      <input type="number" class="form-control" id="price" name="price" value="${requestScope.supplyVsFruitData.price}" placeholder="请输入批发价">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 control-label">批发单位</label>
			    <div class="col-sm-7 input-group">
			      <input type="text" class="form-control" id="unit" name="unit" value="${requestScope.supplyVsFruitData.unit}" placeholder="请输入批发单位">
			    </div>
			</div>
		</form>
	</body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(function(){
			loadFruitKind();// 加载水果分类
			$('#classifyId').find('option[value="${requestScope.supplyVsFruitData.classifyId}"]').attr('selected','selected'); 
			$('#fruitId').find('option[value="${requestScope.supplyVsFruitData.fruitId}"]').attr('selected','selected');
		});
		
		// 加载水果分类
		function loadFruitKind(){
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/listFruitClassify.action',
				async: false,
				success: function(s){
					var str = "";
					if(s.total>0){
						$(s.rows).each(function(m,n){
							if(m==0){
								loadFruitList(n.id);
							}
							str += '<option value="'+n.id+'">'+n.classifyName+'</option>';
						});
					}
					$("#classifyId").html(str);
				},
				error: function(e){
					alert("水果分类查询失败！");
				}
			});
		}
		
		// 加载水果分类下水果列表
		function loadFruitList(fruitClassifyID){
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/listFruit.action',
				data: {'fruitClassifyID': fruitClassifyID},
				async: false,
				success: function(s){
					var str = "";
					if(s.total>0){
						$(s.rows).each(function(m,n){
							str += '<option value="'+n.id+'">'+n.fruitName+'</option>';
						});
					}
					$("#fruitId").html(str);
				},
				error: function(e){
					alert("水果列表查询失败！");
				}
			});
		}
	</script>
</html>
