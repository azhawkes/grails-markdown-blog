<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Archive</title>
    <meta name="layout" content="markdown-blog-default"/>
</head>

<body>
<h1>All Posts</h1>
<g:each var="month" in="${postsByMonth.keySet()}">
    <h2>${month}</h2>
    <g:each var="post" in="${postsByMonth[month]}">
        <g:set var="url" value="${createLink(action: 'post', params: [permalink: post.permalink, yyyy: post.date.format('yyyy'), mm: post.date.format('MM')])}/"/>
        <a href="${url}">${post.title.encodeAsHTML()}</a><br/>
    </g:each>
</g:each>
</body>
</html>