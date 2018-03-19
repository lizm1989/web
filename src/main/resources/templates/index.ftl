<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>测试</title>
</head>
<body>


<table>
    <tr>
        <td>ID</td>
        <td>销售名称</td>
        <td>图片</td>
    </tr>
<#list userList as user>
  <tr>
      <td>${user.id}</td>
      <td name="${user.name}">${user.name}</td>
      <td><img src="${user.url}" width="100px" height="100px"/></td>
  </tr>

</#list>
</table>
<form method="post" action="listdown">
<select name="name">
<#list xsList as user>
        <option value ="${user.name}">${user.name}</option>
</#list>
</select>
    <input type="submit" value="下载表格数据" />
</form>
</body>
</html>