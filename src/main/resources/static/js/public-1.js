$(function () {
    // 打开搜索框
    $("#layui-icon-search").click(function (e) {
        $("#search-win").toggle()
        $("#sortlist").hide();
        e.stopPropagation()
    });

    //==================================
    // 关闭搜索框
    $("#layui-icon-close").click(function () {
        $("#search-win").hide()
    });
    // 关闭分享弹窗
    $("#layui-icon-close03").click(function () {
        $("#curtain").hide()
        $("#sharefiles").hide()
        var id = $("#hidetext1").text();
        $.ajax({
            type: "post",
            url: "/files/setshare",    //请求发送到dataActiont处
            data: {"id":id,"isshare":1},
            success: function (data) {
                if (data!=1){
                    layer.msg("分享添加异常");
                }
            }
        })
    });
    // 关闭右键菜单
    $(".files").mouseleave(function(){
        $(".rightsub-list").hide();
    });
})

window.onload = function () {

    //;layui操作结果反馈
    var layer = layui.layer;
    var chooseones = document.getElementsByClassName("chooseone")
    var files = document.getElementsByClassName("files");
    var rightsub = document.getElementsByClassName("rightsub");
    for (var i=0; i<files.length; i++){
        // 鼠标右击文件弹出框
        files[i].oncontextmenu = function () {
            this.children[0].style.display = "block";
        }
        files[i].onmouseover = function () {
            if (!(this.children[1].children[0].checked)){
                this.style.backgroundColor = "#eeeeee";
                this.children[1].style.display = "block";
                this.children[2].style.display = "block";
            }
        }
        files[i].onmouseout = function () {
            if (!(this.children[1].children[0].checked)) {
                this.style.backgroundColor = "#ffffff";
                this.children[1].style.display = "none";
                this.children[2].style.display = "none";
            }
        }
    }
    for (var i=0; i<rightsub.length; i++){
        rightsub[i].onclick = function(){
            this.parentNode.children[0].style.display = "block";
        }
    }


    // 下载所有选择过的文件
    document.getElementById("downloadall").onclick = function () {
        var ids = new Array();
        for (let i = 0; i < chooseones.length; i++) {
            if (chooseones[i].checked){
                ids.push(chooseones[i].nextElementSibling.value);
            }
        }
        if (ids.length===0){
            return false;
        }
        window.location.href = "/files/downloadfiles?ids="+ids;
        layer.msg("下载准备中...");
    };
}

// 全选
function allchoose(){
    var allchoose = document.getElementById("allchoose");
    var chooseones = document.getElementsByClassName("chooseone");
    if (allchoose.checked){
        for (var i=0;i<chooseones.length;i++){
            chooseones[i].checked = true
            document.getElementsByClassName("leftsub")[i].style.display = "block";
            document.getElementsByClassName("files")[i].style.backgroundColor = "#ecefff"
            document.getElementById("downlist").style.display = "block"
        }
    }else{
        for (var i=0;i<chooseones.length;i++){
            chooseones[i].checked = false
            document.getElementsByClassName("leftsub")[i].style.display = "none";
            document.getElementsByClassName("files")[i].style.backgroundColor = "#ffffff"
            document.getElementById("downlist").style.display = "none"
        }
    }
}
// 选中一个
function chooseone() {
    var chooseones = document.getElementsByClassName("chooseone")
    var j = 0;
    var k = 0;
    for (var i=0;i<chooseones.length;i++){
        if (chooseones[i].checked){
            document.getElementById("downlist").style.display = "block"
            document.getElementsByClassName("leftsub")[i].style.display = "block";
            document.getElementsByClassName("rightsub")[i].style.display = "none";
            document.getElementsByClassName("files")[i].style.backgroundColor = "#ecefff"
            j++

        }else{
            document.getElementsByClassName("leftsub")[i].style.display = "none";
            document.getElementsByClassName("files")[i].style.backgroundColor = "#ffffff"
            k++;
        }
    }
    if (k===chooseones.length){
        document.getElementById("downlist").style.display = "none"
    }
    if (j===chooseones.length){
        document.getElementById("allchoose").checked = true
    }else{
        document.getElementById("allchoose").checked = false
    }
}
// 取消所有选择(底部弹出框)
function closeallchoose() {
    document.getElementById("allchoose").checked = false
    var chooseone = document.getElementsByClassName("chooseone")
    for (var i=0;i<chooseone.length;i++){
        chooseone[i].checked = false;
        document.getElementsByClassName("leftsub")[i].style.display = "none";
        document.getElementsByClassName("files")[i].style.backgroundColor = "#ffffff"
    }
    document.getElementById("downlist").style.display = "none"
}
var curWwwPath=window.document.location.href;
var pathName=window.document.location.pathname;
var pos=curWwwPath.indexOf(pathName);
var localhostPaht=curWwwPath.substring(0,pos);
// 分享弹出窗口(除相册页面外其他页面共享)
function sharefile(avatar,name,id) {
    document.getElementById("avatar2").src = "/icon/"+avatar+".png";
    document.getElementById("filename2").innerText = name;
    document.getElementById("linkid").innerText = localhostPaht+"/files/downloadfile?fileid="+id;
    document.getElementById("hidetext1").innerText = id;
    var elementsByClassName = document.getElementsByClassName("rightsub-list");
    for (let i = 0; i < elementsByClassName.length; i++) {
        elementsByClassName[i].style.display = "none"
    }
    document.getElementById("curtain").style.display = "block";
    document.getElementById("sharefiles").style.display = "block";
}

// 复制连接到粘贴板
function copylink() {
    var link = document.getElementById("linkid").innerText;
    var hidetext = document.getElementById("hidetext");
    hidetext.value = link;
    hidetext.select();
    document.execCommand("copy")
    var layer = layui.layer;
    layer.msg("口令已复制，分享给好友吧");
}

// 提示框
function onclick02(index){
    var elementsByClassName = document.getElementsByClassName("tip-list");
    var searchinput = document.getElementById("search-input")
    var tiplist = ["图片:","视频:","音频:","压缩文件:","文档:"]
    for (var i=0;i<elementsByClassName.length;i++){
        if (index===i){
            searchinput.value=tiplist[i];
            searchinput.focus()
            document.getElementById("tip-frame").style.display = "none";
            document.getElementById("search-win").style.height = "80px";
            break;
        }
    }
}

function oninput01(){
    var searchinput = document.getElementById("search-input")
    var frame = document.getElementById("tip-frame")
    var win = document.getElementById("search-win")
    if (searchinput.value.length>0){
        frame.style.display = "none";
        win.style.height = "80px";
    } else{
        frame.style.display = "block";
        win.style.height = "272px";
    }
}


//关闭默认右键菜单
document.oncontextmenu = function (e) {
    e.preventDefault()
}

