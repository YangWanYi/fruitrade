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
		<title>优易水果商城——后台</title>
		<style type="text/css">
			*{
				padding: 0 0;
				margin: 0 0;
			}
			body{
				width: 100%;
				height: 100%;
				overflow-x: hidden; 
			}
			.top{
				height: 70px;
				line-height: 55px;
				padding: 5px 10px;	
				background-color: #299cce;			
			}
			.dropdown-item{
				background: #fff;
				color: #299cce;
				text-align: center;
			}
			.dropdown-item:hover{
				background: #299cce;
				color: #fff;
			}
			.logoPos {
			    cursor: pointer;
			}
			.logoPos img{
				width: 70px;
			    height: 60px;
			    border-radius: 5px;
			}
			.webName{
				font-size: 20px;
			    font-family: cursive;
			    color: #ece7e7;
			}
			.menuList {
			    height: 40px;
			    line-height: 40px;
			    padding-top: 8px;
			}
			.menuList ol li{
				list-style: none;
			    float: left;
			    color: #ece7e7;
			    font-size: 20px;
			    font-weight: 400;
			    font-family: inherit;
			    width: 125px;
			    margin: auto 5px;
			    text-align: center;
			    cursor: pointer;
			}
			.active{
				display:inline-block;
			    color: #299cce;
		        background: #fff;
		        width: 125px;
			    text-align: center;
			    border-radius: 5px;
			}
			.topRight{
			    float: right;
			    padding-right: 30px;
			    font-family: inherit;
			    color: #ece7e7;
			    font-weight: 400;
			}
			.topRightBtn{
			    padding: 0px 5px;
			    cursor: pointer;
			}
			.topRightBtn:hover{
			    color: #fff;
			}
			.centerPos{
				padding: 0px 10px;
			}
			#mainIframe{
				width: 100%;
				border: none;
			}
			.bottom{
				height: 35px;
				line-height: 35px;
				bottom: 0px;
				position: fixed;
				width: 100%;
				text-align: center;
		        background: #08080861;
    			color: #070808;
			    font-family: cursive;
			}
		</style>
	</head>
	<body>
	<div class="content">
		<div class="row top">
		    <div class="col-3 logoPos">
		    	<img alt="logo" src="../imgs/LOGO.png">
		    	<span class="webName">优易水果商城后台管理</span>
		    </div>
		    <div class="col-7 menuList">
		    	<ol>
		    		<li id="user">
		    			<span class="dropdown">
		    				<span class="dropdown-toggle active menuOne" id="dropdownMenuLink" data-toggle="dropdown">用户管理</span>
							<span class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								<span class="dropdown-item roleMenu">角色维护</span>
								<span class="dropdown-item userMenu">用户维护</span>
								<span class="dropdown-item orderMng">订单维护</span>
							</span>
	    				</span>
		    		</li>
		    		<li id="news">
		    			<span class="menuOne">商品促销</span>
		    		</li>
		    		<li id="storehouse">
		    			<span class="dropdown">
		    				<span class="dropdown-toggle menuOne" id="dropdownMenuLink" data-toggle="dropdown">仓库管理</span>
							<span class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								<span class="dropdown-item storeMenu">仓库维护</span>
								<span class="dropdown-item storageMenu">货架维护</span>
								<span class="dropdown-item purchaseMenu">进货维护</span>
								<span class="dropdown-item outputStore">出库维护</span>
							</span>
	    				</span>
		    		</li>
		    		<li id="supplier">
		    			<span class="dropdown">
		    				<span class="dropdown-toggle menuOne" id="dropdownMenuLink" data-toggle="dropdown">供货商管理</span>
							<span class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								<span class="dropdown-item brandMenu">品牌维护</span>
								<span class="dropdown-item retailMenu">散户维护</span>
							</span>
	    				</span>
		    		</li>
		    		<li id="fruit">
		    			<span class="dropdown">
		    				<span class="dropdown-toggle menuOne" id="dropdownMenuLink" data-toggle="dropdown">商品管理</span>
							<span class="dropdown-menu" aria-labelledby="dropdownMenuLink">
								<span class="dropdown-item fruitKindMenu">水果分类</span>
								<span class="dropdown-item fruitMenu">水果维护</span>
							</span>
	    				</span>
		    		</li>
		    	</ol>
		    </div>
		    <div class="col-2">
		   		<span class="topRight">
					<span class="topRightBtn">${sessionScope.user.account}</span>|<span class="logout topRightBtn">退出</span>
	   			</span>
		    </div>
		  </div>
		<div class="row centerPos">
			<iframe id="mainIframe" src="user/user/userList.jsp"></iframe>
		</div>
		<div class="row bottom">
		    <div class="col-12">
		     	 优易水果商城后台管理中心
		    </div>
		</div>
	</div>
	</body>
	<script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script type="text/javascript">
		$(function(){
			var innerHeight = window.innerHeight;
			$("#mainIframe").css('height', innerHeight-105+'px');
		});
		

		$(".menuOne").click(function(){ // 切换菜单
			$(".menuOne").removeClass("active");
			$(this).addClass("active");
			$(".menuOne").css('color', '#ece7e7');
			$(this).css('color', '#299cce');
		});
		
		$(".roleMenu").click(function(){ // 角色维护
			$("#mainIframe").attr('src', 'user/role/roleList.jsp');
		});
		
		$(".userMenu").click(function(){ // 用户管理
			$("#mainIframe").attr('src', 'user/user/userList.jsp');
		});
		
		$(".storeMenu").click(function(){ // 仓库维护
			$("#mainIframe").attr('src', 'storehouse/storehouse/storehouseList.jsp');
		});
		
		$(".storageMenu").click(function(){ // 货架维护
			$("#mainIframe").attr('src', 'storehouse/storage/storageList.jsp');
		});
		
		$(".purchaseMenu").click(function(){ // 进货维护
			$("#mainIframe").attr('src', 'storehouse/purchase/purchaseList.jsp');
		});
		
		$(".brandMenu").click(function(){ // 品牌维护
			$("#mainIframe").attr('src', 'supplier/brand/brandList.jsp');
		});
		
		$(".retailMenu").click(function(){ // 散户维护
			$("#mainIframe").attr('src', 'supplier/retail/retailList.jsp');
		});
		
		$(".fruitKindMenu").click(function(){ // 水果分类
			$("#mainIframe").attr('src', 'goods/fruitclassify/fruitclassifyList.jsp');
		});
		
		$(".fruitMenu").click(function(){ // 水果维护
			$("#mainIframe").attr('src', '/toFruitListPage.action');
		});
		
		$("#news").click(function(){ // 商品促销管理
			$("#mainIframe").attr('src', 'news/newsList.jsp');
		});
		
		$(".outputStore").click(function(){ // 出库维护
			$("#mainIframe").attr('src', 'outputStore/outputStoreList.jsp');
		});
		
		$(".orderMng").click(function(){ // 订单维护
			$("#mainIframe").attr('src', 'order/orderList.jsp');
		});
		
		$(".logout").click(function(){ // 退出
			if(confirm('确定要退出吗？')){
				$.ajax({
					type: 'post',
					dataType: 'json',
					url: '/logout.action',
					async: false,
					success: function(s){
						location.href = "<%=basePath%>"; 
					},
					error: function(e){
						alert("退出失败！");
					}
				});
		    }
		});
	</script>
</html>
