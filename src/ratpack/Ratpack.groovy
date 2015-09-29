import ratpack.config.ConfigData

import static ratpack.groovy.Groovy.ratpack
import static ratpack.groovy.Groovy.groovyMarkupTemplate
import ratpack.groovy.template.MarkupTemplateModule

import ratpack.jackson.guice.JacksonModule
import static ratpack.jackson.Jackson.json

ratpack {
    bindings {
        module JacksonModule
    }
    handlers {
        get {
            render 'Hello Visitor!'
        }
        get('person') {
            render json(new Person(id: 1, name: 'John Doe', age: 33))
        }
    }
}

class Person {
   Long id
   String name
   int age
}

/*ratpack {
    handlers {
        get('users/:id') { //ex: /users/xyz123
            render context.pathTokens['id']
        }
        get('users') { //ex: /users/
            render 'no path token'
        }
        get('repeatString/:string/:times:[\\d]+') {
            //ex: /repeatString/X/10  would send back 10 Xs
            render context.pathTokens['string'] * context.pathTokens['times'].toInteger()
        }
    }
}*/
  
/*ratpack {
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
*/
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
    //files { it.dir('public') }
  }
}
*/