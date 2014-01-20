<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Blog Admin</title>
    <meta name="layout" content="markdown-blog-admin"/>
</head>

<body>
    <div class="markdown-blog-admin-form">
        <g:if test="${posts}">
            <table class="markdown-blog-posts-table" cellspacing="0">
                <thead>
                    <tr>
                        <th>Post</th>
                        <th>Location</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <g:each var="post" in="${posts}">
                        <tr>
                            <td>
                                <g:link action="edit" id="${post.id}">${post.title?.encodeAsHTML()}</g:link>
                            </td>
                            <td>
                                <blog:link post="${post}"><blog:createLink post="${post}"/></blog:link>
                            </td>
                            <td>
                                <g:message code="post.status.${post.status}"/>
                            </td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </g:if>
        <g:link action="create">+ Create a post</g:link>
    </div>
</body>
</html>