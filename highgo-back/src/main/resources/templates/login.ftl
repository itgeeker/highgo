<#import "htmlBase.ftl" as html>
<@html.htmlBase checkLogin=false jsFiles=["login.js"]>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-success">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        <b>嗨购网上商城-后台</b>
                    </h3>
                </div>
                <div class="panel-body">
                    <form role="form" id="formLogin" action="localhost:8080/manage/user/login" method="post">
                        <fieldset>
                            <div class="form-group">
                                <#if errorMsg??>
                                    <div class="alert alert-danger alert-dismissable">
                                        <button type="button" class="close" data-dismiss="alert"
                                                aria-hidden="true">&times;</button>
                                    ${errorMsg}
                                    </div>
                                </#if>
                            </div>
                            <div class="form-group has-success">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <span class="fa fa-user fa-fw"></span>
                                    </span>
                                    <input type="text" value="" placeholder="账号" name="username"
                                           class="len form-control" id="username" autofocus/>
                                </div>
                            </div>
                            <div class="form-group has-success">
                                <div class="input-group">
                                    <span class="input-group-addon">
                                        <span class="fa fa-key fa-fw"></span>
                                    </span>
                                    <input type="password" name="password" placeholder="密码" class="len form-control"
                                           label="密码"/>
                                </div>
                            </div>
                            <div class="row help-block">
                                <div class="col-md-offset-1 col-md-11">
                                    <span class="fa fa-info-circle"></span>默认用户名密码:admin/123456
                                    </span><a class="btn btn-link" href="http://git.oschina.net/dinguangx/jshop" target="_blank"><span class="fa fa-git-square">源码</a>
                                </div>
                            </div>
                            <input type="submit" id="btnLogin" class="btn btn-lg btn-success btn-block" value="登录">
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</@html.htmlBase>