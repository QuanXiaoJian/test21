<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.8.3.min.js"></script>
	<script>
		$(function () {
			$("#addBtn").click(function () {
				location = "add.jsp";

			});

			$("#delBtn").click(function () {
				var param=""
				var check=$("input[name=chk]:checked")
				alert("删除选中")
				for(var i=0;i<check.length;i++){
					var id=check[i].value
					param+="id="+id

					if(check.length>i+1){
						param+="&";
					}
				}
				location="deleteAll?"+param



			});

			$("img[edit]").click(function () {
				var id = $(this).attr("edit");
				alert("编辑id为"+id+"的商品");
				location = "/edit?id="+id;
			})


			$("img[del]").click(function () {
				var id = $(this).attr("del");
				alert("删除id为"+id+"的商品");
				location = "/deletepro?id="+id;
			})
		})



/*


		$(function () {
			$("img[del]").click(function () {
				var id = $(this).attr("del");

				if(!confirm("确认删除吗？"))return;

				location="deletepro？id="+id;

			})
			
			$("img[edit]").click(function () {

			})

		})*/

	</script>
</head>
<body>
<div style="padding: 10px;">
	<div class="option">
		<input id="addBtn" type="button" value="添加" />
		<input id="delBtn" type="button" value="删除选中" />
	</div>
	<table cellspacing="0" cellpadding="0" bordercolor="gray" border="1" width="100%">
		<tr style="font-weight: bold; font-size: 12pt; height: 25px; background-color: #afd1f3">
			<td width="40px" align="center">
				<input type="checkbox" id="selectAll" />
			</td>
			<td width="80px" align="center">序号</td>
			<td width="" align="center">商品名称</td>
			<td width="100px" align="center">商品价格</td>
			<td width="100px" align="center">剩余数量</td>
			<td width="500px" align="center">商品描述</td>
			<td width="120px" align="center">编辑</td>
		</tr>

		<c:forEach items="${pList}" var="p" varStatus="sta">
			<tr>
				<td align="center">
					<input type="checkbox" name="chk" value="${p.id}"/>
				</td>
				<td style="height: 22px" align="center">${sta.count}</td>
				<td style="height: 22px" align="left">${p.name}</td>
				<td style="height: 22px" align="center">${p.price}</td>
				<td style="height: 22px" align="center">${p.num}</td>
				<td style="height: 22px" align="left"><div>${p.description}</div></td>
				<td align="center">
					<img edit="${p.id}" src="${pageContext.request.contextPath}/static/images/i_edit.gif" border="0" style="cursor: hand">&nbsp;&nbsp;
					<img del="${p.id}" src="${pageContext.request.contextPath}/static/images/i_del.gif" border="0" style="cursor: hand">
				</td>
			</tr>
		</c:forEach>

	<%--放数据--%>
	<%--<tbody id="dataBody"></tbody>--%>
	</table>
</div>
</body>
</html>