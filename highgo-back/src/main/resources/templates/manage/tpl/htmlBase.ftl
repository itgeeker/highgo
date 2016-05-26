<#--<#macro htmlBase title="" jsFiles=[] cssFiles=[] staticJsFiles=[] staticCssFiles=[] checkLogin=true ><#nested param>
 定义宏变量htmlBase，参数title，默认值为“”，jsFiles默认空数组。。。checkLogin参数默认为true-->
<#macro htmlBase title="" jsFiles=[] cssFiles=[] staticJsFiles=[] staticCssFiles=[] checkLogin=true>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <meta name="description" content="嗨购网上商城(Highgo) B2C网上商城"/>
        <meta name="keywords" content="嗨购 Highgo B2C"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Highgo - JAVA开源电商系统</title>

        <link rel="shortcut icon" type="image/x-icon" href="/images/favicon.png">
        <link rel="stylesheet" href="/zTree3.5/css/zTreeStyle/zTreeStyle.css" type="text/css">
        <link rel="stylesheet" href="/bootstrap3.3.4/css/bootstrap.min.css"  type="text/css">
        <link rel="stylesheet" href="/jquery-ui-1.11.2/jquery-ui.css">
        <link rel="stylesheet" href="/validator-0.7.0/jquery.validator.css" />

        <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="/zTree3.5/js/jquery.ztree.all-3.5.min.js"></script>
        <script type="text/javascript" src="/js/jquery.blockUI.js"></script>
        <script type="text/javascript" src="/bootstrap3.3.4/js/bootstrap.min.js"></script>

        <!-- sb admin -->
        <link rel="stylesheet" href="/sb-admin/css/sb-admin-2.css" />
        <script src="/sb-admin/js/sb-admin-2.js" ></script>
        <link href="/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <script src="/jquery-ui-1.11.2/jquery-ui.js"></script>

        <!-- jquery validator -->
        <script type="text/javascript" src="/validator-0.7.0/jquery.validator.js"></script>
        <script type="text/javascript" src="/validator-0.7.0/local/zh_CN.js"></script>
        <script type="text/javascript" src="/My97DatePicker/WdatePicker.js"></script>

        <link rel="stylesheet" href="/kindeditor-4.1.7/themes/default/default.css" />
        <script charset="utf-8" src="/kindeditor-4.1.7/kindeditor-min.js"></script>
        <script charset="utf-8" src="/kindeditor-4.1.7/lang/zh_CN.js"></script>

        <!-- datatables -->
        <link rel="stylesheet" href="/datatables/css/jquery.dataTables.css" />
        <script charset="utf-8" src="/datatables/js/jquery.dataTables.js"></script>
        <link rel="stylesheet" href="/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.css" />
        <script charset="utf-8" src="/datatables-plugins/integration/bootstrap/3/dataTables.bootstrap.js"></script>

        <!-- metisMenu -->
        <link href="/metisMenu/metisMenu.min.css" rel="stylesheet">
        <script src="/metisMenu/metisMenu.min.js"></script>
        <script type="text/javascript" src="/system/manage/manage.js"></script>

        <script src="/system/manage/login.js"></script>
    </head>
<#--<#nested>指令执行指令开始和结束标记之间的模板片断-->
<#nested/>
</html>
</#macro>
