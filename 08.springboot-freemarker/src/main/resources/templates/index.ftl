<!DOCTYPE html>
<html>
<head>
    <title>student</title>
    <link rel="stylesheet" href="../static/css/index.css">
</head>

<body>
<div>
    <table border="1">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
        </tr>
        <#list students as student>
            <tr>
                <td>${student.studentId}</td>
                <td>${student.name}</td>
                <td>
                    <#if student.sex == 'M'>
                        男
                    </#if>
                    <#if student.sex == 'F'>
                        女
                    </#if>
                </td>
            </tr>
        </#list>
    </table>
</div>
</body>
</html>