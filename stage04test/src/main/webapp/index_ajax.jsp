<%--
  Created by IntelliJ IDEA.
  User: QUANXIAOJIAN
  Date: 2021/8/17
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/style.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-1.8.3.min.js"></script>
    <script>
        jQuery(function ($) {
            // 发送异步请求，获取商品列表，初始化表格数据
            function load() {
                $.ajax({
                    url: "index.ajax",
                    //success: function(data) {
                    success:function(s) { // ES6对象增强写法！

                        // 使用循环拼接html内容
                        var html = "";
                        for(var i = 0; i < s.length; i++) {
                            var p = s[i];
                            html += '<tr>';
                            html += '	<td align="center">';
                            html += '		<input value="'+p.id+'" type="checkbox" name="chk" />';
                            html += '	</td>';
                            html += '	<td style="height: 22px" align="center">'+(i+1)+'</td>';
                            html += '	<td style="height: 22px" align="left">'+p.name+'</td>';
                            html += '	<td style="height: 22px" align="center">'+p.price+'</td>';
                            html += '	<td style="height: 22px" align="center">'+p.num+'</td>';
                            html += '	<td style="height: 22px" align="left"><div>'+p.description+'</div></td>';
                            html += '	<td align="center">';
                            html += '		<img edit="'+p.id+'" src="/static/images/i_edit.gif" border="0" style="cursor: hand">&nbsp;&nbsp;';
                            html += '		<img del="'+p.id+'" src="/static/images/i_del.gif" border="0" style="cursor: hand">';
                            html += '	</td>';
                            html += '</tr>';
                        }
                        $("#dataBody").html( html );
                    }
                })
            }

            load();




            function ajaxDel(id,i) {

                $.ajax({

                    url: "/deletepro?id="+id,
                    success:function(data) {

                        if(data.success) {
                            load(); // 重写加载列表
                        }

                    i=i+1
                        if (data.msg) {
                            if(i<=1)
                            alert(data.msg);
                        }
                    }
                })
            }

            // 使用委派机制进行事件绑定：将事件绑定到目标元素的父元素上，然后指定目标元素以及事件即可
            $("#dataBody").on("click", "img[del]", function () {
                var id = $(this).attr("del");
                if ( !confirm("确认删除吗？") ) return;
                ajaxDel(id);

            });


            $("#delBtn").click(function () {

                var $cks = $(":checkbox[name=chk]:checked");
                if ($cks.size() == 0) {
                    alert("请选择删除项！");
                    return ;
                }

                if ( !confirm("确认删除吗？") ) return;

                // 得到“1,2,3”这种格式的数据
                //var id = "";
                var i=0
                $cks.each(function () {
                    var id =this.value;
                    //id += "," +this.value;

                    ajaxDel(id,i);
                    i=i+1
                });
                // ",1,2,3" ===> "1,2,3"
                //id = id.substring(1);


            });

            $("#addBtn").click(function () {
                location="add.jsp"
            })


            /*function editpro(id){

            }*/
            $("#dataBody").on("click", "img[edit]", function () {
                var id = $(this).attr("edit");
                if ( !confirm("确认编辑吗？") ) return;
                //editpro(id);
               /* $.ajax(function () {
                    url:"edit?id="+id

                })*/
                location="edit?id="+id
            });

        });
    </script>
</head>
<body>
<div style="padding: 10px;">
    <div class="option">
        <input id="addBtn" type="button" value="添加" />
        <input id="delBtn" type="button" value="删除选中" />
    </div>
    <table cellspacing="0" cellpadding="0" bordercolor="gray" border="1" width="100%">
        <%--放标题--%>
        <thead><%--table head--%>
        <tr style="font-weight: bold; font-size: 12pt; height: 25px; background-color: #afd1f3"><%--table row--%>
            <td width="40px" align="center"><%--table data--%>
                <input type="checkbox" id="selectAll" />
            </td>
            <td width="80px" align="center">序号</td>
            <td width="" align="center">商品名称</td>
            <td width="100px" align="center">商品价格</td>
            <td width="100px" align="center">剩余数量</td>
            <td width="500px" align="center">商品描述</td>
            <td width="120px" align="center">编辑</td>
        </tr>
        </thead>
        <%--放数据--%>
        <tbody id="dataBody"></tbody>
    </table>
</div>
</body>
</html>
