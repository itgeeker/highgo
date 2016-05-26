<#import "manage/tpl/pageBase.ftl" as page>
<@page.pageBase currentMenu="首页">
<style>
    .font-focus{
        font-weight: 700;font-size: 16px;color: #f50 !important;text-decoration: underline;
    }
</style>
<script>
    $(function() {
        $( "#tabs" ).tabs({
            //event: "mouseover"
        });
    });
</script>
<div id="tabs">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active"> <a href="#tabs-1" style="font-size: 14px;" data-toggle="tab"> 十万火急</a></li>
        <li><a href="#tabs-2" style="font-size: 14px;" data-toggle="tab">基本设置</a></li>
        <li><a href="#tabs-3" style="font-size: 14px;" data-toggle="tab">图片设置</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="tabs-1">
            <!--<p>W3Cschoool菜鸟教程是一个提供最新的web技术站点，本站免费提供了建站相关的技术文档，帮助广大web技术爱好者快速入门并建立自己的网站。菜鸟先飞早入行——学的不仅是技术，更是梦想。</p>-->
            <div class="alert alert-success" style="margin-bottom: 2px;text-align: left;">
                <span class="badge badge-important">重要</span>&nbsp;<strong>主公，订单汇总如下：</strong>
            </div>
            <table class="table table-bordered">
                <tr>
                    <td>未付款订单数：<a class="font-focus" href="http://localhost:8080/manage/order/selectList?init=y&paystatus=n&status=init"></a></td>
                    <td>已付款，但未审核订单数：<a class="font-focus" style="color: #f50;" href="http://localhost:8080/manage/order/selectList?init=y&paystatus=y&status=init"></a></td>
                </tr>
                <tr>
                    <td>已取消订单数：<a class="font-focus" href="http://localhost:8080/manage/order/selectList?init=y&status=cancel"></a></td>
                    <td>等待发货订单数：<a class="font-focus" style="color: #f50;" href="http://localhost:8080/manage/order/selectList?init=y&paystatus=y&status=pass"></a></td>
                </tr>
            </table>

            <div class="alert alert-info" style="margin-bottom: 2px;text-align: left;">
                <strong><span class="badge badge-important">重要</span>&nbsp;主公，退款订单汇总如下：</strong>
            </div>
            <table class="table table-bordered">
                <tr>
                    <td>【卖家需立即处理】退款协议等待卖家确认中：
                        <a class="font-focus" href="http://localhost:8080/manage/order/selectList?init=y&refundStatus=WAIT_SELLER_AGREE"></a>
                    </td>
                </tr>
                <tr>
                    <td>【卖家需立即处理】等待卖家收货：
                        <a class="font-focus" href="http://localhost:8080/manage/order/selectList?init=y&refundStatus=WAIT_SELLER_CONFIRM_GOODS"></a>
                    </td>
                </tr>

                <tr>
                    <td>【等待买家处理完】卖家不同意协议，等待买家修改：
                        <a class="font-focus" href="http://localhost:8080/manage/order/selectList?init=y&refundStatus=SELLER_REFUSE_BUYER"></a>
                    </td>
                </tr>
                <tr>
                    <td>【等待买家处理完】退款协议达成，等待买家退货：
                        <a class="font-focus" href="http://localhost:8080/manage/order/selectList?init=y&refundStatus=WAIT_BUYER_RETURN_GOODS"></a>
                    </td>
                </tr>
            </table>

            <div class="alert alert-warning" style="margin-bottom: 2px;text-align: left;">
                <strong><span class="badge badge-info">关注</span>&nbsp;主公，吐槽和缺货也是要关注一下子的啊：</strong>
            </div>
            <table class="table table-bordered">
                <tr>
                    <td>缺货商品数：<a class="font-focus" href="http://localhost:8080/manage/product/selectList?init=y&selectOutOfStockProduct=true"></a></td>
                    <td>未回复的吐槽评论数：<a class="font-focus" href="http://localhost:8080/manage/comment/selectList?init=y&selectCommentFromIndex=true"></a></td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="tabs-2">
            <table class="table table-condensed">
                <tr>
                    <td style="text-align: right;">系统版本:</td>
                    <td style="text-align: left;">${systemSetting.getVersion()}</td>
                </tr>
                <tr>
                    <td style="text-align: right;">系统代号:</td>
                    <td style="text-align: left;">${systemSetting.getSystemcode()}</td>
                </tr>
                <tr>
                    <td style="text-align: right;">名称:</td>
                    <td style="text-align: left;">${systemSetting.getName()}</td>
                </tr>
                <tr>
                    <td style="text-align: right;">简介:</td>
                    <td style="text-align: left;">${systemSetting.getWww()}</td>
                </tr>
                <tr>
                    <td style="text-align: right;">log:</td>
                    <td style="text-align: left;">${systemSetting.getLog()}</td>
                </tr>
                <tr>
                    <td style="text-align: right;">网站标题:</td>
                    <td style="text-align: left;">${systemSetting.getTitle()}</td>
                </tr>
                <tr>
                    <td style="text-align: right;">description:</td>
                    <td style="text-align: left;">${systemSetting.getDescription()}</td>
                </tr>
                <tr>
                    <td style="text-align: right;">keywords:</td>
                    <td style="text-align: left;">
                    ${systemSetting.getKeywords() }
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">shortcuticon:</td>
                    <td style="text-align: left;">
                    ${systemSetting.getShortcuticon() }
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">联系地址:</td>
                    <td style="text-align: left;">
                    ${systemSetting.getAddress() }
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">联系电话:</td>
                    <td style="text-align: left;">
                    ${systemSetting.getTel() }
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">邮箱:</td>
                    <td style="text-align: left;">
                    ${systemSetting.getEmail() }
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">备案号:</td>
                    <td style="text-align: left;">
                    ${systemSetting.getIcp() }
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">是否开放网站:</td>
                    <td style="text-align: left;">
                        <input type="checkbox" disabled="disabled"  checked="${systemSetting.getIsopen() }" value="${systemSetting.getIsopen() }"/>
                    </td>
                </tr>
                <tr>
                    <td style="text-align: right;">关闭信息:</td>
                    <td style="text-align: left;">${systemSetting.getClosemsg() }</td>
                </tr>
            </table>
        </div>
        <div class="tab-pane fade" id="tabs-3">
            <table class="table table-condensed">
                <tr>
                    <td style="text-align: right;">图片根路径</td>
                    <td style="text-align: left;" >${systemSetting.getImagerootpath() }</td>
                </tr>
            </table>
        </div>
    </div>
</div>
        <!-- tab end -->
</@page.pageBase>