<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Unpublished Post</title>
    <meta name="layout" content="markdown-blog-default"/>
</head>

<body>
    <g:if test="${post?.status == 'draft'}">
        This post is still in draft status. It will be visible once you publish it.
    </g:if>
    <g:elseif test="${post?.status == 'deleted'}">
        This post has been deleted!
    </g:elseif>
</body>
</html>
