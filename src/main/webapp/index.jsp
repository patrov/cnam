<!DOCTYPE html>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/css/bootstrap.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/kimo.ui.css" />
        <script src="${pageContext.request.contextPath}/js/libs/jquery-1.7.2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/libs/jquery-ui-1.8.16.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/libs/mustache.js"></script>
        <script src="${pageContext.request.contextPath}/js/libs/require.js"></script>
        <script src="${pageContext.request.contextPath}/js/libs/nanoscroller.js"></script>
        <script src="${pageContext.request.contextPath}/js/libs/crypto.google.md5.js"></script>
        <script src="${pageContext.request.contextPath}/js/kimonic/main.js"></script>
        <script src="${pageContext.request.contextPath}/js/apps/readlist/main.js"></script>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome/css/fontawesome.min.css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/js/apps/readlist/assets/css/readlist.css" />
        <style>
            .mainWrapper{margin:50px}
            .listContainer{border: 1px solid blue; width: 500px; height: 520px; float:left}
            .actionContainer { border-bottom:1px solid #DADADA; margin: 0 5px 0 5px }
            .recipesContainer { height: 450px; overflow-y: auto}
            .formContainer{ padding:5px; margin-left:10px;border:1px solid lightblue; float: left; width: 500px; min-height: 400px}
            .clearfix{clear:both}
            .field-title{font-weight: bold}
            .item {border: 1px solid #DADADA; margin: 5px}
            .item .title {font-weight: bold} 
            .itemActions{ position : absolute; bottom:0px}
            .tpl{display:none}
        </style>
    </head>
    <body>

        <div class="mainWrapper">
            <div class="container" style="width: 1024px">
                <ul class="nav nav-pills pull-right" style="margin-bottom: 0px; background:white">
                    <li class=""><a data-action="home:home"><i class="fa fa-home"></i></a></li>
                    <li class=""><a href="#/export" href="#"><i class="fa fa-reply-all"></i> Export (<span id="exportCompteur">0</span>)</a></li>
                    <li><a data-action="ReadList:ParametersActivity"><i class="fa fa-cog"></i> Paramètres</a></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-user"></i> Dimitri, Patrov <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li><a href="#/user/account">Compte</a></li>
                            <li><a href="#/user/invitations">Invitations</a></li>
                            <li><a href="#/user/parameters">Paramètres</a></li>
                            <li><a href="#/user/logout">Déconnexion</a></li>
                        </ul>
                    </li>
                    <li>
                        <div id="searchFieldWrapper" class="input-append" style="margin:3px">
                            <input class="span4" id="searhField" type="text">
                            <button id="searhBtn" class="btn" type="button"><i class="fa fa-search"></i></button>
                        </div>
                    </li>
                </ul>
            </div>
            <div id="readList" class="span12" style="float: right"></div>
        </div>
        <style>
            #strsange{background-color: #27ae60}
        </style>
        <script>
            $(function() {
                try {
                    $.get("${pageContext.request.contextPath}/js/apps/readlist/templates/editcontent.tpl").done(function(response){
                        //$(response).appendTo("body");
                
                        /*Require*/
                        Kimo.require(["ReadList"], function() {
                            var ApplicationManager = require("Kimo.ApplicationManager");
                            ApplicationManager.start("ReadList");
                        }); 
                    });
                }
                catch (e) {
                    console.log("error", e);
                }
            
            });
       
        </script>

    </body>
</html>
