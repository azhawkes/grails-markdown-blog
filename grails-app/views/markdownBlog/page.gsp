<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>${post.title.encodeAsHTML()}</title>
    <meta name="layout" content="markdown-blog-default"/>
</head>

<body>
    ${post.content?.encodeAsHTML()}
</body>
</html>