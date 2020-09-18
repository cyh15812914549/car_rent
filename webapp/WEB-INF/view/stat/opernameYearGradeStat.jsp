<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>业务员年度统计</title>
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="Access-Control-Allow-Origin" content="*">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="format-detection" content="telephone=no">
<link rel="icon" href="favicon.ico">
<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all"/>
<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all"/>


</head>


<body style="height: 440px; margin: 0">

    <!-- 搜索条件开始 -->
    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>查询条件</legend>
    </fieldset>
    <form class="layui-form" method="post" id="searchFrm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">选择年份:</label>
                <div class="layui-input-inline">
                    <input type="text" class="layui-input" id="year" readonly="readonly" >
                </div>
            </div>
            <div class="layui-inline">
                <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
            </div>
        </div>
    </form>

    <div id="container" style="height: 100%"></div>
    <script type="text/javascript" src="${ctx }/resources/echarts/js/echarts.min.js"></script>
    <script type="text/javascript" src="${ctx }/resources/js/jquery-1.8.0.min.js"></script>
    <script src="${ctx }/resources/layui/layui.js"></script>

    <script type="text/javascript">

        layui.use([ 'jquery', 'layer', 'form', 'table','laydate'  ], function() {
            var $ = layui.jquery;
            var layer = layui.layer;
            var form = layui.form;
            var table = layui.table;
            var laydate = layui.laydate;

            laydate.render({
                elem: "#year",
                type: "year",
                value: new Date()
            });
            
            $("#doSearch").click(function () {
                getData()
            })


            function getData() {
                var year = $("#year").val();
                if (year == ""){
                    year = new Date().getFullYear()
                }
                $.get("${ctx}/stat/loadOpernameYearGradeStat",{year: year}, function (data) {
                    var dom = document.getElementById("container");
                    var myChart = echarts.init(dom);
                    var app = {};
                    option = null;
                    option = {
                        color: ['#3398DB'],
                        title: {
                            text: '业务员年度统计',
                            subtext: '统计',
                            x: 'center'
                        },
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        grid: {
                            left: '3%',
                            right: '4%',
                            bottom: '3%',
                            containLabel: true
                        },
                        xAxis: [
                            {
                                type: 'category',
                                data: data.name,
                                axisTick: {
                                    alignWithLabel: true
                                }
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: '值',
                                type: 'bar',
                                barWidth: '20%',
                                data: data.value
                            }
                        ]
                    };
                    if (option && typeof option === "object") {
                        //根据数据加载图
                        myChart.setOption(option, true);
                    }
                })
            }
            getData()
        });
    </script>
</body>
</html>