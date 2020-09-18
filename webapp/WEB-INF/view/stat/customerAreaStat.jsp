<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>客户地区统计</title>
<script type="text/javascript" src="${ctx }/resources/echarts/js/echarts.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/js/jquery-1.8.0.min.js"></script>

</head>
<body style="height: 440px; margin: 0">
    <div id="container" style="height: 100%"></div>

    <script type="text/javascript">

        $.get("${ctx}/stat/loadCustomerAreaStatJson", function (data) {
            var dom = document.getElementById("container");
            var myChart = echarts.init(dom);
            var app = {};
            option = null;
            option = {
                title: {
                    text: '客户地区统计分析图',
                    subtext: '客户统计',
                    x: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: data
                },
                series: [
                    {
                        name: '客户量',
                        type: 'pie',
                        radius: '55%',
                        center: ['50%', '60%'],
                        data: data,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };
            ;
            if (option && typeof option === "object") {
                //根据数据加载图
                myChart.setOption(option, true);
            }
        })

    </script>
</body>
</html>