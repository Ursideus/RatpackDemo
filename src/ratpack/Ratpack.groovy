import ratpack.config.ConfigData

import static ratpack.groovy.Groovy.ratpack
import static ratpack.groovy.Groovy.groovyMarkupTemplate
import ratpack.groovy.template.MarkupTemplateModule

ratpack {
  handlers {
    get('getmapping') {
        render 'some data'
    }
    post('postmapping') {
        println request.body.text
        render 'some output'
    }
  }
}

/*ratpack {
  handlers {
    handler {
        println 'some stuff done here'
        next()
    }
    handler {
      render 'some output'
    }
  }
}*/

/*ratpack {
    handlers {
        get("hello") {
            render "Hello, World!"
        }

        get("goodbye") {
            render "Goodbye, World!"
        }
    }
}*/

/*ratpack {
  bindings {
    module MarkupTemplateModule
    // Define config file/Env settings path
    ConfigData configData = ConfigData.of()
            .yaml(ClassLoader.getSystemResource('application.yml'))
            .props(ClassLoader.getSystemResource('application.properties') as URL)
            .sysProps()
            .env()
            .build()
    bindInstance(ConfigData, configData) //this loads this instance into Guice
  }

  handlers {
    // Read config file/Env settings
    handler { ConfigData configData ->
      println configData.get("/property1", String)
      println configData.get("/property2/subproperty2", String)
      println configData.get("/javasysprop", String)
      println configData.get("/MYPROP", String)
      render "done"
    }
    get {
      render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
    }

    files { dir "public" }
  }
}
*/