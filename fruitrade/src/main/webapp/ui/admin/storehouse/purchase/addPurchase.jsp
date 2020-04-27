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
		<title>新增商品</title>
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
		<form id="myForm" role="form">
			<input type="hidden" id="supplyVsFruitId" name="supplyVsFruitId"/>
			<div class="form-group row">
				<!-- 供货商类型 1：品牌 2：散户 -->
			    <label class="col-sm-2 control-label">供货商类型</label>
			    <div class="col-sm-7 input-group">
					<select class="form-control" name="supplyType" id="supplyType" onChange="initSupplys(value);">
						<option value="2">散户</option>
						<option value="1">品牌</option>
					</select>
			    </div>
			</div>
			<div class="form-group row">
			    <label class="col-sm-2 control-label">供货商</label>
			    <div class="col-sm-7 input-group">
					<select class="form-control" name="supplyId" id="supplyId" onChange="loadFruitKindList(null,value);">
					
					</select>
			    </div>
			</div>
			<div class="form-group row">
			    <label class="col-sm-2 control-label">水果分类</label>
			    <div class="col-sm-7 input-group">
					<select class="form-control" name="classifyId" id="classifyId" onChange="loadFruitList(null,null,value);">
					
					</select>
			    </div>
			</div>
			<div class="form-group row">
			    <label class="col-sm-2 control-label">水果</label>
			    <div class="col-sm-7 input-group">
					<select class="form-control" name="fruitId" id="fruitId" onChange="loadFruitPlace(null, null, null, null, value);">
					
					</select>
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 control-label">产地</label>
			    <div class="col-sm-7 input-group">
			      <input type="text" class="form-control" id="place" name="place" value="" placeholder="请输入产地">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 control-label">批发价</label>
			    <div class="col-sm-7 input-group">
			      <input type="text" class="form-control" id="price" name="price" value="" placeholder="请输入批发价">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 control-label">批发单位</label>
			    <div class="col-sm-7 input-group">
			      <input type="text" class="form-control" id="unit" name="unit" value="" placeholder="请输入批发单位">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 control-label">进货数量</label>
			    <div class="col-sm-7 input-group">
			      <input type="number" class="form-control" id="purchaseNum" name="purchaseNum" value="" placeholder="请输入进货数量">
			    </div>
			</div>
			<div class="form-group row">
			    <label for="colFormLabelSm" class="col-sm-2 control-label">售价</label>
			    <div class="col-sm-7 input-group">
			      <input type="number" class="form-control" id="salePrice" name="salePrice" value="" placeholder="请输入售价">
			    </div>
			</div>
			<div class="form-group row">
			    <label class="col-sm-2 control-label">选择仓库</label>
			    <div class="col-sm-7 input-group">
					<select class="form-control" name="storeHouseId" id="storeHouseId" onChange="loadStore(value);">
					
					</select>
			    </div>
			</div>
			<div class="form-group row">
			    <label class="col-sm-2 control-label">选择货架</label>
			    <div class="col-sm-7 input-group">
					<select class="form-control" name="storageId" id="storageId">
					
					</select>
			    </div>
			</div>
		</form>
	</body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(function(){
			initSupplys(2);// 加载水果分类
			loadStoreHouse();// 加载仓库
		});
		
		// 加载仓库
		function loadStoreHouse(){
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: "/listStorehouse.action",
				async: false,
				success: function(s){
					var str = "";
					if(s.total>0){
						$(s.rows).each(function(m,n){
							if(m==0){
								loadStore(n.id);// 加载货架
							}
							str += '<option value="'+n.id+'">'+n.storeHouseCode+'</option>';
						});
					}
					$("#storeHouseId").html(str);
				},
				error: function(e){
					alert("仓库查询失败！");
				}
			});
		}
		
		// 加载货架
		function loadStore(storeHouseId){
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: "/listStorage.action",
				data: {'storeHouseId': storeHouseId},
				async: false,
				success: function(s){
					var str = "";
					if(s.total>0){
						$(s.rows).each(function(m,n){
							str += '<option value="'+n.id+'">'+n.storageCode+'</option>';
						});
					}
					$("#storageId").html(str);
				},
				error: function(e){
					alert("货架查询失败！");
				}
			});
		}
		
		// 加载供货商数据
		function initSupplys(supplyType){
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: "/listSupplyVsFruit.action",
				data: {'supplyType': supplyType},
				async: false,
				success: function(s){
					var str = "";
					if(s.total>0){
						$(s.rows).each(function(m,n){
							if(m==0){
								loadFruitKindList(supplyType, n.supplyId);
							}
							if(str.indexOf(n.supplyName)==-1){
								str += '<option value="'+n.supplyId+'">'+n.supplyName+'</option>';
							}
						});
					}
					$("#supplyId").html(str);
				},
				error: function(e){
					alert("供货商查询失败！");
				}
			});
		}
		
		// 加载水果分类
		function loadFruitKindList(supplyType, supplyId){
			if(supplyType==null){
				supplyType = $("#supplyType").val();
			}
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/listSupplyVsFruit.action',
				data: {'supplyId': supplyId, 'supplyType': supplyType},
				async: false,
				success: function(s){
					var str = "";
					if(s.total>0){
						$(s.rows).each(function(m,n){
							if(m==0){
								loadFruitList(supplyType, n.supplyId, n.classifyId);
							}
							str += '<option value="'+n.classifyId+'">'+n.classifyName+'</option>';
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
		function loadFruitList(supplyType, supplyId, classifyId){
			if(supplyType==null){
				supplyType = $("#supplyType").val();
			}
			if(supplyId==null){
				supplyId = $("#supplyId").val();
			}
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/listSupplyVsFruit.action',
				data: {'classifyId': classifyId, 'supplyId': supplyId, 'supplyType': supplyType},
				async: false,
				success: function(s){
					var str = "";
					if(s.total>0){
						$(s.rows).each(function(m,n){
							if(m==0){
								loadFruitPlace(n.id, supplyType, supplyId, classifyId, n.fruitId);
							}
							str += '<option value="'+n.fruitId+'">'+n.fruitName+'</option>';
						});
					}
					$("#fruitId").html(str);
				},
				error: function(e){
					alert("水果列表查询失败！");
				}
			});
		}
		
		// 加载水果产地
		function loadFruitPlace(id, supplyType, supplyId, classifyId, fruitId){
			if(supplyType==null){
				supplyType = $("#supplyType").val();
			}
			if(supplyId==null){
				supplyId = $("#supplyId").val();
			}
			if(classifyId==null){
				classifyId = $("#classifyId").val();
			}
			$.ajax({
				type: 'post',
				dataType: 'json',
				url: '/listSupplyVsFruit.action',
				data: {'classifyId': classifyId, 'supplyId': supplyId, 'supplyType': supplyType, 'fruitId': fruitId},
				async: false,
				success: function(s){
					if(s.total>0){
						$("#place").val(s.rows[0].place);
						$("#price").val(s.rows[0].price);
						$("#unit").val(s.rows[0].unit);
						$("#supplyVsFruitId").val(s.rows[0].id);
					}
				},
				error: function(e){
					alert("产地查询失败！");
				}
			});
		}
	</script>
</html>
