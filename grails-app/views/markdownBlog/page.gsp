<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${post.title.encodeAsHTML()}</title>
    <meta name="layout" content="markdown-blog-default"/>
</head>

<body>
    ${post.html}
</body>
</html>