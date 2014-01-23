<%@ page import="org.springframework.validation.FieldError; com.sourcerefinery.cms.Post" %>

<g:javascript src="epiceditor-0.22.min.js"/>

<g:form action="save" class="edit-post-form" id="${post.id}" onsubmit="return saveEditorContents()">
    <div class="markdown-blog-admin-form">
        <div class="form-row">
            <label for="title">Title</label>
            <g:textField name="title" value="${post.title}"/>
        </div>
        <div class="form-row">
            <label for="markdown">Content</label>
            <div id="epiceditor" class="epiceditor"></div>
            <g:textArea name="markdown" value="${post.markdown}" style="display: none"/>
            <g:hiddenField name="html" value="${post.html}"/>
        </div>
        <div class="form-row clearfix">
            <div class="form-half">
                <label for="author">Author</label>
                <g:textField name="author" value="${post.author}"/>
            </div>
            <div class="form-half">
                <label for="permalink">Permalink</label>
                <g:textField name="permalink" value="${post.permalink}"/>
            </div>
        </div>
        <div class="form-row clearfix">
            <div class="form-half">
                <label for="status">Status</label>
                <g:radioGroup name="status" values="['draft', 'published', 'deleted']" value="${post.status ?: 'draft'}" labels="['Draft', 'Published', 'Deleted']">
                    <label class="radio">${it.radio} ${it.label}</label>
                </g:radioGroup>
            </div>
            <div class="form-half">
                <label for="date">Date</label>
                <g:datePicker name="date" value="${post.date}" precision="day" relativeYears="[-5..1]"/>
            </div>
        </div>
        <div class="form-row clearfix">
            <div class="form-half">
                <label for="type">Type</label>
                <g:radioGroup name="type" values="['post', 'page']" value="${post.type}" labels="['Post', 'Page']">
                    <label class="radio">${it.radio} ${it.label}</label>
                </g:radioGroup>
            </div>
        </div>
        <g:hasErrors bean="${post}">
            <ul class="errors" role="alert">
                <g:eachError bean="${post}" var="error">
                    <li><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
        </g:hasErrors>
    </div>
    <div class="markdown-blog-admin-footer">
        <div class="edit-post-buttons">
            <g:submitButton name="save" value="Save"/>
            <g:link action="index">Cancel</g:link>
        </div>
    </div>
</g:form>

<script type="text/javascript">
    var uniqueName = "epiceditor-" + Math.random();
    var markdown = document.getElementById("markdown");
    var html = document.getElementById("html");

    var opts = {
        basePath: "/epiceditor",
        textarea: markdown,
        file: {
            name: uniqueName
        },
        autogrow: {
            minHeight: 200,
            maxHeight: 500
        }
    };

    var editor = new EpicEditor(opts).load();

    function saveEditorContents() {
        editor.save();
        html.value = editor.exportFile(uniqueName, "html");

        return true;
    }

</script>

