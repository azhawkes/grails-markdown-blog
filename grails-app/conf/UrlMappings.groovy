class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.${format})?"{
            constraints {
                // apply constraints here
            }
        }

        "/blog/$permalink" {
            controller = "blog"
            action = "post"
        }

        "/blog/$yyyy/$mm/$permalink" {
            controller = "blog"
            action = "post"
        }

        "/blog/show/$id" {
            controller = "blog"
            action = "post"
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
