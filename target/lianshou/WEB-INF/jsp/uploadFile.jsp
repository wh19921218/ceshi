<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>上传文件</title>
</head>
<body>
<script type="text/javascript" src="../../static/js/jquery-form.js"></script>
    <form id="fileupload" enctype="multipart/form-data" action="uploadfile.do" method="post">
        <p style="font-size:16px;">请选择正确的excel文件上传</p>
        <input id="txt" class="input" type="text" disabled="disabled" value="文件域" name="txt">
        <input class="liulan" type="button" onclick="file.click()" size="30" value="上传文件" onmousemove="file.style.pixelLeft=event.x-60;file.style.pixelTop=this.offsetTop;">
        <input id="file1" class="files" type="file" hidefocus="" size="1" style="height:26px;" name="file" onchange="txt.value=this.value">
        <br/><input type="button" onclick="checkSuffix();" value="提交上传" style="height:26px;width:100px">
        <p style="color:red;">支持的excel格式为：xls、xlsx、xlsb、xlsm、xlst！</p>
    </form>
    <script>
        //用于验证文件扩展名的正则表达式
        function checkSuffix(){
            var name = document.getElementById("txt").value;
            var strRegex = "(.xls|.xlsx|.xlsb|.xlsm|.xlst)$";
            var re=new RegExp(strRegex);
            if (re.test(name.toLowerCase())){

                $('#fileupload').ajaxSubmit(function(result) {

                    $.messager.alert('提示',result.msg);
                });
            } else{
                alert("文件名不合法");
            }
        }
    </script>
</body>


</html>
