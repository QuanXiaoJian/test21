<%@ page contentType="text/html; charset=UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/style.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript">
		$(function () {
			$("#yes").click(function () {
				var id=$(".bg:eq(0)").val()
				$.ajax({

					url: "/addpro?id="+id,
					success:function(data) {

						if(data.success) {
							location="index_ajax.jsp" // 重写加载列表
						}


						if (data.msg) {
							alert(data.msg);
						}
					}


				})

			})
		})

		</script>
<%--
	<script type="text/javascript">
		$(function () {
			$()
		})
	</script>--%>
</head>
<body>
	<form action="addpro" method="get">
		<table cellSpacing="1" cellPadding="5" align="center">
			<tr>
				<td class="ta_01" bgColor="#afd1f3" colSpan="2" height="26">
					<strong>添加商品</strong>
				</td>
			</tr>
			<tr>
				<td width="30%" align="center" bgColor="#f5fafe" class="ta_01">
					商品名称：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" class="bg" name="name"/>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">
					商品价格：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" class="bg" name="price"/>
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">
					库存数量：
				</td>
				<td class="ta_01" bgColor="#ffffff">
					<input type="text" class="bg" name="num" />
				</td>
			</tr>
			<tr>
				<td align="center" bgColor="#f5fafe" class="ta_01">
					商品描述：
				</td>
				<td class="ta_01" bgColor="#ffffff" colspan="3">
					<textarea rows="5" style="width:100%;" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="2">
					<input type="submit" value="确定" id="yes"/>&nbsp;&nbsp;
					<input type="reset" value="重置" id="re"/>&nbsp;&nbsp;
					<input id="goBack" type="button" value="返回" onclick="history.go(-1)" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>