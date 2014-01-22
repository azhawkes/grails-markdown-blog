includeTargets << grailsScript("_GrailsInit")

target(markdownBlogGenerateViews: "Generate post and page views for the Markdown Blog plugin") {
    def indexView = "$markdownBlogPluginDir/grails-app/views/markdownBlog/index.gsp"
    def archiveView = "$markdownBlogPluginDir/grails-app/views/markdownBlog/archive.gsp"
    def pageView = "$markdownBlogPluginDir/grails-app/views/markdownBlog/page.gsp"
    def postView = "$markdownBlogPluginDir/grails-app/views/markdownBlog/post.gsp"
    def _postView = "$markdownBlogPluginDir/grails-app/views/markdownBlog/_post.gsp"
    def outputDir = "${basedir}/grails-app/views/markdownBlog"

    ant.mkdir(dir: outputDir)

    println "Created directory ${outputDir}"

    ant.copy(file: indexView, todir: outputDir)
    ant.copy(file: archiveView, todir: outputDir)
    ant.copy(file: pageView, todir: outputDir)
    ant.copy(file: postView, todir: outputDir)
    ant.copy(file: _postView, todir: outputDir)

    println "Copied views to ${outputDir}"

    def configFile = new File("${basedir}/grails-app/conf/Config.groovy")

    if (configFile.exists()) {
        configFile.withWriterAppend { BufferedWriter writer ->
            writer.newLine()
            writer.newLine()
            writer.writeLine '// Added by the Markdown Blog plugin'
            writer.writeLine 'grails.plugin.markdownblog.title = "Untitled Blog"'
            writer.writeLine 'grails.plugin.markdownblog.dateFormat = "yyyy/MM/dd"'
            writer.writeLine 'grails.plugin.markdownblog.postUrlShowYYYYMM = true'
            writer.writeLine 'grails.plugin.markdownblog.indexView = "/markdownBlog/index"'
            writer.writeLine 'grails.plugin.markdownblog.archiveView = "/markdownBlog/archive"'
            writer.writeLine 'grails.plugin.markdownblog.postView = "/markdownBlog/post"'
            writer.writeLine 'grails.plugin.markdownblog.pageView = "/markdownBlog/page"'
            writer.newLine()
        }
    }

    println "Added default configs to Config.groovy"
    println "Make sure to map your blog controllers in UrlMappings.groovy if you haven't already!"
}

setDefaultTarget(markdownBlogGenerateViews)
