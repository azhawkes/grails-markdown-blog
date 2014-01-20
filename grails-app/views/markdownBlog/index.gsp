<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Blog</title>
    <meta name="layout" content="markdown-blog-default"/>
</head>

<body>
    <g:each var="post" in="${posts}">
        <g:render template="post" model="[post: post]"/>
    </g:each>
</body>
</html>