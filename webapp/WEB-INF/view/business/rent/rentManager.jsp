<%--
  Created by IntelliJ IDEA.
  User: Chen
  Date: 2020/9/7
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>出租管理</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="icon" href="favicon.ico">
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
    <!-- 搜索条件开始 -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>查询条件</legend>
    </fieldset>
    <form class="layui-form" method="post" id="searchFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">身份证号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="identity"  id="identity" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            </div>
        </div>
    </form>

    <!-- 搜索条件结束 -->

    <!-- 数据表格开始 -->
    <div id="content" style="display: none;">
        <table  id="carTable" lay-filter="carTable"></table>
        <div  id="carBar" style="display: none;">
            <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="rentCar">出租汽车</a>
            <a class="layui-btn layui-btn-xs" lay-event="viewImage">查看车辆大图</a>
        </div>
    </div>
    <!-- 数据表格结束 -->

    <!-- 添加和修改的弹出层开始 -->
    <div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
        <form class="layui-form layui-row layui-col-space10"  lay-filter="dataFrm" id="dataFrm">
        </form>
    </div>
    <!-- 添加和修改的弹出层结束 -->
    <!-- 查看大图弹出的层 开始 -->
    <div id="viewCarImageDiv" style="display: none;text-align: center;">
        <img alt="出租图片" width="550" height="350" id="view_carimg">
    </div>
    <!-- 查看大图弹出的层 结束 -->

    <script src="${ctx }/resources/layui/layui.js"></script>
    <script type="text/javascript">
        var tableIns;
        layui.use([ 'jquery', 'layer', 'form', 'table','upload'  ], function() {
            var $ = layui.jquery;
            var layer = layui.layer;
            var form = layui.form;
            var table = layui.table;
            var upload=layui.upload;

            function initCarData(){
                //渲染数据表格
                tableIns=table.render({
                    elem: '#carTable'   //渲染的目标对象
                    ,url:'${ctx}/car/loadAllCar?isrenting=0' //数据接口
                    ,title: '车辆据表'//数据导出来的标题
                    ,height:'full-220'
                    ,cellMinWidth:100 //设置列的最小默认宽度
                    ,page: true  //是否启用分页
                    ,cols: [[   //列表数据
                        {field:'carnumber', title:'车牌号',align:'center',width:'120'}
                        ,{field:'cartype', title:'出租类型',align:'center',width:'100'}
                        ,{field:'color', title:'出租颜色',align:'center',width:'120'}
                        ,{field:'price', title:'购买价格',align:'center',width:'150'}
                        ,{field:'rentprice', title:'出租价格',align:'center',width:'120'}
                        ,{field:'deposit', title:'出租押金',align:'center',width:'120'}
                        ,{field:'isrenting', title:'出租状态',align:'center',width:'80',templet:function(d){
                                return d.isrenting=='1'?'<font color=blue>已出租</font>':'<font color=red>未出租</font>';
                            }}
                        ,{field:'description', title:'出租描述',align:'center',width:'80'}
                        ,{field:'carimg', title:'缩略图',align:'center',width:'180',templet:function(d){
                                return "<img width=40 height=40 src=${ctx}/file/downloadShowFile?path="+d.carimg+" />";
                            }}
                        ,{field:'createtime', title:'录入时间',align:'center',width:'180'}
                        ,{fixed: 'right', title:'操作', toolbar: '#carBar', width:220,align:'center'}
                    ]]
                })
            }

            //模糊查询
            $("#doSearch").click(function(){
                var params=$("#searchFrm").serialize();
                $.post("${ctx}/rent/checkCustomerExist",params,function(obj){
                    if(obj.code>=0){
                        $("#content").show();
                        initCarData();
                    }else{
                        layer.msg("客户身份证号不存在,请更正后再查询");
                        //隐藏
                        $("#content").hide();
                    }
                })
            });

            //监听头部工具栏事件
            table.on("toolbar(carTable)",function(obj){
                switch(obj.event){
                    case 'add':
                        openAddCar();
                        break;
                    case 'deleteBatch':
                        deleteBatch();
                        break;
                };
            })
            //监听行工具事件
            table.on('tool(carTable)', function(obj){
                var data = obj.data; //获得当前行数据
                var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
                if(layEvent === 'del'){ //删除
                    layer.confirm('真的删除【'+data.carnumber+'】这个出租吗', function(index){
                        //向服务端发送删除指令
                        $.post("${ctx}/car/deleteCar",{carnumber:data.carnumber},function(res){
                            layer.msg(res.msg);
                            //刷新数据 表格
                            tableIns.reload();
                        })
                    });
                } else if(layEvent === 'edit'){ //编辑
                    openUpdateCar(data);
                }else if(layEvent==='viewImage'){
                    showCarImage(data);
                }
            });

            var url;
            var mainIndex;
            //打开添加页面
            function openAddCar(){
                mainIndex=layer.open({
                    type:1,
                    title:'添加出租',
                    content:$("#saveOrUpdateDiv"),
                    area:['1000px','450px'],
                    success:function(index){
                        //清空表单数据
                        $("#dataFrm")[0].reset();
                        //设置默认图片
                        $("#showCarImg").attr("src","${ctx}/file/downloadShowFile?path=images/defaultcarimage.jpg")
                        $("#carimg").val("images/defaultcarimage.jpg")
                        url="${ctx}/car/addCar";
                        $("#carnumber").removeAttr("readonly");
                    }
                });
            }
            //打开修改页面
            function openUpdateCar(data){
                mainIndex=layer.open({
                    type:1,
                    title:'修改出租',
                    content:$("#saveOrUpdateDiv"),
                    area:['1000px','450px'],
                    success:function(index){
                        form.val("dataFrm",data);
                        $("#showCarImg").attr("src","${ctx}/file/downloadShowFile?path="+data.carimg);
                        url="${ctx}/car/updateCar";
                        $("#carnumber").attr("readonly","readonly");
                    }
                });
            }
            //保存
            form.on("submit(doSubmit)",function(obj){
                //序列化表单数据
                var params=$("#dataFrm").serialize();
                $.post(url,params,function(obj){
                    layer.msg(obj.msg);
                    //关闭弹出层
                    layer.close(mainIndex)
                    //刷新数据 表格
                    tableIns.reload();
                })
            });

            //批量删除
            function deleteBatch(){
                //得到选中的数据行
                var checkStatus = table.checkStatus('carTable');
                var data = checkStatus.data;
                var params="";
                $.each(data,function(i,item){
                    if(i==0){
                        params+="ids="+item.carnumber;
                    }else{
                        params+="&ids="+item.carnumber;
                    }
                });
                layer.confirm('真的删除选中的这些出租吗', function(index){
                    //向服务端发送删除指令
                    $.post("${ctx}/car/deleteBatchCar",params,function(res){
                        layer.msg(res.msg);
                        //刷新数据 表格
                        tableIns.reload();
                    })
                });
            }


            //上传图片
            //上传缩略图
            upload.render({
                elem: '#carimgDiv',
                url: '${ctx}/file/uploadFile',
                method : "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
                acceptMime:'images/*',
                field:"mf",
                done: function(res, index, upload){
                    $('#showCarImg').attr('src',"${ctx}/file/downloadShowFile?path="+res.data.src);
                    $('#carimg').val(res.data.src);
                    $('#carimgDiv').css("background","#fff");
                }
            });

            //查看大图
            function showCarImage(data){

                mainIndex=layer.open({
                    type:1,
                    title:"【"+data.carnumber+'】的出租图片',
                    content:$("#viewCarImageDiv"),
                    area:['600px','400px'],
                    success:function(index){
                        $("#view_carimg").attr("src","${ctx}/file/downloadShowFile?path="+data.carimg);
                    }
                });
            }
        });
    </script>
</body>
</html>
